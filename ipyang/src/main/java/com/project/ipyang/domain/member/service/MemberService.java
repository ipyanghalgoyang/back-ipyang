package com.project.ipyang.domain.member.service;


import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.InsertMemberDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.dto.SelectMemberDto;
import com.project.ipyang.domain.member.dto.SignUpMemberDto;
import com.project.ipyang.domain.member.entity.Member;
import com.project.ipyang.domain.member.entity.MemberRoleType;
import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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

    //회원가입 시 유효성 검사
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return  validatorResult;
    }

    public boolean checkNickname(SignUpMemberDto signUpMemberDto) {
        return  memberRepository.existsByNickname(signUpMemberDto.getNickname());
    }

    //회원가입 정보 저장
    public void memberInfoSave(SignUpMemberDto signUpMemberDto, PasswordEncoder passwordEncoder) {
        Member member = signUpMemberDto.toEntity(passwordEncoder);
        Member savedMember = memberRepository.save(member);
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
}

