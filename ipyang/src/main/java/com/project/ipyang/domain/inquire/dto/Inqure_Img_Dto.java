package com.project.ipyang.domain.inquire.dto;

import com.project.ipyang.domain.inquire.entity.Inquire;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@RequiredArgsConstructor

public class Inqure_Img_Dto {
    private Long id;


    private String original_file;

    private String stored_file;


    private Inquire inquire;
}
