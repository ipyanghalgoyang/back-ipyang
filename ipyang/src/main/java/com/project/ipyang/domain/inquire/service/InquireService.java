package com.project.ipyang.domain.inquire.service;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.inquire.dto.*;
import com.project.ipyang.domain.inquire.entity.Inquire;
import com.project.ipyang.domain.inquire.repository.InquireRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquireService {

    private final InquireRepository inquireRepository;
    private final MemberRepository memberRepository;

    //문의글 작성
    @Transactional
    public ResponseDto createInquire(WriteInquireDto request, Long memberId, PasswordEncoder passwordEncoder) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()) return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

        Inquire inquire = Inquire.builder()
                                        .title(request.getTitle())
                                        .content(request.getContent())
                                        .passwd(passwordEncoder.encode(request.getPasswd()))
                                        .status(IpyangEnum.Status.N)
                                        .member(member.get())
                                        .build();

        Long savedId = inquireRepository.save(inquire).getId();

        if(savedId != null) return new ResponseDto(inquire.convertWriteDto(memberId), HttpStatus.OK.value());
        else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
    

    //전체 문의글 조회
    @Transactional
    public ResponseDto<List<SelectInquireDto>> selectAllInquire() {
        List<Inquire> inquires = inquireRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        if(!inquires.isEmpty()) {
            List<SelectInquireDto> selectInquireDtos = inquires.stream().map(SelectInquireDto::new).collect(Collectors.toList());Collectors.toList();
            return new ResponseDto(selectInquireDtos, HttpStatus.OK.value());
        } else return new ResponseDto("데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    //특정 문의글 조회
    @Transactional
    public ResponseDto<SelectInquireDto> selectInquire(Long id, String inputPasswd) {
        Inquire inquire = inquireRepository.findById(id).orElseThrow(()->new IllegalArgumentException("문의글이 존재하지 않습니다."));

        if(inquire != null && inquire.getPasswd().equals(inputPasswd)) {
            SelectInquireDto selectInquireDto = inquire.convertSelectDto();
            return new ResponseDto(selectInquireDto, HttpStatus.OK.value());

        } else return new ResponseDto("비밀번호가 일치하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }


    //특정 문의글 수정
    /*
    * 현재 이미지 수정은 제외하였음
    * */
    @Transactional
    public ResponseDto updateInquire(Long id, UpdateInquireDto request) {
        Optional<Inquire> inquire = inquireRepository.findById(id);
        if(!inquire.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        if(inquire.get().isPasswordMatch(request.getPasswd())) {
            inquire.get().update(request.getTitle(), request.getContent());
            return new ResponseDto("수정되었습니다", HttpStatus.OK.value());
        }
        else return new ResponseDto("비밀번호가 일치하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }


    //특정 문의글 삭제
    @Transactional
    public ResponseDto deleteInquire(Long id, String inputPasswd) {
        Optional<Inquire> inquire = inquireRepository.findById(id);
        if(!inquire.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        if(inquire.get().isPasswordMatch(inputPasswd)) {
            inquireRepository.deleteById(id);
            return new ResponseDto("글이 삭제되었습니다", HttpStatus.OK.value());
        }
        else return new ResponseDto("비밀번호가 일치하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }


    //관리자 : 문의글에 답변
    @Transactional
    public ResponseDto replyInquire(Long id, ReplyContentDto request) {
        Optional<Inquire> inquire = inquireRepository.findById(id);
        if(!inquire.isPresent()) {
            return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        inquire.get().replyUpdate(request.getReplyContent());
        if(inquire.get().getStatus() == IpyangEnum.Status.Y) {
            return new ResponseDto("답변이 작성되었습니다", HttpStatus.OK.value());
        } else return new ResponseDto("에러가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


}
