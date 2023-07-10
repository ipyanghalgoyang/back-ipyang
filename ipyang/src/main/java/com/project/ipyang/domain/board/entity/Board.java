package com.project.ipyang.domain.board.entity;


import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.board.dto.DeleteBoardDto;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Board extends BaseEntity {   //공유하기 제보하기  홍보하기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "b_title")
    private String title;

    @Column(name = "b_content")
    private String content;

    @Column(name = "b_view_cnt")
    private int viewCnt;

    @Column(name = "b_like_cnt")
    private int likeCnt;

    @Column(name = "b_category")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.BoardCategory category;

    @Column(name = "b_ref")
    private int ref;

     @Column(name = "b_re_step")
    private int reStep;

     @Column(name = "b_re_level")
    private int reLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<BoardImg> boardImgs = new ArrayList<>();


    public BoardDto convertUpdateDto() {
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .viewCnt(viewCnt)
                .likeCnt(likeCnt)
                .category(category)
                .ref(ref)
                .reStep(reStep)
                .reLevel(reLevel)
                .memberId(member)
                .build();
    }
    public SelectBoardDto convertSelectDto() {
        return SelectBoardDto.builder()
                                     .id(id)
                                     .title(title)
                                     .content(content)
                                     .viewCnt(viewCnt)
                                     .likeCnt(likeCnt)
                                     .category(category)
                                    .memberId(member.getId())
                                    .createdAt(getCreatedAt())
                                     .build();
    }

    public DeleteBoardDto convertDelDto(){
        return DeleteBoardDto.builder()
                .id(id)
                .build();
    }

public void UpdateBoard(String title,String content){
 this.title = title;
 this.content = content;
}


    public void updateViewCnt(int viewCnt) {
        this.viewCnt = viewCnt +1;
    }
}
