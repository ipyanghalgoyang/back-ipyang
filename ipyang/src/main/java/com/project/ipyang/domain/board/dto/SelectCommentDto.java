package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SelectCommentDto {
    private Long id;
    private String content;
    private int likeCnt;
    private long memberId;
    private long boardId;
    private LocalDateTime createdAt;


    public SelectCommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.likeCnt = comment.getLikeCnt();
        this.memberId = comment.getMember().getId();
        this.boardId = comment.getBoard().getId();
        this.createdAt = comment.getCreatedAt();
    }


}
