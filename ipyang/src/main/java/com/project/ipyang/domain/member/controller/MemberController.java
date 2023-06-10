package com.project.ipyang.domain.member.controller;

import com.project.ipyang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

}
