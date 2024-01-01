package com.example.blackout.test.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.blackout.test.vo.Member;

@Mapper
public interface MemberDao {
    @Select("SELECT * FROM member WHERE loginId = #{loginId}")
    Member getMemberByLoginId(String loginId);
    
    @Insert("INSERT INTO member (loginId, loginPw, name, email, regDate, alcoholLevel, delStatus) VALUES (#{loginId}, #{loginPw}, #{name}, #{email}, NOW(), #{alcoholLevel}, 0)")
    void insertMember(Member member);
    
    @Select("SELECT COUNT(*) FROM member WHERE loginId = #{loginId}")
    int countByLoginId(String loginId);

    @Update("UPDATE member SET name = #{name}, email = #{email}, updateDate = NOW() WHERE id = #{id}")
    void updateMember(Member member);

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member getMemberById(int id);
}
