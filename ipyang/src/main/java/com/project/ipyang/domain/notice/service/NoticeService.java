package com.project.ipyang.domain.notice.service;

import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.notice.dto.InsertNoticeDto;
import com.project.ipyang.domain.notice.dto.NoticeDto;
import com.project.ipyang.domain.notice.entity.Notice;
import com.project.ipyang.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public NoticeDto createNotice(InsertNoticeDto noticeDto) {

        Member member = memberRepository.findById(noticeDto.getMemberId()).get();

        Notice notice = Notice.builder().title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .commonNotice(noticeDto.getCommonNotice())
                .member(member)
                .build();
        noticeRepository.save(notice);
        return new NoticeDto();

    }


}
