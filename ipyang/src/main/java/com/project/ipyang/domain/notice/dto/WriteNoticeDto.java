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
import org.springframework.web.multipart.MultipartFile;

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
    private List<MultipartFile> noticeFile;
    private List<String> imgOriginFile;   //원본 파일 이름
    private List<String> imgStoredFile;   //서버에 저장된 파일 이름
}
