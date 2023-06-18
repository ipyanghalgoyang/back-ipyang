package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.domain.inquire.entity.Inquire_Img;
import com.project.ipyang.domain.member.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor

public class InquireDto {
    private Long id;
    private String title;
    private String content;
    private int passwd;               //비밀번호 숫자4자리로만 받기
    private int reply_yn;
    private String  reply_content;
    private String  common_inquire;
    private Member member;
    private List<Inquire_Img> inquire_imgs = new ArrayList<>();



}
