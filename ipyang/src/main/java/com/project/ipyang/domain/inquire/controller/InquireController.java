package com.project.ipyang.domain.inquire.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.inquire.dto.InquireDto;
import com.project.ipyang.domain.inquire.dto.InsertInquireDto;
import com.project.ipyang.domain.inquire.service.InquireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InquireController {
    private final InquireService inquireService;

    @PostMapping(value = "/v1/inquire")
    public ResponseDto<InquireDto>createInquire(InsertInquireDto request) {
        return new ResponseDto(inquireService.createInquire(request));
    }
}
