package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.adopt.entity.Adopt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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
    private int viewCnt;
    private String name;
    private String gender;
    private String weight;
    private String age;
    private String neu;
    private IpyangEnum.Status status;
    private List<AdoptImgDto> adoptImgs = new ArrayList<>();
    private Long memberId;
    private Long vacId;
    private Long catId;
    private LocalDateTime createdAt;

    public SelectAdoptDto(Adopt adopt) {
        this.id = adopt.getId();
        this.title = adopt.getTitle();
        this.content = adopt.getContent();
        this.viewCnt = adopt.getViewCnt();
        this.name = adopt.getName();
        this.gender = adopt.getGender();
        this.weight = adopt.getWeight();
        this.age = adopt.getAge();
        this.neu = adopt.getNeu();
        this.status = adopt.getStatus();
        this.adoptImgs = adopt.convertImgDto();
        this.memberId = adopt.getMember().getId();
        this.vacId = adopt.getVaccine().getId();
        this.catId = adopt.getCatType().getId();
        this.createdAt = adopt.getCreatedAt();

    }

}
