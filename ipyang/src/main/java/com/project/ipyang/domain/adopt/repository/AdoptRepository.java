package com.project.ipyang.domain.adopt.repository;

import com.project.ipyang.domain.adopt.entity.Adopt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptRepository extends JpaRepository<Adopt, Long> {
    List<Adopt> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}
