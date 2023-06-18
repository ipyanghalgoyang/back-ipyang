package com.project.ipyang.domain.member.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.dto.SelectMemberDto;
import com.project.ipyang.domain.member.dto.UpdateMemberDto;
import com.project.ipyang.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/v1/member")
    public ResponseDto<MemberDto> createMember(InsertMemberDto request) {
        return new ResponseDto(memberService.createMember(request));
    }



    @GetMapping(value = "/v1/member")
    public ResponseDto<MemberDto> selectAllMember(SelectMemberDto id) {
        return new ResponseDto(memberService.selectAllMember(id));
    }

    @GetMapping(value = "/v1/dup-email")
    public ResponseDto duplicateMember(@RequestParam String email) {


        return memberService.checkDuplicateEmail(email);
    }

    @GetMapping(value = "/v1/login")
    public ResponseDto loginMember(@RequestParam String email,@RequestParam String passwd) {

        return memberService.loginMember(email,passwd);
    }

    @PutMapping(value = "/v1/member")
    public ResponseDto<MemberDto> updateMember(UpdateMemberDto memberDto) {
        return new ResponseDto(memberService.updateMember(memberDto));
    }





}
