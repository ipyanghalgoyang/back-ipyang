package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.entity.BoardImg;
import com.project.ipyang.domain.member.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private int viewCnt;
    private int likeCnt;
    private IpyangEnum.BoardCategory category;
    private int ref;
    private int reStep;
    private int reLevel;
    private Member memberId;
    private List<BoardImg> boardImgs = new ArrayList<>();



    public Board toEntity() {
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .viewCnt(viewCnt)
                .likeCnt(likeCnt)
                .category(category)
                .ref(ref)
                .reStep(reStep)
                .reLevel(reLevel)
                .member(memberId)
                .build();
    }



}
