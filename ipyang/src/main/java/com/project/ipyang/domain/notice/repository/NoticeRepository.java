package com.project.ipyang.domain.notice.repository;

import com.project.ipyang.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
