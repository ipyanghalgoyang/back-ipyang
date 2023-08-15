package com.project.ipyang.domain.inquire.entity;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.inquire.dto.*;
import com.project.ipyang.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inquire extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquire_id")
    private Long id;

    @Column(name = "i_title")
    private String title;

    @Column(name = "i_content")
    private String content;

    @Column(name = "i_passwd")
    private String passwd;

    @Column(name = "i_reply_yn")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.Status status;

    @Column(name = "i_reply_content")
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "inquire")
    private List<InquireImg> inquireImgs = new ArrayList<>();

    public InquireDto convertDto() {
        return InquireDto.builder()
                                    .id(id)
                                    .title(title)
                                    .content(content)
                                    .passwd(passwd)
                                    .status(status)
                                    .replyContent(replyContent)
                                    .member(member)
                                    .inquireImgDtos(convertImgDto())
                                    .build();
    }


    public SelectInquireDto convertSelectDto() {
        return new SelectInquireDto().builder()
                                                .id(id)
                                                .email(member.getEmail())
                                                .nickName(member.getNickname())
                                                .title(title)
                                                .content(content)
                                                .replyContent(replyContent)
                                                .createdAt(getCreatedAt())
                                                .build();
    }


    public List<InquireImgDto> convertImgDto() {
        if(inquireImgs == null || inquireImgs.isEmpty()) return Collections.emptyList();

        List<InquireImgDto> inquireImgDtoList = new ArrayList<>();
        InquireImgDto inquireImgDto = null;

        for(InquireImg inquireImg : inquireImgs) {
            inquireImgDtoList.add(inquireImgDto.builder()
                                                        .id(inquireImg.getId())
                                                        .imgOriginFile(inquireImg.getImgOriginFile())
                                                        .imgStoredFile(inquireImg.getImgStoredFile())
                                                        .build());
        }
        return inquireImgDtoList;
    }


    //문의글 조회 시 패스워드 일치 여부 검증
    public boolean isPasswordMatch(String inputPasswd) {
        return passwd.equals(inputPasswd);
    }


    //문의글 작성 후 제목과 내용 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


    /*
    * 문의글에 관리자가 답변 추가
    * 답변이 추가된 순간 status 칼럼이 N → Y 로 변경됨
    * */
    public void replyUpdate(String inputReplyContent) {
        this.replyContent = inputReplyContent;
        this.status = IpyangEnum.Status.Y;
    }

}
