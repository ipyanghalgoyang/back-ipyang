package com.project.ipyang.domain.board.repository;

import com.project.ipyang.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    //메인 화면에 필요한 데이터 조회
    List<Board> findTop15ByOrderByCreatedAtDesc();
}
