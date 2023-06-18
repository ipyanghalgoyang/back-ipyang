package com.project.ipyang.domain.member.service;


import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.dto.SelectMemberDto;
import com.project.ipyang.domain.member.dto.UpdateMemberDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.entity.MemberRoleType;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


                )
                .collect(Collectors.toList());
    }

    public ResponseDto checkDuplicateEmail(String email) {

        boolean isDuplicate = memberRepository.existsByEmail(email);
        if (isDuplicate) {
            return new ResponseDto("중복된 이메일입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else {
            return new ResponseDto("중복되지 않은 이메일입니다.", HttpStatus.OK.value());
            }

    }
    public ResponseDto loginMember(String email, String passwd) {
        Member member = memberRepository.findByEmail(email);

        boolean isDuplicate = memberRepository.existsByEmail(email);

        if (isDuplicate) {

            if (member.getPasswd().equals(passwd)) {
                // 패스워드가 일치하면 로그인 성공으로 처리합니다.
                return new ResponseDto("로그인 되었습니다", HttpStatus.OK.value());
            } else {
                // 패스워드가 일치하지 않으면 로그인 실패로 처리합니다.
                return new ResponseDto("비밀번호가 일치하지 않습니다", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        else {

            return new ResponseDto("일치하는 회원이 없습니다" , HttpStatus.INTERNAL_SERVER_ERROR.value());

        }


    }

    public ResponseDto updateMember(UpdateMemberDto memberDto) {
        // 회원 정보를 데이터베이스에서 조회합니다.
        Optional<Member> memberOptional = memberRepository.findById(memberDto.getId());
        if (!memberOptional.isPresent()) {
            // 해당 회원이 존재하지 않는 경우 에러 응답을 반환합니다.
            return new ResponseDto("존재하지 않는 회원입니다.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        // 기존 회원 정보를 가져옵니다.
        Member member = memberOptional.get();
        MemberDto updateMember = member.convertDto();
        updateMember.setNickname(memberDto.getNickname());
        updateMember.setPasswd(memberDto.getPasswd());
        updateMember.setPhone(memberDto.getPhone());
        updateMember.setAddress(memberDto.getAddress());

        memberRepository.save(updateMember.toEntity());

        // 성공 응답을 반환합니다.
        return new ResponseDto("회원 정보가 업데이트되었습니다.", HttpStatus.OK.value());
    }
}
