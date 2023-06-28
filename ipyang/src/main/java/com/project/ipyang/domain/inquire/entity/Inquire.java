package com.project.ipyang.domain.inquire.entity;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.inquire.dto.InquireImgDto;
import com.project.ipyang.domain.inquire.dto.SelectInquireDto;
import com.project.ipyang.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String passwd;               //비밀번호 숫자 4자리로만 받기

    @Column(name = "i_reply_yn")      //답변 여부 1 아니면 0
    private int replyYn;

    @Column(name = "i_reply_content")
    private String replyContent;

    @Column(name = "i_common_inquire")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.InquireCategory commonInquire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "inquire")
    private List<InquireImg> inquireImgs = new ArrayList<>();

    public SelectInquireDto convertSelectDto() {
        return new SelectInquireDto().builder()
                                                .id(id)
                                                .memberId(member.getId())
                                                .title(title)
                                                .content(content)
                                                .replyContent(replyContent)
                                                .commonInquire(commonInquire)
                                                .inquireImgDtos(convertImgDto())
                                                .build();
    }

    public List<InquireImgDto> convertImgDto() {
        if(inquireImgs.isEmpty()) return null;

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




}
