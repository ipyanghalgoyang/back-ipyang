package com.project.ipyang.domain.product.controller;

import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class ProductController {

        private final ProductRepository productRepository;




//        @PostConstruct
//        public void init(){
//                for (int i =0; i<11; i++){
//                        productRepository.save(new Product("product"+ i , i ));
//                }
//        }
}
