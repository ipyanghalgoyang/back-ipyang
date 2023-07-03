package com.project.ipyang.domain.inquire.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.inquire.dto.ReplyContentDto;
import com.project.ipyang.domain.inquire.dto.UpdateInquireDto;
import com.project.ipyang.domain.inquire.dto.WriteInquireDto;
import com.project.ipyang.domain.inquire.dto.SelectInquireDto;
import com.project.ipyang.domain.inquire.service.InquireService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InquireController {

    private final InquireService inquireService;
    private final PasswordEncoder passwordEncoder;

    //문의글 작성
    /*
    * 비밀번호 저장 시 암호화되는 기능 추가해야 함
    * */
    @PostMapping(value = "/v1/inquire/write")
    public ResponseDto<WriteInquireDto> createInquire(WriteInquireDto request, HttpSession session) {
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();

        return inquireService.createInquire(request, memberId);
    }

    //전체 문의글 조회
    @GetMapping(value = "/v1/inquire")
    public ResponseDto<List<SelectInquireDto>> selectAllInquire(SelectInquireDto request) {
        return inquireService.selectAllInquire(request);
    }


    //특정 문의글 조회
    @GetMapping(value = "v1/inquire/{id}")
    public ResponseDto<SelectInquireDto> inquireDetail(@PathVariable("id") Long id, @RequestParam String inputPasswd) {
        return inquireService.selectInquire(id, inputPasswd);
    }


    //특정 문의글 수정
    @PutMapping(value = "v1/inquire/{id}/edit")
    public ResponseDto updateInquire(@PathVariable("id") Long id, @RequestBody UpdateInquireDto request) {
        return inquireService.updateInquire(id, request);
    }


    //특정 문의글 삭제
    @DeleteMapping(value = "v1/inquire/{id}/delete")
    public ResponseDto deleteInquire(@PathVariable("id") Long id, @RequestParam String inputPasswd) {
        return inquireService.deleteInquire(id, inputPasswd);
    }


    //관리자 : 문의글에 답변
    @PutMapping(value = "v1/inquire/{id}/reply")
    public ResponseDto replyInquire(@PathVariable("id") Long id, @RequestBody ReplyContentDto request) {
        return inquireService.replyInquire(id, request);
    }



}
