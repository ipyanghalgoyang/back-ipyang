package com.project.ipyang.domain.board.repository;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    List<Board> findByCategory(IpyangEnum.BoardCategory sC);

}
