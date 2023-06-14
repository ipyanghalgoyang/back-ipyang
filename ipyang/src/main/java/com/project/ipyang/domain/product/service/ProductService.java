package com.project.ipyang.domain.product.service;

import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.product.dto.InsertProductDto;
import com.project.ipyang.domain.product.dto.InsertProduct_ImgDto;
import com.project.ipyang.domain.product.dto.ProductDto;
import com.project.ipyang.domain.product.dto.Product_ImgDto;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.entity.Product_Img;
import com.project.ipyang.domain.product.repository.ProductRepository;
import com.project.ipyang.domain.product.repository.Product_ImgRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private  final Product_ImgRepository productImgRepository;

    public ProductDto createProduct(InsertProductDto productDto ){



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
        return  new Product_ImgDto();
    }



}
