package com.project.ipyang.domain.notice.entity;

import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.notice.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice_Img extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name = "img_original_file")
    private String original_file;

    @Column(name = "img_stored_file")
    private String stored_file;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "notice_id")
    private Notice notice;
}
