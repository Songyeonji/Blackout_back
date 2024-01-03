package com.example.blackout.test.dao;

import java.util.List;
import org.apache.ibatis.annotations.*;
import com.example.blackout.test.vo.Article;
import com.example.blackout.test.vo.Member;

@Mapper
public interface ArticleDao {

	@Insert("""
	        INSERT INTO article (title, body, boardId, memberId, regDate, updateDate)
	        VALUES (#{title}, #{body}, #{boardId}, #{memberId}, NOW(), NOW())
	        """)
	@Options(useGeneratedKeys=true, keyProperty="id")
	public int writeArticle(Article article);

	 @Select("SELECT * FROM article")
	    List<Article> getArticles();

    @Select("SELECT * FROM article WHERE id = #{id}")
    public Article getArticleById(int id);

    @Update("""
            UPDATE article
            SET title = #{title}, body = #{body}, boardId = #{boardId}, updateDate = NOW()
            WHERE id = #{id}
            """)
    public void modifyArticle(Article article);


    @Delete("DELETE FROM article WHERE id = #{id}")
    void deleteArticle(int id);

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member getMemberById(int id);
    
    @Update("""
            UPDATE article
            SET title = #{title}, body = #{body}, boardId = #{boardId}, updateDate = NOW()
            WHERE id = #{id}
            """)
    void updateArticle(Article article);
    
    @Select("""
            SELECT A.*, COUNT(R.memberId) as hitCount
            FROM article A
            LEFT JOIN recommendPoint R ON A.id = R.relId AND R.relTypeCode = 'article'
            GROUP BY A.id
            ORDER BY hitCount DESC
            LIMIT 5
            """)
    List<Article> getTopRecommendedArticles();
    @Select("SELECT a.*, " +
            "(SELECT COUNT(*) FROM recommendPoint WHERE relTypeCode = 'article' AND relId = a.id) AS hitCount " +
            "FROM article a")
    List<Article> getArticlesWithRecommendCount();
    
    @Update("UPDATE article SET hitCount = hitCount + 1 WHERE id = #{id}")
    void increaseRecommendCount(int id);

    @Update("UPDATE article SET hitCount = hitCount - 1 WHERE id = #{id}")
    void decreaseRecommendCount(int id);
    
}