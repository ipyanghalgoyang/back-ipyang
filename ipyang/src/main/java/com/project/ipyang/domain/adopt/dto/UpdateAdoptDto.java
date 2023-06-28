package com.project.ipyang.domain.adopt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdoptDto {
    private String title;
    private String content;
    private String name;
    private String gender;
    private String weight;
    private String age;
    private String neu;
    private int yn;
    private Long vacId;
    private Long catId;
}
