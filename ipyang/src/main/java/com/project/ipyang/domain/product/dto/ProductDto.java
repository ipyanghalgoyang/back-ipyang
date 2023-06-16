package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.product.entity.Product_Img;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto   {
    private Long id;
    private String name;
    private String status;
    private int price;
    private String type;
    private String loc;
    private Long member_id;
    private List<Product_Img> product_imgs = new ArrayList<>();





}
