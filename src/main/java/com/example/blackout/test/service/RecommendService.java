package com.example.blackout.test.service;

import org.springframework.stereotype.Service;
import com.example.blackout.test.dao.RecommendDao;

@Service
public class RecommendService {

    private final RecommendDao recommendDao;

    public RecommendService(RecommendDao recommendDao) {
        this.recommendDao = recommendDao;
    }

    public void toggleRecommend(int memberId, String relTypeCode, int relId) {
        if (recommendDao.existsRecommendPoint(memberId, relTypeCode, relId)) {
            recommendDao.deleteRecommendPoint(memberId, relTypeCode, relId);
        } else {
            recommendDao.insertRecommendPoint(memberId, relTypeCode, relId);
        }
    }

}

