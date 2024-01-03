package com.example.blackout.test.controller;

//UsrRecommendPointController.java
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.blackout.test.service.RecommendService;
import com.example.blackout.test.vo.Rq;

@Controller
@RequestMapping("/usr/recommendPoint")
public class UsrRecommendController {

    private final RecommendService recommendService;
    private final Rq rq;

    public UsrRecommendController(RecommendService recommendService, Rq rq) {
        this.recommendService = recommendService;
        this.rq = rq;
    }

    @PostMapping("/toggleRecommend/{relTypeCode}/{relId}")
    @ResponseBody
    public ResponseEntity<?> toggleRecommend(@PathVariable String relTypeCode, @PathVariable int relId,  @RequestParam("memberId") int memberId) {
        if (memberId == 0) {
            return ResponseEntity.status(401).body("로그인 필요");
        }

        recommendService.toggleRecommend(memberId, relTypeCode, relId);
        return ResponseEntity.ok().body("추천 상태 변경");
    }
}
