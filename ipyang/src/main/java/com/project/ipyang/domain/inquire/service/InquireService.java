package com.project.ipyang.domain.inquire.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.inquire.dto.InquireDto;
import com.project.ipyang.domain.inquire.dto.InsertInquireDto;
import com.project.ipyang.domain.inquire.dto.SelectInquireDto;
import com.project.ipyang.domain.inquire.entity.Inquire;
import com.project.ipyang.domain.inquire.repository.InquireRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    //특정 문의글 조회
    @Transactional
    public ResponseDto<SelectInquireDto> selectInquire(Long id, String passwd) {
        Inquire inquire = inquireRepository.findById(id).orElseThrow(()->new IllegalArgumentException("문의글이 존재하지 않습니다."));

        if(inquire != null && inquire.getPasswd().equals(passwd)) {
            SelectInquireDto selectInquireDto = inquire.convertSelectDto();
            return new ResponseDto(selectInquireDto, HttpStatus.OK.value());

        } else return new ResponseDto("비밀번호가 일치하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }


    //특정 문의글 삭제
    /*@Transactional
    public ResponseDto deleteInquire(Long id) {
        inquireRepository.deleteById(id);
    }*/
}
