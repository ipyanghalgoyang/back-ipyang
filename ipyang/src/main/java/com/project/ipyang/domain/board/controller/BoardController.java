package com.project.ipyang.domain.board.controller;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.board.dto.InsertBoardDto;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping(value = "/v1/board")
    public ResponseDto<BoardDto> createBoard(InsertBoardDto request) {
        return new ResponseDto(boardService.createBoard(request));
    }


    //전체 게시판 게시글 가져오기
    @GetMapping(value = "/v1/board")
    public ResponseDto<List<BoardDto>> selectAllBoard(SelectBoardDto request) {
        return boardService.selectAllBoard(request);
    }


}
