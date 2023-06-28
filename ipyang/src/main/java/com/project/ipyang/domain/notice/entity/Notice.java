package com.project.ipyang.domain.notice.entity;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.notice.dto.NoticeImgDto;
import com.project.ipyang.domain.notice.dto.WriteNoticeDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "n_common_notice")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.NoticeCategory category;

    @Column(name = "n_title")
    private String title;

    @Column(name = "n_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "notice")
    private List<NoticeImg> noticeImgs = new ArrayList<>();


    public List<NoticeImgDto> convertImgDto() {
        if(noticeImgs == null || noticeImgs.isEmpty()) return Collections.emptyList();

        List<NoticeImgDto>  noticeImgDtoList = new ArrayList<>();
        NoticeImgDto noticeImgDto = null;

        for(NoticeImg noticeImg : noticeImgs) {
            noticeImgDtoList.add(noticeImgDto.builder()
                                                    .id(noticeImg.getId())
                                                    .imgOriginFile(noticeImg.getImgOriginFile())
                                                    .imgStoredFile(noticeImg.getImgStoredFile())
                                                    .build());

        }
        return noticeImgDtoList;
    }


    public WriteNoticeDto convertWriteDto(Long memberId) {
        return new WriteNoticeDto().builder()
                                            .category(category)
                                            .title(title)
                                            .content(content)
                                            .memberId(memberId)
                                            .noticeImgs(convertImgDto())
                                            .build();
    }
}
