package com.project.ipyang.domain.adopt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
@NoArgsConstructor
public class AdoptPageDto {
    private Page<AdoptDto> adoptDtos;
    private int startPage;
    private int endPage;

    public AdoptPageDto(Page<AdoptDto> adoptDtos, int startPage, int endPage) {
        this.adoptDtos = adoptDtos;
        this.startPage = startPage;
        this.endPage = endPage;
    }
}
