package com.project.ipyang.domain.product.service;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.product.dto.*;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.entity.ProductImg;
import com.project.ipyang.domain.product.repository.ProductRepository;
import com.project.ipyang.domain.product.repository.ProductImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ProductImgRepository productImgRepository;
    private  final EntityManager em;


    public ProductDto createProduct(InsertProductDto productDto, Long memberId) {
        // memberId를 기반으로 회원 정보를 필요한 경우에 가져옵니다.
        Member member = memberRepository.findById(memberId).orElse(null);

        // 필요한 데이터를 사용하여 Product 객체를 생성합니다.
        Product product = Product.builder()
                .name(productDto.getName())
                .status(IpyangEnum.ProductStatus.N)
                .price(productDto.getPrice())
                .type(productDto.getType())
                .loc(productDto.getLoc())
                .member(member)
                .build();

        // 상품을 저장합니다.
        productRepository.save(product);

        // 생성된 ProductDto나 필요한 응답을 반환합니다.
        return new ProductDto();
    }


    public ProductImgDto createProduct_Img(InsertProductImgDto productImgDto) {

        Product product = productRepository.findById(productImgDto.getProductId()).orElse(null);
        ProductImg productImg = ProductImg.builder().imgOriginFile(productImgDto.getImgOriginFile())
                .imgStoredFile(productImgDto.getImgStoredFile())
                .build();
        productImgRepository.save(productImg);
        return new ProductImgDto();
    }


    //전체 상품 데이터 가져오기
    public ResponseDto selectAllProduct(SelectProductDto request) {
        List<Product> products = productRepository.findAll();
        List<SelectProductDto> selectProductDtos = products.stream().map(SelectProductDto::new).collect(Collectors.toList());

        if(!selectProductDtos.isEmpty()) {
            return new ResponseDto(selectProductDtos, HttpStatus.OK.value());
        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public Object updateProduct(UpdateProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(productDto.getId());
        if (!productOptional.isPresent()) {
            return new ResponseDto("존재하지 않는 판매글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Product product = productOptional.get();
        UpdateProductDto updateProduct = product.convertUpdateDto();
        updateProduct.setName(productDto.getName());
        updateProduct.setPrice(productDto.getPrice());
        updateProduct.setType(productDto.getType());
        updateProduct.setLoc(productDto.getLoc());
        productRepository.save(updateProduct.toEntity());

        return new ResponseDto("판매글이 수정되었습니다.", HttpStatus.OK.value());

    }



    public ResponseDto soldoutProduct(SoldProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(productDto.getId());
        if (!productOptional.isPresent()) {
            return new ResponseDto("존재하지 않는 판매글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Product product = productOptional.get();
        SoldProductDto updateProduct = product.convertSoldDto();
        updateProduct.setStatus(IpyangEnum.ProductStatus.Y);

        productRepository.save(updateProduct.toEntity());

        return new ResponseDto("판매가 완료되었습니다.", HttpStatus.OK.value());
    }





    public ResponseDto deleteProduct(ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(productDto.getId());
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
            return new ResponseDto("판매글삭제 성공", HttpStatus.OK.value());
        }
        else {
            return new ResponseDto("판매글삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}




