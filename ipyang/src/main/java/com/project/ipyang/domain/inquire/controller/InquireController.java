package com.project.ipyang.domain.inquire.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.inquire.dto.WriteInquireDto;
import com.project.ipyang.domain.inquire.dto.SelectInquireDto;
import com.project.ipyang.domain.inquire.service.InquireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class InquireController {

    private final InquireService inquireService;
    private final HttpServletRequest httpServletRequest;

    //문의글 작성
    @PostMapping(value = "/v1/inquire/write")
    public ResponseDto<WriteInquireDto> createInquire(WriteInquireDto request) {
        return new ResponseDto(inquireService.createInquire(request));
    }

    //전체 문의글 조회


    //특정 문의글 조회
    @PostMapping(value = "v1/inquire/{id}")
    public ResponseDto<SelectInquireDto> inquireDetail(@PathVariable("id") Long id, @RequestParam String passwd) {
        return inquireService.selectInquire(id, passwd);
    }


    //특정 문의글 수정


    //특정 문의글 삭제
    /*@DeleteMapping(value = "v1/inquire/delete/{id}")
    public ResponseDto deleteInquire(@PathVariable("id") Long id) {
        return inquireService.deleteInquire(id);
    }*/
}
