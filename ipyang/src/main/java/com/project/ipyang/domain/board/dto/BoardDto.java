package com.project.ipyang.domain.board.dto;

import com.project.ipyang.domain.board.entity.Board_Img;
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

public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private int view_cnt;
    private int like_cnt;
    private String common_board;
    private int ref;
    private int re_step;
    private int re_level;
    private Member member;
    private Long member_id;
    private List<Board_Img> board_imgs = new ArrayList<>();

    public BoardDto(Long id, String title, String content, int view_cnt,
                    int like_cnt, String common_board, int ref, int re_step,
                    int re_level, Member member, List<Board_Img> board_imgs) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view_cnt = view_cnt;
        this.like_cnt = like_cnt;
        this.common_board = common_board;
        this.ref = ref;
        this.re_step = re_step;
        this.re_level = re_level;
        this.member = member;
        this.board_imgs = board_imgs;
    }

    public BoardDto(Long id, String title, String content, int viewCnt, int likeCnt,
                    String commonBoard, int ref, int reStep, int reLevel, Long member_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view_cnt = view_cnt;
        this.like_cnt = like_cnt;
        this.common_board = common_board;
        this.ref = ref;
        this.re_step = re_step;
        this.re_level = re_level;
        this.member_id = member_id;
    }
}
