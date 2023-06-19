package com.project.ipyang.domain.board.service;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.board.dto.InsertBoardDto;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardDto createBoard(InsertBoardDto boardDto) {

        Member member = memberRepository.findById(boardDto.getMember_id()).get();

        Board board = Board.builder().title(boardDto.getTitle())
                .content(boardDto.getContent())
                .view_cnt(boardDto.getView_cnt())
                .like_cnt(boardDto.getLike_cnt())
                .common_board(boardDto.getCommon_board())
                .ref(boardDto.getRef())
                .re_step(boardDto.getRe_step())
                .re_level(boardDto.getRe_level())
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
}
