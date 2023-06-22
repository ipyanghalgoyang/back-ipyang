package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptImgDto extends BaseEntity {
    private Long id;
    private String imgOriginFile;
    private String imgStoredFile;

    public AdoptImgDto(Long id,
                         String imgOriginFile,
                         String imgStoredFile) {
        this.id = id;
        this.imgOriginFile = imgOriginFile;
        this.imgStoredFile = imgStoredFile;
    }

}
