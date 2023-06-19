package com.project.ipyang.domain.member.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.*;
import com.project.ipyang.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/v1/member")
    public ResponseDto<MemberDto> createMember(InsertMemberDto request) {
        return new ResponseDto(memberService.createMember(request));
    }

    @GetMapping(value = "/v1/member")
    public ResponseDto<MemberDto> selectAllMember(SelectMemberDto id) {
        return new ResponseDto(memberService.selectAllMember(id));
    }

    @PutMapping(value = "/v1/member")
    public ResponseDto<MemberDto> updateMember(UpdateMemberDto memberDto) {
        return new ResponseDto(memberService.updateMember(memberDto));
    }

    @GetMapping(value = "/v1/dup-email")
    public ResponseDto duplicateMember(@RequestParam String email) {
        return memberService.checkDuplicateEmail(email);
    }

    //닉네임 중복 확인
    @GetMapping(value = "/v1/dup-nickname")
    public ResponseDto duplicateNickname(@RequestParam String nickname) {
        return memberService.checkDuplicateNickname(nickname);
    }

    @GetMapping(value = "/v1/login")
    public ResponseDto loginMember(@RequestParam String email,@RequestParam String passwd) {
        return memberService.loginMember(email,passwd);
    }


    //회원 가입 요청
    @PostMapping(value = "/v1/sign")
    public ResponseDto signUp(@Valid @ModelAttribute("member") SignUpMemberDto requestDto, BindingResult errors, Model model) {
        //에러 처리
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                log.info("Object name: {}", error.getObjectName());
                log.info("Error message: {}", error.getDefaultMessage());
                log.info("Error codes: {}", Arrays.toString(error.getCodes()));
            }
            model.addAttribute("member", requestDto);
            Map<String, String> validatorResult = memberService.validateHandling(errors);

            for(String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
        }
        return memberService.memberInfoSave(requestDto, passwordEncoder);

    }




}
