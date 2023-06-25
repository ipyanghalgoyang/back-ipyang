package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.domain.inquire.entity.Inquire;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class InquireImgDto {
    private Long id;
    private String imgOriginFile;
    private String imgStoredFile;
}
