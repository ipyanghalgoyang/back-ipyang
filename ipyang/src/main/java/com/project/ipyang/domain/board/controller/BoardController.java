package com.project.ipyang.domain.board.controller;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.board.dto.*;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.board.service.BoardService;
import com.project.ipyang.domain.member.dto.UpdateMemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(
            value = "게시물 작성"
            , notes = "사용자의 ID를 가지고  게시물을 작성한다.")
    @PostMapping(value = "/v1/board/{category}/write")
    public ResponseDto<BoardDto> createBoard(@PathVariable("category")IpyangEnum.BoardCategory sC,
                                           @RequestBody InsertBoardDto request,
                                             HttpSession session) {

        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");

        Long memberId = loggedInUser.getId();
        return  boardService.writeBoard(sC,request,memberId);
    }


    @ApiOperation(
            value = "전체게시물 조회"
            , notes = "게시판 전체글을 가져온다")
    @GetMapping(value = "/v1/board")
    public ResponseDto<List<SelectBoardDto>> selectAllBoard( ) {

        return boardService.selectAllBoard();
    }


    @ApiOperation(
            value = "카테고리별 게시글 조회"
            , notes = "제보하기 홍보하기 공유하기별로 조회한다.")
    @GetMapping(value = "/v1/board/{category}")
    public ResponseDto<List<SelectBoardDto>> selectSomeBoard(@PathVariable ("category") IpyangEnum.BoardCategory sC) {
        return boardService.selectSomeBoard(sC);
    }

    //선택한 게시글 조회(글리스트 보여주면서    //댓글 가져오기
    @GetMapping(value = "/v1/board/read/{id}")
    public ResponseDto <List<ReadBoardDto>> readBoard(@PathVariable("id") Long id) {


        return new ResponseDto(boardService.readSomeBoard(id));
    }


    //게시글 수정
    @PutMapping(value = "/v1/board/{id}")
    public ResponseDto<BoardDto> updateBoard(@PathVariable("id")Long id,@RequestBody UpdateBoardDto request) {
        return boardService.updateBoard(id,request);
    }

    //게시글 삭제
    @DeleteMapping(value = "/v1/board")
    public ResponseDto<BoardDto> deleteBoard(BoardDto boardDto) {
        return new ResponseDto(boardService.deleteBoard(boardDto));
    }


    //===========================================================================
    //댓글작성
    @PostMapping(value = "/v1/comment/{id}")
    public ResponseDto writeComment(@PathVariable("id")Long id,@RequestBody InsertCommentDto request,
                                             HttpSession session) {

        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");

        Long memberId = loggedInUser.getId();
        return  boardService.writeComment(id,request,memberId);
    }







}
