package com.project.ipyang.domain.adopt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class WriteAdoptDto extends BaseEntity {
    private Long memberId;
    private Long vaccineId;
    private Long catId;
    private String title;
    private String content;
    private int view;
    private String name;
    private String gender;
    private String weight;
    private String age;
    private String neu;
    private int yn;
    private List<AdoptImgDto> adoptImgDtos = new ArrayList<>();

    @JsonIgnore
    public HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getSession();
    }


}
