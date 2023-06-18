package com.project.ipyang.domain.board.repository;

import com.project.ipyang.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
