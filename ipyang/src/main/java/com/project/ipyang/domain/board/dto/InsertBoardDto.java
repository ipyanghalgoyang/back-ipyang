package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Data
public class InsertBoardDto  {
    private String title;
    private String content;

}
