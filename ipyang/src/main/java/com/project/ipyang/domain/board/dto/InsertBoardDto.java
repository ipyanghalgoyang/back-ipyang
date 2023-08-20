package com.project.ipyang.domain.board.dto;

import com.project.ipyang.common.IpyangEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class InsertBoardDto  {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private List<MultipartFile> boardFile; // save.html -> Controller 파일 담는 용도
    private IpyangEnum.BoardCategory category;

}
