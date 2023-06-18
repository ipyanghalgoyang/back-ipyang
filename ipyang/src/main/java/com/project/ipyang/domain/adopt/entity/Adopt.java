package com.project.ipyang.domain.adopt.entity;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.apply.entity.Apply;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Adopt extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adopt_id")
    private Long id;

    @Column(name = "a_title")
    private String title;

    @Column(name = "a_content")
    private String content;

    @Column(name = "a_view_cnt")
    private int view;

    @Column(name = "a_name")
    private String name;

    @Column(name = "a_gender")
    private String gender;

    @Column(name = "a_weight")
    private String weight;

    @Column(name = "a_age")
    private String age;

    @Column(name = "a_neu")
    private String neu;

    @Column(name = "a_adopted_yn")
    private String yn;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private CatType catType;

    @OneToMany(mappedBy = "adopt")
    @OrderColumn(name = "adopt_img_order")
    private List<Adopt_Img> adopt_imgs = new ArrayList<>();

    @OneToMany(mappedBy = "adopt")
    private List<Apply> applies = new ArrayList<>();


}
