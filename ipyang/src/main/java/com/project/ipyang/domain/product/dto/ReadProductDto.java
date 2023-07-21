package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.product.entity.ProductImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReadProductDto {
    private Long id;
    private String name;
    private String content;
    private IpyangEnum.Status status;
    private int price;
    private IpyangEnum.ProductType type;
    private String loc;
    private Long memberId;
    private List<ProductImg> imageFiles;
}