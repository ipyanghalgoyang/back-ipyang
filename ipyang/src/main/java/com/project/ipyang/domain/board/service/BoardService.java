package com.project.ipyang.domain.board.service;

import com.project.ipyang.domain.board.dto.BoardDto;
import com.project.ipyang.domain.board.dto.InsertBoardDto;
import com.project.ipyang.domain.board.dto.SelectBoardDto;
import com.project.ipyang.domain.board.entity.Board;
import com.project.ipyang.domain.board.repository.BoardRepository;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardDto createBoard(InsertBoardDto boardDto) {

        Member member = memberRepository.findById(boardDto.getMember_id()) .get();

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
        return  new BoardDto();


    }

    public List<BoardDto> selectAllBoard(SelectBoardDto selectBoardDto) {

        List<Board> boardList = boardRepository.findAll();

        return boardList.stream()
                .map(board -> new BoardDto(
                        board.getId(),
                        board.getTitle(),
                        board.getContent(),
                        board.getView_cnt(),
                        board.getLike_cnt(),
                        board.getCommon_board(),
                        board.getRef(),
                        board.getRe_step(),
                        board.getRe_level(),
                        board.getMember().getId()
                ))
                .collect(Collectors.toList());


    }
}
