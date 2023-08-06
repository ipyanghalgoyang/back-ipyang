package com.project.ipyang.domain.notice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.notice.entity.NoticeImg;
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
public class WriteNoticeDto {
    private String title;
    private String content;
    private Long memberId;
    private List<NoticeImgDto> noticeImgs = new ArrayList<>();

    @JsonIgnore
    public HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getSession();
    }
}
