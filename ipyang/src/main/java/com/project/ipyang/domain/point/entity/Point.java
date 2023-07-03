package com.project.ipyang.domain.point.entity;


import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.common.entity.BaseEntity;
import com.project.ipyang.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @Column(name = "point_type")
    @Enumerated(EnumType.STRING)
    private IpyangEnum.PointType type;

    @Column(name = "point_history")
    private String pointHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
