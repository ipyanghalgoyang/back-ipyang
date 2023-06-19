package com.project.ipyang.domain.product.entity;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.product.dto.SelectProductDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "p_name")
    private String name;

    @Column(name = "p_status")
    private String status;

    @Column(name = "p_price")
    private int price;

    @Column(name = "p_type")
    private String type;

    @Column(name = "p_loc")
    private String loc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "product")
    private List<Product_Img> product_imgs = new ArrayList<>();

    public SelectProductDto convertDto() {
        return SelectProductDto.builder()
                                        .id(id)
                                        .name(name)
                                        .status(status)
                                        .price(price)
                                        .type(type)
                                        .loc(loc)
                                        .memberId(member.getId())
                                        .build();
    }

}
