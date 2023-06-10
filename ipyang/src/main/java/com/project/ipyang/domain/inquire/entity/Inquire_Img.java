package com.project.ipyang.domain.inquire.entity;

import com.project.ipyang.domain.inquire.entity.Inquire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Inquire_Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name = "img_original_file")
    private String original_file;

    @Column(name = "img_stored_file")
    private String stored_file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquire_id")
    private Inquire inquire;
}
