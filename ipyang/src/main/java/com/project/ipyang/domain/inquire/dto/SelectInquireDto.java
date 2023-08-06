package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.domain.inquire.entity.Inquire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SelectInquireDto {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private String replyContent;
    private List<InquireImgDto> inquireImgDtos = new ArrayList<>();
    private LocalDateTime createdAt;

    public SelectInquireDto(Inquire inquire) {
        this.id = inquire.getId();
        this.memberId = inquire.getMember().getId();
        this.title = inquire.getTitle();
        this.content = inquire.getContent();
        this.replyContent = inquire.getReplyContent();
        this.inquireImgDtos = inquire.convertImgDto();
        this.createdAt = inquire.getCreatedAt();
    }
}
