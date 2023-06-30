package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AdoptImgDto extends BaseEntity {
    private Long id;
    private String imgOriginFile;
    private String imgStoredFile;

}
