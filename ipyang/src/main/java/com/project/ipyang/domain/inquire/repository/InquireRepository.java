package com.project.ipyang.domain.inquire.repository;

import com.project.ipyang.domain.inquire.entity.Inquire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquireRepository extends JpaRepository<Inquire,Long> {
    List<Inquire> findByMemberIdOrderByCreatedAtDesc(Long memberId);
}
