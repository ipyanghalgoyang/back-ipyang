package com.project.ipyang.domain.board.service;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.board.dto.*;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.entity.Comment;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.board.repository.CommentRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public ResponseDto writeBoard(IpyangEnum.BoardCategory sC, InsertBoardDto boardDto, Long memberId) {

        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()) return new ResponseDto("로그인이 필요합니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

        Board board = Board.builder().title(boardDto.getTitle())
                .content(boardDto.getContent())
                .category(sC)
                .member(member.get())
                .build();
        Board writeBoard = boardRepository.save(board);
        if (writeBoard != null) {
            return new ResponseDto("게시글을 작성했습니다.", HttpStatus.OK.value());
        }else {
            return new ResponseDto("게시글을 작성 에러.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }


    }

    //전체 게시판 게시글 가져오기
    @Transactional
    public ResponseDto selectAllBoard( ) {
        List<Board> boards = boardRepository.findAll();
        List<SelectBoardDto> selectBoardDtos = boards.stream().map(SelectBoardDto::new).collect(Collectors.toList());

        if(!selectBoardDtos.isEmpty()) {
            return new ResponseDto(selectBoardDtos, HttpStatus.OK.value());
        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
    //카테고리별 글 조회
    @Transactional
    public ResponseDto<List<SelectBoardDto>> selectSomeBoard(IpyangEnum.BoardCategory sC) {
        List<Board> boards = boardRepository.findByCategory(sC);
        List<SelectBoardDto> selectBoardDtos = new ArrayList<>();

        if (!boards.isEmpty()){
            for (Board board : boards){
                SelectBoardDto selectBoardDto = board.convertSelectDto();
                selectBoardDtos.add(selectBoardDto);
            }
            return new ResponseDto(selectBoardDtos,HttpStatus.OK.value());

        }else
         return new ResponseDto("글이 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

}
    //선택한 글 조회
    @Transactional
    public ResponseDto<ReadBoardDto> readSomeBoard(Long id) {
            Optional<Board> board = boardRepository.findById(id);
            if (!board.isPresent()){
                return new ResponseDto("존재하지 않는 글입니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            Board findBoard = board.get();
            findBoard.updateViewCnt(findBoard.getViewCnt()); //조회수 증가
            SelectBoardDto readBoard = findBoard.convertSelectDto();


            List<Comment> comments = commentRepository.findByBoardId(id);
            List<SelectCommentDto>  readComment = comments.stream().map(SelectCommentDto::new).collect(Collectors.toList());


            ReadBoardDto readBoardDto = new ReadBoardDto(readBoard, readComment);

            return new ResponseDto(readBoardDto,HttpStatus.OK.value());

        }




    //게시글 수정
    @Transactional
    public ResponseDto updateBoard(Long id,UpdateBoardDto request,Long memberId) {

        Optional<Board> boardOptional = boardRepository.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseDto("존재하지 않는 게시글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        Board findBoard = boardOptional.get();

        if (memberId.equals(findBoard.getMember().getId())){
            findBoard.UpdateBoard(request.getTitle(),request.getContent());
            return new ResponseDto("게시물 수정 성공.", HttpStatus.OK.value());
        }
        else {
            return new ResponseDto("게시글 작성자만 삭제가능합니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }



    @Transactional
    public ResponseDto deleteBoard(Long id,Long memberId ) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseDto("존재하지 않는 게시글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Board board = boardOptional.get();
        List<Comment> commentOptional = commentRepository.findByBoardId(id);

        if (memberId.equals(board.getMember().getId())){
            for (Comment comment : commentOptional) {
                System.out.println(comment);
                commentRepository.delete(comment);
            }
            boardRepository.delete(board);
            return new ResponseDto("게시물 삭제 성공.", HttpStatus.OK.value());
        }
        else {
            return new ResponseDto("게시글 작성자만 삭제가능합니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

//===================댓글========================================
    @Transactional
    public ResponseDto writeComment(Long boardId,InsertCommentDto request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        Board board = boardRepository.findById(boardId).orElse(null);
        Comment comment = Comment.builder()
                .content(request.getContent())
                .reLevel(0)
                .member(member)
                .board(board)
                .build();
        Long id = comment.getId();  // comment의 id 값을 가져옴
        if (id != null && comment.getReLevel() == 0) {
            comment.setRef(Math.toIntExact(id));  // id가 null이 아니고 reLevel이 0인 경우에만 ref 필드에 id 값을 설정
        }

        Comment wrtieComment = commentRepository.save(comment);
        if (wrtieComment != null){
            return new ResponseDto("댓글을 작성했습니다.", HttpStatus.OK.value());
        }
        else {
                return new ResponseDto("댓글 작성 에러.", HttpStatus.INTERNAL_SERVER_ERROR.value());
               }
    }
    @Transactional
    public ResponseDto updateComment(Long id, UpdateCommentDto request, Long memberId) {

        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()){
            return new ResponseDto("존재하지않는 댓글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Comment findComment = commentOptional.get();
        if (memberId.equals(findComment.getMember().getId())){
                findComment.updateComment(request.getContent());
            return new ResponseDto("댓글수정성공.", HttpStatus.OK.value());
        }else {

            return new ResponseDto("댓글 작성자만 수정가능합니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Transactional
    public ResponseDto deleteComment(Long id, Long memberId) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()){
            return new ResponseDto("존재하지않는 댓글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        Comment findComment = commentOptional.get();

        if (memberId.equals(findComment.getMember().getId())){
           findComment.delete();
            return new ResponseDto("댓글삭제성공.", HttpStatus.OK.value());
        }else {

            return new ResponseDto("댓글 작성자만 삭제가능합니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }
}

