package com.project.ipyang.main.controller;

import com.project.ipyang.main.dto.SelectTotalDto;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    //전체 게시판 게시글, 상품, 입양글 데이터 가져오기
    @GetMapping(value = "/api/main")
    public ResponseDto<SelectTotalDto> selectTotal(SelectTotalDto request) {
        return mainService.selectTotal(request);
    }
}
