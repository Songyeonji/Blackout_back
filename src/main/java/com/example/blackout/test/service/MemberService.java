package com.example.blackout.test.service;

import org.springframework.stereotype.Service;
import com.example.blackout.test.dao.MemberDao;
import com.example.blackout.test.vo.Member;

@Service
public class MemberService {
    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    

    public void joinMember(Member member) {
        // 비밀번호 해싱 없이 바로 저장
        memberDao.insertMember(member);
    }
    
    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public boolean validateMemberCredentials(String loginId, String loginPw) {
        Member member = memberDao.getMemberByLoginId(loginId);
        return member != null && member.getLoginPw().equals(loginPw);
    }
}
