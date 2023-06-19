package com.project.ipyang.domain.adopt.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.adopt.dto.AdoptDto;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.dto.InsertAdoptDto;
import com.project.ipyang.domain.adopt.service.AdoptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdoptController {

    private final AdoptService adoptService;

    //입양 게시글 데이터 삽입
    @PostMapping(value = "/v1/adopt")
    public ResponseDto<AdoptDto> createAdopt(InsertAdoptDto request) {
        return new ResponseDto(adoptService.createAdopt(request));
    }

    //전체 입양 게시글 가져오기
    @GetMapping(value = "/v1/getAdopt")
    public ResponseDto<List<SelectAdoptDto>> selectAllAdopt(SelectAdoptDto request) {
        return adoptService.selectAllAdopt(request);
    }
}
