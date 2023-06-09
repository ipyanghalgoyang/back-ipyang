package com.project.ipyang.domain.member.repository;

import com.project.ipyang.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
