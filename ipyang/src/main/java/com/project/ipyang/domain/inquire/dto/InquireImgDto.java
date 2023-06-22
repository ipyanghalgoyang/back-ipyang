package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.domain.inquire.entity.Inquire;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class InquireImgDto {
    private Long id;
    private String imgOriginFile;
    private String imgStoredFile;
    private Inquire inquire;
}
