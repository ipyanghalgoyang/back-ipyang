package com.project.ipyang.domain.product.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.product.dto.InsertProductDto;
import com.project.ipyang.domain.product.dto.InsertProduct_ImgDto;
import com.project.ipyang.domain.product.dto.ProductDto;
import com.project.ipyang.domain.product.dto.SelectProductDto;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.repository.ProductRepository;
import com.project.ipyang.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

        private final ProductService productService;

        @PostMapping(value = "/v1/product")
        public ResponseDto<ProductDto> createProduct(InsertProductDto request)  {

//                String img_context = "images"+File.separator+"product" + File.separator;
//                for (MultipartFile multipartFile : file1) {
//                        log.info("originalName: {}, img_context : {}",multipartFile.getOriginalFilename(),img_context);
//                        String img_stored_file = uploadFile(multipartFile.getOriginalFilename(), multipartFile.getBytes(),  img_context);
//
//
//                }

                return new ResponseDto(productService.createProduct(request));

        }

//        private String uploadFile(String originalName,byte[] fileData, String img_context) throws Exception {
//                UUID uid = UUID.randomUUID();
//                // requestPath = requestPath + "/resources/image";
//                log.info("img_context->" + img_context);
//
//                // Directory 생성
//                File fileDirectory = new File(img_context);
//                if (!fileDirectory.exists()) {
//                        fileDirectory.mkdirs();
//                        log.info("업로드용 폴더 생성" + img_context );
//
//                }
//                String img_stored_file = img_context + uid.toString() + "_" + originalName;
//                log.info("img_stored_file ->" + img_stored_file);
//                File target = new File(img_stored_file);
//
//                FileCopyUtils.copy(fileData,target);
//                return img_stored_file;
//        }
@GetMapping(value = "/v1/product")
public ResponseDto<ProductDto> selectAllProduct(SelectProductDto id) throws ChangeSetPersister.NotFoundException {

    return new ResponseDto(productService.selectAllProduct(id));
}


}
