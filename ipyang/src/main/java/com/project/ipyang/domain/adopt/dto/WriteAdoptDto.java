package com.project.ipyang.domain.adopt.dto;

import com.project.ipyang.common.IpyangEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WriteAdoptDto {

    @NotEmpty(message = "제목을 입력해주십시오")
    private String title;

    @NotEmpty(message = "내용을 입력해주십시오")
    private String content;

    private Long memberId;
    private Long vacId;
    private Long catId;
    private int view;
    private String name;
    private String gender;
    private String weight;
    private String age;
    private String neu;
    private IpyangEnum.Status status;
    private List<AdoptImgDto> adoptImgDtos = new ArrayList<>();

}
