package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SelectBoardDto {
    private Long id;
    private String title;
    private String content;
    private int viewCnt;
    private int likeCnt;
    private IpyangEnum.BoardCategory category;
    private long memberId;
    private LocalDateTime createdAt;
    public SelectBoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCnt = board.getViewCnt();
        this.likeCnt = board.getLikeCnt();
        this.category = board.getCategory();
        this.memberId = board.getMember().getId();
        this.createdAt = board.getCreatedAt();
    }



}
