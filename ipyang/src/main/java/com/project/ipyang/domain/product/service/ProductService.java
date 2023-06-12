package com.project.ipyang.domain.product.service;

import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.product.dto.InsertProductDto;
import com.project.ipyang.domain.product.dto.ProductDto;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.repository.ProductRepository;
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

    public ProductDto createProduct(InsertProductDto productDto){
//        List<MultipartFile> file = productDto.getImageFiles();
//        if(!file.isEmpty()){
//            //파일 업로드 되었을때 수행되는 로직
//        }
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


}
