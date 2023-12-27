package com.example.blackout.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blackout.test.dao.ArticleDao;
import com.example.blackout.test.vo.Article;

@Service
public class ArticleService {
    
    private ArticleDao articleDao;
    
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
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
        articleDao.updateArticle(article);
    }

    
}