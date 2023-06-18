package com.project.ipyang.domain.notice.dto;

import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.notice.entity.Notice_Img;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor

public class NoticeDto {
    private Long id;
    private String common_notice;
    private String title;
    private String content;
    private Member member;
    private List<Notice_Img> notice_imgs = new ArrayList<>();
}
