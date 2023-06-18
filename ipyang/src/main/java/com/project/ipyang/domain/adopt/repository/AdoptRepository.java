package com.project.ipyang.domain.adopt.repository;

import com.project.ipyang.domain.adopt.entity.Adopt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptRepository extends JpaRepository<Adopt, Long> {
}
