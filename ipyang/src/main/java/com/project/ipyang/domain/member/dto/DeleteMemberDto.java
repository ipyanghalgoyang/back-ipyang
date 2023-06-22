package com.project.ipyang.domain.member.dto;

import com.project.ipyang.common.IpyangEnum;
import com.project.ipyang.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class DeleteMemberDto {
    private Long id;
    private IpyangEnum.MemberDelYN delYn;

    public Member toEntity( ){
        return Member.builder()
                .id(id)
                .delYn(delYn)
                .build();
    }
}
