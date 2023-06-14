package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;

@Data
public class InsertInquireDto extends BaseEntity {
    private String title;
    private String content;
    private int passwd;
    private int reply_yn;
    private String reply_content;
    private String common_inquire;
    private Long member_id;

}
