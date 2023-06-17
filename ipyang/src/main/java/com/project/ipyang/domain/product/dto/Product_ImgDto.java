package com.project.ipyang.domain.product.dto;

import com.project.ipyang.domain.product.entity.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product_ImgDto {
    private String original_file;
    private String stored_file;
    private Long product_id;
}
