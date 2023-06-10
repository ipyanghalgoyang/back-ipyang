package com.project.ipyang.domain.inquire.entity;

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

public class Inquire {   //문의게시판
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquire_id")
    private Long id;

    @Column(name = "i_title")
    private String title;

    @Column(name = "i_content")
    private String content;

    @Column(name = "i_passwd")
    private int passwd;               //비밀번호 숫자4자리로만 받기

    @Column(name = "i_reply_yn")   //답변여부 1 아니면 0
    private int reply_yn;

    @Column(name = "i_reply_content")
    private String  reply_content;

    @Column(name = "i_common_inquire")  //공통테이블로 문의글 종류
    private String  common_inquire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "inquire_img")
    private List<Inquire_Img> inquire_imgs = new ArrayList<>();




}
