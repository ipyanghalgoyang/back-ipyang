package com.project.ipyang.domain.board.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.board.dto.InsertBoardDto;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.board.dto.UpdateBoardDto;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.board.service.BoardService;
import com.project.ipyang.domain.member.dto.UpdateMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final HttpServletRequest request;

    @PostMapping(value = "/v1/board")
    public ResponseDto<BoardDto> createBoard(InsertBoardDto request) {
        HttpSession session = request.getSession();
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");

        Long memberId = loggedInUser.getId();
        return new ResponseDto(boardService.createBoard(request,memberId));
    }


    //전체 게시판 게시글 가져오기
    @GetMapping(value = "/v1/board")
    public ResponseDto<List<BoardDto>> selectAllBoard(SelectBoardDto request) {
        return boardService.selectAllBoard(request);
    }

    //게시글 수정
    @PutMapping(value = "/v1/board")
    public ResponseDto<BoardDto> updateBoard(UpdateBoardDto boardDto) {
        return new ResponseDto(boardService.updateBoard(boardDto));
    }

    //게시글 삭제
    @DeleteMapping(value = "/v1/board")
    public ResponseDto<BoardDto> deleteBoard(BoardDto boardDto) {
        return new ResponseDto(boardService.deleteBoard(boardDto));
    }



}
