package com.project.ipyang.domain.adopt.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.dto.WriteAdoptDto;
import com.project.ipyang.domain.adopt.service.AdoptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdoptController {

    private final AdoptService adoptService;
    private final HttpServletRequest httpServletRequest;

    //입양글 작성
    @PostMapping(value = "/v1/adopt/write")
    public ResponseDto<WriteAdoptDto> createAdopt(WriteAdoptDto request) {
        HttpSession session = request.getSession();
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();

        return adoptService.createAdopt(request, memberId);
    }

    //전체 입양글 조회
    @GetMapping(value = "/v1/adopt_list")
    public ResponseDto<List<SelectAdoptDto>> selectAllAdopt(SelectAdoptDto request) {
        return adoptService.selectAllAdopt(request);
    }

    //특정 입양글 조회
    @PostMapping(value = "/v1/adopt/{id}")
    public ResponseDto<SelectAdoptDto> adoptDetail(@PathVariable("id") Long id) {
        return adoptService.selectAdopt(id);
    }

    //특정 입양글 수정
    @PutMapping(value = "/v1/adopt/edit/{id}")
    public ResponseDto updateAdopt(@PathVariable("id") Long id, SelectAdoptDto request) {
        return adoptService.updateAdopt(id, request);
    }

    //특정 입양글 삭제
    @DeleteMapping(value = "v1/adopt/delete/{id}")
    public ResponseDto deleteAdopt(@PathVariable("id") Long id) {
        return adoptService.deleteAdopt(id);
    }
}
