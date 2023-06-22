package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
    private IpyangEnum.BoardCategory commonBoard;
    private int ref;   //글그룹
    private int reStep;  //들여쓰기
    private int reLevel;//1이 게시글 2가 댓글
    private Long memberId;

    public SelectBoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.viewCnt = board.getViewCnt();
        this.likeCnt = board.getLikeCnt();
        this.commonBoard = board.getCommonBoard();
        this.ref = board.getRef();
        this.reStep = board.getReStep();
        this.reLevel = board.getReLevel();
        this.memberId = board.getMember().getId();
    }
}
