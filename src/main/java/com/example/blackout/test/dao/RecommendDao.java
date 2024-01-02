package com.example.blackout.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface RecommendDao {
    
    @Select("SELECT memberId FROM recommendPoint WHERE relTypeCode = 'article' AND relId = #{articleId}")
    List<Integer> getRecommendPointUsersByArticleId(int articleId);

    @Select("SELECT COUNT(*) FROM recommendPoint WHERE memberId = #{memberId} AND relTypeCode = #{relTypeCode} AND relId = #{relId}")
    boolean existsRecommendPoint(int memberId, String relTypeCode, int relId);

    @Insert("INSERT INTO recommendPoint (memberId, relTypeCode, relId) VALUES (#{memberId}, #{relTypeCode}, #{relId})")
    void insertRecommendPoint(int memberId, String relTypeCode, int relId);

    @Delete("DELETE FROM recommendPoint WHERE memberId = #{memberId} AND relTypeCode = #{relTypeCode} AND relId = #{relId}")
    void deleteRecommendPoint(int memberId, String relTypeCode, int relId);
}
