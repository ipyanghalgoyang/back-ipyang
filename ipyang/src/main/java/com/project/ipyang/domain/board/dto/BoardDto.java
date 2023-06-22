package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.board.entity.BoardImg;
import com.project.ipyang.domain.member.entity.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private int viewCnt;
    private int likeCnt;
    private IpyangEnum.BoardCategory commonBoard;
    private int ref;
    private int reStep;
    private int reLevel;
    private Member member;
    private Long memberId;
    private List<BoardImg> boardImgs = new ArrayList<>();

    public BoardDto(Long id, String title, String content, int viewCnt,
                    int likeCnt, IpyangEnum.BoardCategory commonBoard, int ref, int reStep,
                    int reLevel, Member member, List<BoardImg> boardImgs) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.commonBoard = commonBoard;
        this.ref = ref;
        this.reStep = reStep;
        this.reLevel = reLevel;
        this.member = member;
        this.boardImgs = boardImgs;
    }

    public BoardDto(Long id, String title, String content, int viewCnt, int likeCnt,
                    IpyangEnum.BoardCategory commonBoard, int ref, int reStep, int reLevel, Long memberId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.commonBoard = commonBoard;
        this.ref = ref;
        this.reStep = reStep;
        this.reLevel = reLevel;
        this.memberId = memberId;
    }
}
