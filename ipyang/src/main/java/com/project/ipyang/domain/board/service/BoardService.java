package com.project.ipyang.domain.board.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.board.dto.InsertBoardDto;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.board.dto.UpdateBoardDto;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardDto createBoard(InsertBoardDto boardDto,Long memberId) {

        Member member = memberRepository.findById(memberId).orElse(null);

        Board board = Board.builder().title(boardDto.getTitle())
                .content(boardDto.getContent())
                .viewCnt(boardDto.getViewCnt())
                .likeCnt(boardDto.getLikeCnt())
                .commonBoard(boardDto.getCommonBoard())
                .ref(boardDto.getRef())
                .reStep(boardDto.getReStep())
                .reLevel(boardDto.getReLevel())
                .member(member)
                .build();
        boardRepository.save(board);
        return new BoardDto();

    }

    //전체 게시판 게시글 가져오기
    public ResponseDto selectAllBoard(SelectBoardDto request) {
        List<Board> boards = boardRepository.findAll();
        List<SelectBoardDto> selectBoardDtos = boards.stream().map(SelectBoardDto::new).collect(Collectors.toList());

        if(!selectBoardDtos.isEmpty()) {
            return new ResponseDto(selectBoardDtos, HttpStatus.OK.value());
        } else return new ResponseDto("가져올 데이터가 없습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
    @Transactional
    public ResponseDto updateBoard(Long id,UpdateBoardDto request) {

        Optional<Board> boardOptional = boardRepository.findById(id);
        if (!boardOptional.isPresent()) {
            return new ResponseDto("존재하지 않는 게시글입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        boardOptional.get().UpdateBoard(request.getTitle(),request.getContent());

        return new ResponseDto("게시글이 업데이트되었습니다.", HttpStatus.OK.value());

    }


    public ResponseDto deleteBoard(BoardDto boardDto) {
        Optional<Board> boardOptional = boardRepository.findById(boardDto.getId());
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            boardRepository.delete(board);
            return new ResponseDto("글삭제 성공", HttpStatus.OK.value());
        }
        else {
            return new ResponseDto("글삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
