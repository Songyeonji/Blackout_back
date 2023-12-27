package com.example.blackout.test.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.blackout.test.service.ArticleService;
import com.example.blackout.test.vo.Article;

@Controller
@RequestMapping("/usr/article")
public class UsrArticleController {

    private final ArticleService articleService;

    public UsrArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/showList")
    @ResponseBody
    public List<Article> showList() {
        return articleService.getArticles();
    }

    @GetMapping("/getArticle")
    @ResponseBody
    public ResponseEntity<Article> getArticle(@RequestParam("id") int id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/doWrite")
    public ResponseEntity<Article> doWrite(@RequestBody Article vo) {
        Article article = articleService.writeArticle(vo);
        return ResponseEntity.ok(article);
    }
    // 게시글 삭제
    @DeleteMapping("/doDelete")
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@RequestParam("id") int id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/doModify")
    @ResponseBody
    public ResponseEntity<?> updateArticle(@RequestParam("id") int id, @RequestBody Article article) {
        articleService.updateArticle(id, article);
        return ResponseEntity.ok().build();
    }
}
