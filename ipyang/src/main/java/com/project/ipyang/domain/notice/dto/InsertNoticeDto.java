package com.project.ipyang.domain.notice.dto;

import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.notice.entity.Notice_Img;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InsertNoticeDto {
    private Long id;
    private String common_notice;
    private String title;
    private String content;
    private Long   member_id;
    private List<Notice_Img> notice_imgs = new ArrayList<>();
}
