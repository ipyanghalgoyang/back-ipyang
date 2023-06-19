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

    @PostMapping(value = "/v1/adopt")
    public ResponseDto<AdoptDto> createAdopt(InsertAdoptDto request) {
        return new ResponseDto(adoptService.createAdopt(request));
    }

    @GetMapping(value = "/v1/getAdopt")
    public ResponseDto<List<SelectAdoptDto>> selectAdopt(SelectAdoptDto request) {
        List<SelectAdoptDto> selectAdoptDtos = adoptService.selectAdopt(request);
        return new ResponseDto<>(selectAdoptDtos);
    }
}
