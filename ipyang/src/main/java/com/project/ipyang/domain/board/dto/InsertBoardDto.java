package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Data
public class InsertBoardDto  {
    private String title;
    private String content;

    private List<MultipartFile> boardFile; // save.html -> Controller 파일 담는 용도
    private List<String> imgOriginFile; // 원본 파일 이름
    private List<String> imgStoredFile; // 서버 저장용 파일 이름
   // private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

}
