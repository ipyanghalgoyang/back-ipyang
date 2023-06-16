package com.project.ipyang.domain.product.dto;

import com.project.ipyang.domain.product.entity.Product;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class InsertProduct_ImgDto {

    private String original_file;
    private String stored_file;
    private Long product_id;
}
