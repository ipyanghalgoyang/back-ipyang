package com.project.ipyang.main.controller;

import com.project.ipyang.main.dto.SelectTotalDto;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.main.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /*
    * 전체 게시판 게시글, 상품, 입양글 데이터 조회
    * ------------------------------------------
    * 기본 설정   |  데이터를 15개씩 최신순으로 조회
    * sort 조건  |  id
    * 조회 예시   |  /v2/main?size=5&page=2
    * */
    @GetMapping(value = "/v1/main")
    public ResponseDto getTotal(@RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "15") int size,
                                @RequestParam(required = false, defaultValue = "id") String sort) {
        int pageNo = page <=1 ? 0 : page - 1;
        return mainService.getTotal(pageNo, size, sort);
    }

}
