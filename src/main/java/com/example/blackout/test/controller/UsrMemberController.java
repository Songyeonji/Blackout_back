package com.example.blackout.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.blackout.test.service.MemberService;
import com.example.blackout.test.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usr/member")
public class UsrMemberController {
    private final MemberService memberService;

    public UsrMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody Member member, HttpSession session) {
        boolean isLoginSuccess = memberService.validateMemberCredentials(member.getLoginId(), member.getLoginPw());
        if (isLoginSuccess) {
            Member loggedInMember = memberService.getMemberByLoginId(member.getLoginId());
            session.setAttribute("memberId", loggedInMember.getId());
            return ResponseEntity.ok(loggedInMember);
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @PostMapping("/doJoin")
    public ResponseEntity<Member> doJoin(@RequestBody Member member) {
        memberService.joinMember(member);
        return ResponseEntity.ok(member); // 회원 객체 반환
    }
    //아이디 중복체크
    @GetMapping("/isLoginIdAvailable")
    @ResponseBody
    public ResponseEntity<?> isLoginIdAvailable(String loginId) {
        boolean isAvailable = memberService.isLoginIdAvailable(loginId);
        return ResponseEntity.ok(isAvailable);
    }



    // 로그아웃 처리
    @GetMapping("/doLogout")
    public String doLogout(HttpSession session) {
        session.invalidate(); // 사용자 세션을 종료
        return "redirect:/"; // 홈 페이지나 로그인 페이지로 리다이렉트
    }

    @GetMapping("/myPage")
    @ResponseBody
    public ResponseEntity<?> getMemberInfo(HttpSession session) {
        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/doModify")
    @ResponseBody
    public ResponseEntity<?> doModify(@RequestBody Member member, HttpSession session) {
        Integer memberId = (Integer) session.getAttribute("memberId");
        if (memberId == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }
        member.setId(memberId);
        memberService.modifyMember(member);
        return ResponseEntity.ok("Member updated successfully");
    }
}
