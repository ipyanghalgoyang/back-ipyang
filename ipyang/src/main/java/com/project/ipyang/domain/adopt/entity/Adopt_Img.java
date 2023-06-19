package com.project.ipyang.domain.adopt.entity;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adopt_Img extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "adopt_id")
    private Adopt adopt;

    @Column(name = "img_original_file")
    private String originFileName;

    @Column(name = "img_stored_file")
    private String storedFileName;
}
