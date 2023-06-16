package com.project.ipyang.domain.member.service;


import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.dto.SelectMemberDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.entity.MemberRoleType;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .member_role(MemberRoleType.USER)
                .address(memberDto.getAddress())
                .point(String.valueOf(memberDto.getPoint()))
                .build();
        memberRepository.save(member);
        return new MemberDto();
    }

    public List<MemberDto> selectAllMember(SelectMemberDto selectMemberDto) {

        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(member -> MemberDto.builder()
                                .id(member.getId())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .name(member.getName())
                                .phone(member.getPhone())
                                .member_role(member.getMember_role())
                                .address(member.getAddress())
                                .point(member.getPoint())
                                .img_context(member.getImg_context())
                                .img_stored_file(member.getImg_stored_file())
                                .build()

//                        new MemberDto(
//                        member.getId(),
//                        member.getEmail(),
//                        member.getNickname(),
//                        member.getPasswd(),
//                        member.getName(),
//                        member.getPhone(),
//                        member.getMember_role(),
//                        member.getAddress(),
//                        member.getPoint(),
//                        member.getImg_context(),
//                        member.getOriginal_file(),
//                        member.getImg_stored_file()

                )
                .collect(Collectors.toList());
    }

    public ResponseDto checkDuplicateEmail(String email) {

        boolean isDuplicate = memberRepository.existsByEmail(email);
        if (isDuplicate) {

            return ResponseDto.ERROR("중복된 이메일입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
           // return new ResponseDto()
        }
        return null;

//        - (성공 시) 코드 값 200
//                - (중복 또는 실패 시) 코드 값 500
//                - 결과에 따른 메시지
    }
}
