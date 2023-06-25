package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.common.IpyangEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SelectInquireDto {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private String replyContent;
    private IpyangEnum.InquireCategory commonInquire;
    private List<InquireImgDto> inquireImgDtos = new ArrayList<>();
}
