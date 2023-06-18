package com.project.ipyang.domain.vaccine.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class VaccineDto {
    private Long id;
    private String name;

    public VaccineDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
