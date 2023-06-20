package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.domain.adopt.entity.Adopt;
import com.project.ipyang.domain.adopt.entity.Adopt_Img;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SelectAdoptDto {
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
    private List<Adopt_Img> adopt_imgs = new ArrayList<>();
    private Long memberId;
    private Long vacId;
    private Long catId;

    public SelectAdoptDto(Adopt adopt) {
        this.id = adopt.getId();
        this.title = adopt.getTitle();
        this.content = adopt.getContent();
        this.view = adopt.getView();
        this.name = adopt.getName();
        this.gender = adopt.getGender();
        this.weight = adopt.getWeight();
        this.age = adopt.getAge();
        this.neu = adopt.getNeu();
        this.yn = adopt.getYn();
        this.memberId = adopt.getMember().getId();
        this.vacId = adopt.getVaccine().getId();
        this.catId = adopt.getCatType().getId();
    }
}
