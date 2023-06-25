package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;

@Data
public class InsertInquireDto extends BaseEntity {
    private String title;
    private String content;
    private String passwd;
    private int replyYn;
    private String replyContent;
    private IpyangEnum.InquireCategory commonInquire;
    private Long memberId;

}
