package com.example.blackout.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.blackout.test.service.MemberService;
import com.example.blackout.test.vo.Member;

@Controller
@RequestMapping("/usr/member")
public class UsrMemberController {
    private final MemberService memberService;

    public UsrMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody Member member) {
        boolean isLoginSuccess = memberService.validateMemberCredentials(member.getLoginId(), member.getLoginPw());

        if (isLoginSuccess) {
            Member loggedInMember = memberService.getMemberByLoginId(member.getLoginId());
            return ResponseEntity.ok(loggedInMember);
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }
}
