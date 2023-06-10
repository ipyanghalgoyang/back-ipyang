package com.project.ipyang.domain.adopt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineDto {
    private Long id;
    private String name;

    public VaccineDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
