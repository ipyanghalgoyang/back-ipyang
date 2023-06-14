package com.project.ipyang.domain.notice.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.notice.dto.InsertNoticeDto;
import com.project.ipyang.domain.notice.dto.NoticeDto;
import com.project.ipyang.domain.notice.repository.NoticeRepository;
import com.project.ipyang.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping(value = "/v1/notice")
    public ResponseDto<NoticeDto> createNotice(InsertNoticeDto request) {
        return new ResponseDto(noticeService.createNotice(request));
    }

}
