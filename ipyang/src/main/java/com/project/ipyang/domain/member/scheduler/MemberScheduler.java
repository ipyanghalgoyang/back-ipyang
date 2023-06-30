package com.project.ipyang.domain.member.scheduler;

import com.project.ipyang.common.response.ResponseDto;
import com.project.ipyang.domain.member.dto.MemberDto;
import com.project.ipyang.domain.member.service.MemberService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//public class MemberScheduler {
//    private final MemberService memberService;
//
//    public MemberScheduler(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//
////    @Scheduled(fixedDelay = 30 * 24 * 60 * 60 * 1000) // 30일(30 * 24 * 60 * 60 * 1000밀리초) 후에 실행
////    public void deleteExpiredMembers() {
////        // 30일이 경과한 회원 목록을 조회
////        List<MemberDto> expiredMembers = memberService.getExpiredMembers();
////
////        // 회원을 삭제하는 로직을 수행
////        for (MemberDto member : expiredMembers) {
////            memberService.deleteMember(member);
////        }
//    }
//}
