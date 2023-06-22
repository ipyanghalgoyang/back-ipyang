package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;

@Data
public class InsertBoardDto  {
    private String title;
    private String content;
    private int viewCnt;
    private int likeCnt;
    private IpyangEnum.BoardCategory commonBoard;
    private int ref;   //글그룹
    private int reStep;  //들여쓰기
    private int reLevel;//1이 게시글 2가 댓글
    private Long memberId;

}
