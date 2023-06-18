package com.project.ipyang.domain.catType.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;

@Data
public class InsertCatTypeDto extends BaseEntity {
    private String type;
}
