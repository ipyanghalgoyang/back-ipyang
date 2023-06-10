package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.product.entity.Product_Img;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
public class ProductDto   {
    private Long id;

    private String name;


    private String status;


    private int price;


    private String type;


    private String loc;


    private Member member;


    private List<Product_Img> product_imgs = new ArrayList<>();

    public ProductDto(Long id, String name, String status,
                      int price, String type, String loc,
                      Member member, List<Product_Img> product_imgs) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.type = type;
        this.loc = loc;
        this.member = member;
        this.product_imgs = product_imgs;
    }
}
