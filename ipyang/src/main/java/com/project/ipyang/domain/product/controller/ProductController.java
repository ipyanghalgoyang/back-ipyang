package com.project.ipyang.domain.product.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.product.dto.InsertProductDto;
import com.project.ipyang.domain.product.dto.ProductDto;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.repository.ProductRepository;
import com.project.ipyang.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class ProductController {

        private final ProductService productService;

        @PostMapping(value = "/v1/product")
        public ResponseDto<ProductDto> createProduct(InsertProductDto request){
                return new ResponseDto(productService.createProduct(request));
        }


//        @PostConstruct
//        public void init(){
//                for (int i =0; i<11; i++){
//                        productRepository.save(new Product("product"+ i , i ));
//                }
//        }
}
