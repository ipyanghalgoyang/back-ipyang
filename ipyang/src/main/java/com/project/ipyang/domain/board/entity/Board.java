package com.project.ipyang.domain.board.entity;


import com.project.ipyang.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {   //공유하기 제보하기  홍보하기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "b_title")
    private String title;

    @Column(name = "b_content")
    private String content;

    @Column(name = "b_view_cnt")
    private int view_cnt;

    @Column(name = "b_like_cnt")
    private int like_cnt;

    @Column(name = "b_common_board")
    private String common_board;

    @Column(name = "b_ref")
    private int ref;

     @Column(name = "b_re_step")
    private int re_step;

     @Column(name = "b_re_level")
    private int re_level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board_img")
    private List<Board_Img> board_imgs = new ArrayList<>();






}
