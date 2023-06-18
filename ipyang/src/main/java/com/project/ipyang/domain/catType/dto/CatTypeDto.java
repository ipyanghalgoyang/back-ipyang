package com.project.ipyang.domain.catType.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class CatTypeDto {
    private Long id;
    private String type;

    public CatTypeDto(Long id, String type) {
        this.id = id;
        this.type = type;
    }
}
