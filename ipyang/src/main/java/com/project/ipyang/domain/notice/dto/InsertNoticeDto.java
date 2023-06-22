package com.project.ipyang.domain.notice.dto;

import com.project.ipyang.domain.notice.entity.NoticeImg;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InsertNoticeDto {
    private Long id;
    private String commonNotice;
    private String title;
    private String content;
    private Long   memberId;
    private List<NoticeImg> noticeImgs = new ArrayList<>();
}
