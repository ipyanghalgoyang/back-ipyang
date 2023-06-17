package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;

@Data
public class InsertBoardDto  {
    private String title;
    private String content;
    private int view_cnt;
    private int like_cnt;
    private String common_board;
    private int ref;   //글그룹
    private int re_step;  //들여쓰기
    private int re_level;//1이 게시글 2가 댓글
    private Long member_id;

}
