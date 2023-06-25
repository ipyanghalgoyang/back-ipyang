package com.project.ipyang.domain.product.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.product.dto.*;
import com.project.ipyang.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

        private final ProductService productService;
        private final HttpServletRequest request;

        @PostMapping(value = "/v1/product")
        public ResponseDto<ProductDto> createProduct(InsertProductDto request) {
                HttpSession session = request.getSession();
                SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");

                Long memberId = loggedInUser.getId();

                return new ResponseDto<>(productService.createProduct(request, memberId));
        }



        //전체 상품 데이터 가져오기
        @GetMapping(value = "/v1/product")
        public ResponseDto<List<ProductDto>> selectAllProduct(SelectProductDto request)  {
            return productService.selectAllProduct(request);
        }

        //판매글 수정하기
        @PutMapping(value = "/v1/product")
        public ResponseDto<ProductDto> updateProduct(UpdateProductDto productDto) {
                return new ResponseDto(productService.updateProduct(productDto));
        }

        //판매완료  status N->Y
        @PutMapping(value = "/v1/soldout")
        public ResponseDto<ProductDto> soldProduct(SoldProductDto productDto) {
                return new ResponseDto(productService.soldoutProduct(productDto));
        }


        //판매글 삭제하기
        @DeleteMapping(value = "/v1/product")
        public ResponseDto<ProductDto> deleteBoard(ProductDto productDto) {
                return new ResponseDto(productService.deleteProduct(productDto));
        }


}
