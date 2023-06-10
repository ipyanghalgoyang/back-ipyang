package com.project.ipyang.domain.adopt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adopt_Img_Dto {
    private Long id;
    private String original_file;
    private String stored_file;

    public Adopt_Img_Dto(Long id,
                         String original_file,
                         String stored_file) {
        this.id = id;
        this.original_file = original_file;
        this.stored_file = stored_file;
    }

}
