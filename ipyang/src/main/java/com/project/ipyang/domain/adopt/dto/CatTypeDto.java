package com.project.ipyang.domain.adopt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatTypeDto {
    private Long id;
    private String type;
    private String trait;

    public CatTypeDto(Long id, String type, String trait) {
        this.id = id;
        this.type = type;
        this.trait = trait;
    }
}
