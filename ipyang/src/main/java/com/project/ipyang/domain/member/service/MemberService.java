package com.project.ipyang.domain.member.service;


import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.dto.SelectMemberDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;

    public MemberDto createMember(InsertMemberDto memberDto) {

        Member member = Member.builder().name(memberDto.getName())
                .email(memberDto.getEmail())
                .nickname(memberDto.getNickname())
                .passwd(memberDto.getPasswd())
                .name(memberDto.getName())
                .phone(memberDto.getPhone())
                .common_role(memberDto.getCommon_role())
                .address(memberDto.getAddress())
                .point(String.valueOf(memberDto.getPoint()))
                .build();
        memberRepository.save(member);
        return new MemberDto();
    }

    public List<MemberDto> selectAllMember(SelectMemberDto selectMemberDto) {

        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(member -> new MemberDto(
                        member.getId(),
                        member.getEmail(),
                        member.getNickname(),
                        member.getPasswd(),
                        member.getName(),
                        member.getPhone(),
                        member.getCommon_role(),
                        member.getAddress(),
                        member.getPoint(),
                        member.getImg_context(),
                        member.getOriginal_file(),
                        member.getImg_stored_file()

                ))
                .collect(Collectors.toList());
    }
}
