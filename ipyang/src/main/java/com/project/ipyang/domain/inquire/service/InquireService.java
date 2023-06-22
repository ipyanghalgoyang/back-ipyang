package com.project.ipyang.domain.inquire.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.inquire.dto.InquireDto;
import com.project.ipyang.domain.inquire.dto.InsertInquireDto;
import com.project.ipyang.domain.inquire.entity.Inquire;
import com.project.ipyang.domain.inquire.repository.InquireRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class InquireService {
    private final InquireRepository inquireRepository;
    private final MemberRepository memberRepository;

    public InquireDto createInquire(InsertInquireDto inquireDto) {
        Member member = memberRepository.findById(inquireDto.getMemberId()) .get();

        Inquire inquire = Inquire.builder().title(inquireDto.getTitle())
                .content(inquireDto.getContent())
                .passwd(inquireDto.getPasswd())
                .replyYn(inquireDto.getReplyYn())
                .replyContent(inquireDto.getReplyContent())
                .commonInquire(inquireDto.getCommonInquire())
                .member(member)
                .build();
        inquireRepository.save(inquire);
        return new InquireDto();
    }

}
