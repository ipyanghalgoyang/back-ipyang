package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.domain.adopt.entity.AdoptImg;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import com.project.ipyang.domain.member.entity.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AdoptDto {
    private Long id;
    private String title;
    private String content;
    private int view;
    private String name;
    private String gender;
    private String weight;
    private String age;
    private String neu;
    private String yn;
    private Member member;
    private Vaccine vaccine;
    private CatType catType;
    private List<AdoptImg> adoptImgs = new ArrayList<>();

    public AdoptDto(Long id, String title, String content, int view,
                    String name, String gender, String weight,
                    String age, String neu, String yn,
                    Member member, Vaccine vaccine,
                    CatType catType, List<AdoptImg> adoptImgs) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.neu = neu;
        this.yn = yn;
        this.member = member;
        this.vaccine = vaccine;
        this.catType = catType;
        this.adoptImgs = adoptImgs;
    }
}
