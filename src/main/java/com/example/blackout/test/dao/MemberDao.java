package com.example.blackout.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.blackout.test.vo.Member;

@Mapper
public interface MemberDao {
    @Select("SELECT * FROM member WHERE loginId = #{loginId}")
    Member getMemberByLoginId(String loginId);
}
