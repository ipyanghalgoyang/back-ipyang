package com.project.ipyang.domain.adopt.repository;

import com.project.ipyang.domain.adopt.entity.Adopt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptRepository extends JpaRepository<Adopt, Long> {
    List<Adopt> findByMemberIdOrderByCreatedAtDesc(Long memberId);

    //메인 화면에 필요한 데이터 조회
    List<Adopt> findTop15ByOrderByCreatedAtDesc();
}
