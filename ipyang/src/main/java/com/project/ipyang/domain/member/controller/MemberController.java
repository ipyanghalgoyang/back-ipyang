package com.project.ipyang.domain.member.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.member.dto.*;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session; // HttpSession 객체를 주입받음

    @PostMapping(value = "/v1/member")
    public ResponseDto<MemberDto> createMember(InsertMemberDto request) {
        return new ResponseDto(memberService.createMember(request));
    }

    //전체 사용자 데이터 가져오기
    @GetMapping(value = "/v1/member")
    public ResponseDto<List<SelectMemberDto>> selectAllMember(SelectMemberDto request) {
        return memberService.selectAllMember(request);
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

    @PostMapping("/v1/login")
    public ResponseEntity loginMember(@RequestParam String email, @RequestParam String passwd) {
        ResponseDto response;

        // 회원 로그인 서비스 호출
        try {
            response = memberService.loginMember(email, passwd);
        }catch(Exception e ){
            log.info(e.getMessage());
            response = new ResponseDto("로그인에 실패하였습니다. 다시 시도해주세요.",HttpStatus.NOT_FOUND.value());
        }
        return new ResponseEntity<ResponseDto<?>>(response,HttpStatus.OK);
    }


    @GetMapping("/v1/afterlogin")
    public ResponseDto<MemberDto> getLoggedInMember(MemberDto memberDto) {
        ResponseDto loggedInMember = memberService.getLoggedInMember();
        // 필요한 경우 MemberDto로 변환하여 반환
        return loggedInMember;
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


    //회원탈퇴시 del_yn N->Y로 전환
    @PutMapping(value = "/v1/memberdel")
    public ResponseDto<MemberDto> deleteMember(DeleteMemberDto memberDto) {
        return new ResponseDto(memberService.deleteWait(memberDto));
    }
    //회원탈퇴
    @DeleteMapping(value = "/v1/member")
    public ResponseDto<MemberDto> deleteMember( MemberDto memberDto){
        return new ResponseDto (memberService.deleteMember(memberDto));
    }



}
