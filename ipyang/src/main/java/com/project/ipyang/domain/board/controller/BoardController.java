package com.project.ipyang.domain.board.controller;

import com.project.ipyang.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private BoardRepository boardRepository;
}
