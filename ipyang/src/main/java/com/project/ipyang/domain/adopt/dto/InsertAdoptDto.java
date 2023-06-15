package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;

@Data
public class InsertAdoptDto extends BaseEntity {
    private Long member_id;
    private Long vaccine_id;
    private Long cat_id;
    private String title;
    private String content;
    private int view;
    private String name;
    private String gender;
    private String weight;
    private String age;
    private String neu;
    private String yn;
}
