package com.project.ipyang.domain.vaccine.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.vaccine.dto.InsertVaccineDto;
import com.project.ipyang.domain.vaccine.dto.VaccineDto;
import com.project.ipyang.domain.vaccine.service.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;

    @PostMapping(value = "/v1/vaccine")
    public ResponseDto<VaccineDto> createVaccine(InsertVaccineDto request) {
        return new ResponseDto(vaccineService.createVaccine(request));
    }
}
