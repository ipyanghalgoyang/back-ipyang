package com.project.ipyang.domain.product.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.product.dto.*;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.entity.Product_Img;
import com.project.ipyang.domain.product.repository.ProductRepository;
import com.project.ipyang.domain.product.repository.Product_ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final Product_ImgRepository productImgRepository;


    public ProductDto createProduct(InsertProductDto productDto) {


        Member member = memberRepository.findById(productDto.getMember_id()).get();

        Product product = Product.builder().name(productDto.getName())
                .status(productDto.getStatus())
                .price(productDto.getPrice())
                .type(productDto.getType())
                .loc(productDto.getLoc())
                .member(member)
                .build();


        productRepository.save(product);
        return new ProductDto();
    }

    public Product_ImgDto createProduct_Img(InsertProduct_ImgDto productImgDto) {

        Product product = productRepository.findById(productImgDto.getProduct_id()).orElse(null);


        Product_Img productImg = Product_Img.builder().original_file(productImgDto.getOriginal_file())
                .stored_file(productImgDto.getStored_file())
                .build();
        productImgRepository.save(productImg);
        return new Product_ImgDto();
    }


    //전체 상품 데이터 가져오기
    public ResponseDto selectAllProduct(SelectProductDto request) {
        List<Product> products = productRepository.findAll();
        List<SelectProductDto> selectProductDtos = products.stream().map(SelectProductDto::new).collect(Collectors.toList());

        if(!selectProductDtos.isEmpty()) {
            return new ResponseDto(selectProductDtos, HttpStatus.OK.value());
        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}




