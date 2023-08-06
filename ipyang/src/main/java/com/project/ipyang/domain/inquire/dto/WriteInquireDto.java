package com.project.ipyang.domain.inquire.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class WriteInquireDto {
    private String title;
    private String content;
    private String passwd;
    private IpyangEnum.Status status;
    private Long memberId;
    private List<InquireImgDto> inquireImgDtos = new ArrayList<>();

}
