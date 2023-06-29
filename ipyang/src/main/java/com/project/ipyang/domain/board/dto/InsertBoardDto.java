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
    private int viewCnt;
    private int likeCnt;
    private IpyangEnum.BoardCategory commonBoard;
    private int ref;   //글그룹
    private int reStep;  //들여쓰기
    private int reLevel;//1이 게시글 2가 댓글
    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


//    public HttpSession getSession() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//        return request.getSession();
//    }
}
