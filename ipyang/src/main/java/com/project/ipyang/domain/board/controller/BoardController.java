package com.project.ipyang.domain.board.controller;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.config.SessionService;
import com.project.ipyang.config.SessionUser;
import com.project.ipyang.domain.board.dto.*;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.board.service.BoardService;
import com.project.ipyang.domain.member.dto.UpdateMemberDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final SessionService sessionService;

    @ApiOperation(
            value = "게시물 작성"
            , notes = "사용자의 ID를 가지고  게시물을 작성한다.")
//    @PostMapping(value = "/v1/board/{category}/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseDto<BoardDto> createBoard(@PathVariable("category") IpyangEnum.BoardCategory sC,
//                                              @RequestPart(name = "file") MultipartFile file,
//                                           @ModelAttribute @RequestPart(name = "request") InsertBoardDto request,
//                                             HttpSession session) throws IOException {
//        System.out.println("BoardController.createBoard");
//        System.out.println( " request = " + request );
//
//        List<MultipartFile> imgFile = Collections.singletonList(file);
//        System.out.println("과연 전송이 됐을까? = " + imgFile);
//            request.setBoardFile(imgFile);
//
//
//        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
//        if (loggedInUser == null) {
//            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        }
//        Long memberId = loggedInUser.getId();
//        if (memberId == null) {
//            return new ResponseDto("존재하지 않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        }
//
//        return boardService.writeBoard(sC, request, memberId);
//    }

    @PostMapping(value = "/v1/board/{category}/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<BoardDto> createBoard(@PathVariable("category") IpyangEnum.BoardCategory sC,
                                             @RequestPart(name = "file") MultipartFile file,
                                             @RequestBody  InsertBoardDto request,
                                             HttpSession session) throws IOException {
        System.out.println("BoardController.createBoard");
        System.out.println( " request = " + request );

        List<MultipartFile> imgFile = Collections.singletonList(file);
        System.out.println("과연 전송이 됐을까? = " + imgFile);
            request.setBoardFile(imgFile);


        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지 않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return boardService.writeBoard(sC, request, memberId);
    }






//    @PostMapping(value = "/v1/board/{category}/write")
//    public ResponseDto<BoardDto> createBoard(@PathVariable("category")IpyangEnum.BoardCategory sC,
//                                           @RequestBody InsertBoardDto request,
//                                             HttpSession session) {
//
//      Long memberId = sessionService.validating(session);
//
//        return  boardService.writeBoard(sC,request,memberId);
//    }



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
    public ResponseDto<List<SelectBoardDto>> selectSomeBoard(@PathVariable ("category") IpyangEnum.BoardCategory sC
    /*,@PageableDefault(page = 1) Pageable pageable*/) {
       /* pageable.getPageNumber();*/


        return boardService.selectSomeBoard(sC/*,pageable*/);


    }

    @ApiOperation(
            value = "게시글 조회"
            , notes = "선택한 게시글 클릭시 게시글내용,댓글조회가능하고  조회수가 1증가한다.")
    @GetMapping(value = "/v1/board/read/{id}")
    public ResponseDto <List<ReadBoardDto>> readBoard(@PathVariable("id") Long id) {


        return new ResponseDto(boardService.readSomeBoard(id));
    }


    @ApiOperation(
            value = "게시글 수정"
            , notes = "선택한 게시글 제목과 내용을 수정한다.")
    @PutMapping(value = "/v1/board/{id}")
    public ResponseDto<BoardDto> updateBoard(@PathVariable("id")Long id,@RequestBody UpdateBoardDto request
                                                ,HttpSession session) {
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Long memberId = loggedInUser.getId();

        if (memberId == null) {
            return new ResponseDto("존재하지않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return boardService.updateBoard(id,request,memberId);
    }

    @ApiOperation(
            value = "게시글 삭제"
            , notes = "게시글과 게시글에있는 모든 댓글을 삭제한다")
    @DeleteMapping(value = "/v1/board/{id}")
    public ResponseDto deleteBoard(@PathVariable("id")Long id,HttpSession session) {
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return new ResponseDto(boardService.deleteBoard(id,memberId));
    }


    @ApiOperation(
            value = "게시글 좋아요버튼"
            , notes = "실행시키면 좋아요1증가 한번 더 누르면 좋아요 1감소")
    @PutMapping(value = "/v1/board/{id}/like")
    public ResponseDto likeBoard(@PathVariable("id")Long id,HttpSession session){
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        Long memberId = loggedInUser.getId();

        return new ResponseDto(boardService.likeBoard(id,memberId));

    }


    //===========================================================================
    @ApiOperation(
            value = "댓글 작성"
            , notes = "게시글에 댓글을 작성한다")
    @PostMapping(value = "/v1/comment/{id}")
    public ResponseDto writeComment(@PathVariable("id")Long id,@RequestBody InsertCommentDto request,
                                             HttpSession session) {

        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Long memberId = loggedInUser.getId();

        if (memberId == null) {
            return new ResponseDto("존재하지않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return  boardService.writeComment(id,request,memberId);
    }

    @PutMapping(value = "/v1/comment/{id}")
    public ResponseDto updateComment(@PathVariable("id")Long id,@RequestBody UpdateCommentDto request,HttpSession session){
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return  boardService.updateComment(id,request,memberId);
    }

    @DeleteMapping(value = "/v1/comment/{id}")
    public ResponseDto deleteComment(@PathVariable("id")Long id,HttpSession session){
        SessionUser loggedInUser = (SessionUser) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Long memberId = loggedInUser.getId();
        if (memberId == null) {
            return new ResponseDto("존재하지않는 회원입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return boardService.deleteComment(id,memberId);
    }







}
