package com.project.ipyang.domain.adopt.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.adopt.dto.AdoptDto;
import com.project.ipyang.domain.adopt.dto.SelectAdoptDto;
import com.project.ipyang.domain.adopt.dto.WriteAdoptDto;
import com.project.ipyang.domain.adopt.service.AdoptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdoptController {

    private final AdoptService adoptService;

    //입양 게시글 데이터 삽입
    @PostMapping(value = "/v1/adopt/write")
    public ResponseDto<AdoptDto> createAdopt(WriteAdoptDto request) {
        return new ResponseDto(adoptService.createAdopt(request));
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
