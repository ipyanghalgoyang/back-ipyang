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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    //안내글 작성
    @PostMapping(value = "/v1/notice/{category}/write",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto createNotice(@PathVariable("category") IpyangEnum.NoticeCategory selectedCategory,
                                    @ModelAttribute WriteNoticeDto request) throws IOException {
        return noticeService.createNotice(selectedCategory, request);
    }


    //카테고리별 안내글 목록 조회
    @GetMapping(value = "/v1/notice/category/{category}")
    public ResponseDto selectAllNotice(@PathVariable("category") IpyangEnum.NoticeCategory selectedCategory,
                                       @PageableDefault(page = 1) Pageable pageable) {
        return noticeService.selectAllNotice(selectedCategory, pageable);
    }


    //특정 안내글 조회
    @GetMapping(value = "/v1/notice/{id}")
    public ResponseDto noticeDetail(@PathVariable("id") Long id) {
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
