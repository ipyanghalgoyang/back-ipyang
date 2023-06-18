package com.project.ipyang.domain.member.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService  memberService;

    @PostMapping(value = "/v1/member")
    public ResponseDto<MemberDto> createMember(InsertMemberDto request) {
        return new ResponseDto(memberService.createMember(request));
    }

}
