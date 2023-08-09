package com.project.ipyang.domain.notice.controller;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.notice.dto.SelectNoticeDto;
import com.project.ipyang.domain.notice.dto.UpdateNoticeDto;
import com.project.ipyang.domain.notice.dto.WriteNoticeDto;
import com.project.ipyang.domain.notice.dto.NoticeDto;
import com.project.ipyang.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final HttpSession session;

    //안내글 작성
    @PostMapping(value = "/v1/notice/{category}/write")
    public ResponseDto<WriteNoticeDto> createNotice(@PathVariable("category") IpyangEnum.NoticeCategory selectedCategory,
                                                    @RequestBody WriteNoticeDto request) {
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();

        return noticeService.createNotice(selectedCategory, request, memberId);
    }


    //카테고리별 안내글 조회
    @GetMapping(value = "/v1/notice/{category}")
    public ResponseDto<List<SelectNoticeDto>> selectAllNotice(@PathVariable("category") IpyangEnum.NoticeCategory selectedCategory) {
        return noticeService.selectAllNotice(selectedCategory);
    }


    //특정 안내글 조회
    @GetMapping(value = "/v1/notice/{id}")
    public ResponseDto<SelectNoticeDto> noticeDetail(@PathVariable("id") Long id) {
        return noticeService.selectNotice(id);
    }


    //특정 안내글 수정
    /*
    * 현재 이미지 수정은 제외하였음
    * */
    @PutMapping(value = "/v1/notice/{id}/edit")
    public ResponseDto updateNotice(@PathVariable("id") Long id,
                                    @RequestBody UpdateNoticeDto request) {
        return noticeService.updateNotice(id, request);
    }


    //특정 안내글 삭제
    @DeleteMapping(value = "/v1/notice/{id}/delete")
    public ResponseDto deleteNotice(@PathVariable("id") Long id) {
        return noticeService.deleteNotice(id);
    }


}
