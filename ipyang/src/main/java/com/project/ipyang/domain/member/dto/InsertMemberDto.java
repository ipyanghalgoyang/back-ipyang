package com.project.ipyang.domain.member.dto;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.Data;


@Data
public class InsertMemberDto extends BaseEntity {
    private String email;
    private String nickname;
    private String passwd;
    private String name;
    private String phone;
    private String common_role;
    private String address;
    private int    point;
//    private String img_original;
//    private String img_stored;

}
