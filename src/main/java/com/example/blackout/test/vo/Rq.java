package com.example.blackout.test.vo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.blackout.test.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {

	   private final HttpSession session;
	    private final MemberService memberService;
	    private Member loginedMember;

    public Rq(HttpServletRequest req, MemberService memberService) {
        this.session = req.getSession();
        this.memberService = memberService;

        loadLoginedMember();
    }

    private void loadLoginedMember() {
        Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");

        if (loginedMemberId != null) {
            this.loginedMember = memberService.getMemberById(loginedMemberId);
        }
    }

    public boolean isLogined() {
        return this.loginedMember != null;
    }

    public boolean isNotLogined() {
        return !isLogined();
    }

    public int getLoginedMemberId() {
        if (isNotLogined()) {
            return -1;
        }

        return this.loginedMember.getId();
    }

    public Member getLoginedMember() {
        return this.loginedMember;
    }

    public void login(Member member) {
        session.setAttribute("loginedMemberId", member.getId());
        this.loginedMember = member;
    }

    public void logout() {
        session.removeAttribute("loginedMemberId");
        this.loginedMember = null;
    }
}

