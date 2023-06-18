package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adopt_Img_Dto extends BaseEntity {
    private Long id;
    private String originFileName;
    private String storedFileName;

    public Adopt_Img_Dto(Long id,
                         String originFileName,
                         String storedFileName) {
        this.id = id;
        this.originFileName = originFileName;
        this.storedFileName = storedFileName;
    }

}
