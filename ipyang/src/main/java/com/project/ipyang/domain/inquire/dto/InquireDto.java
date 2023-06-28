package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.domain.inquire.entity.InquireImg;
import com.project.ipyang.domain.member.entity.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class InquireDto {
    private Long id;
    private String title;
    private String content;
    private String passwd;
    private int replyYn;
    private String replyContent;
    private String commonInquire;
    private Member member;
    private List<InquireImg> inquireImgs = new ArrayList<>();



}
