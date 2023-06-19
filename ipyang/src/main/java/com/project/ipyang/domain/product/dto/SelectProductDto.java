package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.product.entity.Product;
import com.project.ipyang.domain.product.entity.Product_Img;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SelectProductDto  {
    private Long id;
    private String name;
    private String status;
    private int price;
    private String type;
    private String loc;
    private Long memberId;
    private List<Product_Img> imageFiles;

    public SelectProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.status = product.getStatus();
        this.price = product.getPrice();
        this.type = product.getType();
        this.loc = product.getLoc();
        this.memberId = product.getMember().getId();
    }
}