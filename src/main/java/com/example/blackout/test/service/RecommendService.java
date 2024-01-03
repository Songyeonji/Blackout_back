package com.example.blackout.test.service;

import org.springframework.stereotype.Service;

import com.example.blackout.test.dao.ArticleDao;
import com.example.blackout.test.dao.RecommendDao;

@Service
public class RecommendService {

    private final RecommendDao recommendDao;
    private final ArticleDao articleDao; 

    public RecommendService(RecommendDao recommendDao, ArticleDao articleDao) {
        this.recommendDao = recommendDao;
        this.articleDao = articleDao; 
    }
    public void toggleRecommend(int memberId, String relTypeCode, int relId) {
        if (recommendDao.existsRecommendPoint(memberId, relTypeCode, relId)) {
            recommendDao.deleteRecommendPoint(memberId, relTypeCode, relId);
            articleDao.decreaseRecommendCount(relId); // 추천 수 감소
        } else {
            recommendDao.insertRecommendPoint(memberId, relTypeCode, relId);
            articleDao.increaseRecommendCount(relId); // 추천 수 증가
        }
    }
}

