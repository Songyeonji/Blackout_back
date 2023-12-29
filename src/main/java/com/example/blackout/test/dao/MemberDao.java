package com.example.blackout.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.blackout.test.vo.Member;

@Mapper
public interface MemberDao {
    @Select("SELECT * FROM member WHERE loginId = #{loginId}")
    Member getMemberByLoginId(String loginId);
    
    @Insert("INSERT INTO member (loginId, loginPw, name, email, regDate, alcoholLevel, delStatus) VALUES (#{loginId}, #{loginPw}, #{name}, #{email}, NOW(), #{alcoholLevel}, 0)")
    void insertMember(Member member);
}
