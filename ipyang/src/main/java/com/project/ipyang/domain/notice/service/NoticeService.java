package com.project.ipyang.domain.notice.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import com.project.ipyang.domain.notice.dto.WriteNoticeDto;
import com.project.ipyang.domain.notice.dto.NoticeDto;
import com.project.ipyang.domain.notice.entity.Notice;
import com.project.ipyang.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    //공지사항 작성
    @Transactional
    public ResponseDto createNotice(WriteNoticeDto request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Notice notice = Notice.builder()
                                        .title(request.getTitle())
                                        .content(request.getContent())
                                        .category(request.getCategory())
                                        .member(member)
                                        .build();

        Long savedId = noticeRepository.save(notice).getId();

        if(savedId != null) return new ResponseDto(notice.convertWriteDto(memberId), HttpStatus.OK.value());
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}
