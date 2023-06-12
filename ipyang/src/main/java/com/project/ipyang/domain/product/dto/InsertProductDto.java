package com.project.ipyang.domain.product.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class InsertProductDto extends BaseEntity {
    private String name;
    private String status;
    private int price;
    private String type;
    private String loc;

    private Long member_id;
//    private List<MultipartFile> imageFiles;
}
