package com.example.blackout.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blackout.test.dao.ArticleDao;
import com.example.blackout.test.dao.RecommendDao;
import com.example.blackout.test.vo.Article;

@Service
public class ArticleService {
    
    private ArticleDao articleDao;
    private final RecommendDao recommendDao;

    public ArticleService(ArticleDao articleDao, RecommendDao recommendDao) {
        this.articleDao = articleDao;
        this.recommendDao = recommendDao;
    }

    public List<Article> getArticlesWithRecommendPointUsers() {
        List<Article> articles = articleDao.getArticles();
        articles.forEach(article -> {
            List<Integer> recommendPointUsers = recommendDao.getRecommendPointUsersByArticleId(article.getId());
            article.setRecommendPointUsers(recommendPointUsers);
        });
        return articles;
    }
    public Article writeArticle(Article article) {
        articleDao.writeArticle(article);
        return article; // ID가 자동으로 설정될 것으로 예상
    }
    
    public List<Article> getArticles() {
        return articleDao.getArticles();
    }
    
    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }
    
    public void updateArticle(int id, Article article) {
        article.setId(id); // ID를 설정합니다.
        articleDao.updateArticle(article);
    }
    public List<Article> getTopRecommendedArticles() {
        return articleDao.getTopRecommendedArticles();
    }


    public List<Article> getArticlesWithRecommendCount() {
        List<Article> articles = articleDao.getArticles();
        articles.forEach(article -> {
            int hitCount = recommendDao.getRecommendCountByArticleId(article.getId());
            article.setRecommendCount(hitCount);
        });
        return articles;
    }
    
    
}