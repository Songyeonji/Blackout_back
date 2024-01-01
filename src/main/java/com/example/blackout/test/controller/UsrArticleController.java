package com.example.blackout.test.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Article> doWrite(@RequestBody Article article) {
        Article savedArticle = articleService.writeArticle(article);
        return ResponseEntity.ok(savedArticle);
    }
    
    // 게시글 삭제
    @DeleteMapping("/doDelete")
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@RequestParam("id") int id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/doModify")
    @ResponseBody
    public ResponseEntity<?> updateArticle(@RequestParam("id") int id, @RequestBody Article article) {
        article.setId(id); // ID를 설정합니다.
        articleService.updateArticle(id, article);
        return ResponseEntity.ok().build();
    }

    private static final String IMAGE_DIRECTORY = "/path/to/image/directory";

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            String fileName = saveImage(file);
            // 파일을 웹에서 접근할 수 있는 경로로 변환
            String imageUrl = "/images/" + fileName;
            return ResponseEntity.ok().body(Map.of("imageUrl", imageUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Image upload failed");
        }
    }

    private String saveImage(MultipartFile file) throws Exception {
        // 파일 이름 생성 (중복 방지를 위해 현재 시간을 추가)
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        
        // 이미지 저장 경로 설정
        Path imagePath = Paths.get(IMAGE_DIRECTORY, fileName);
        
        // 이미지 파일 저장
        Files.copy(file.getInputStream(), imagePath);
        
        return fileName;
    }
}

