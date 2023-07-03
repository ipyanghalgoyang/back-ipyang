package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class UpdateProductDto {
    private Long id;
    private String name;
    private int price;
    private String type;
    private String loc;
    private Long memberId;
    private IpyangEnum.Status status;


    public Product toEntity( ) {

        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .type(type)
                .loc(loc)
                .status(status)
                .member(Member.builder().id(memberId).build())
                .build();
    }

}
