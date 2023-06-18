package com.project.ipyang.domain.member.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.dto.SelectMemberDto;
import com.project.ipyang.domain.member.dto.SignUpMemberDto;
import com.project.ipyang.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

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



    @GetMapping(value = "v1/member")
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

    //회원 가입창 이동
    @GetMapping(value = "/sign")
    public String goSign(@ModelAttribute SignUpMemberDto requestDto, Model model) {
        model.addAttribute("member", requestDto);
        return "추후 생성하는 회원 가입 페이지 이름";
    }


    //회원 가입 요청 처리
    @PostMapping(value = "/v1/sign")
    public String sign(@Valid @ModelAttribute("member") SignUpMemberDto requestDto, BindingResult errors, Model model) {

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
            return "추후 생성하는 회원 가입 페이지 이름";
        }

        memberService.memberInfoSave(requestDto, passwordEncoder);
        return "redirect:/v1/login";
    }

}
