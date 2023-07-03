package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.adopt.entity.AdoptImg;
import com.project.ipyang.domain.catType.entity.CatType;
import com.project.ipyang.domain.vaccine.entity.Vaccine;
import com.project.ipyang.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
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
    private IpyangEnum.Status status;
    private Member member;
    private Vaccine vaccine;
    private CatType catType;
    private List<AdoptImg> adoptImgs = new ArrayList<>();

}
