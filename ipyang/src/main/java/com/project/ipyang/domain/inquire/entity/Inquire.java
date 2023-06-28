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

    @Column(name = "i_reply_yn")         //미답변 : 0, 답변 : 1
    private int replyYn;

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
                                    .replyYn(replyYn)
                                    .replyContent(replyContent)
                                    .member(member)

                                    .inquireImgDtos(convertImgDto())
                                    .build();
    }

    public WriteInquireDto convertWriteDto(Long memberId) {
        return new WriteInquireDto().builder()
                .title(title)
                .content(content)
                .passwd(passwd)
                .replyYn(0)
                .memberId(memberId)
                .inquireImgDtos(convertImgDto())
                .build();
    }

    public SelectInquireDto convertSelectDto() {
        return new SelectInquireDto().builder()
                                                .id(id)
                                                .memberId(member.getId())
                                                .title(title)
                                                .content(content)
                                                .replyContent(replyContent)
                                                .inquireImgDtos(convertImgDto())
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


    //패스워드 일치 여부 검증
    public boolean isPasswordMatch(String inputPasswd) {
        return passwd.equals(inputPasswd);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void replyUpdate(String inputReplyContent) {
        this.replyContent = inputReplyContent;
        this.replyYn = 1;
    }





}
