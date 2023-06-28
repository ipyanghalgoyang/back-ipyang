package com.project.ipyang.domain.notice.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.notice.dto.WriteNoticeDto;
import com.project.ipyang.domain.notice.dto.NoticeDto;
import com.project.ipyang.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //공지사항 작성
    @PostMapping(value = "/v1/notice/write")
    public ResponseDto<WriteNoticeDto> createNotice(WriteNoticeDto request) {
        HttpSession session = request.getSession();
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();

        return noticeService.createNotice(request, memberId);
    }

}
