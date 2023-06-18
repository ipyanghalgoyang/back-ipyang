package com.project.ipyang.domain.catType.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.catType.dto.CatTypeDto;
import com.project.ipyang.domain.catType.dto.InsertCatTypeDto;
import com.project.ipyang.domain.catType.service.CatTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CatTypeController {
    private final CatTypeService catTypeService;

    @PostMapping(value = "/v1/catType")
    public ResponseDto<CatTypeDto> createCatType(InsertCatTypeDto request) {
        return new ResponseDto(catTypeService.createCatType(request));
    }
}
