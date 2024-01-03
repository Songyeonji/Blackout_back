package com.example.blackout.test.vo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int id;
    private String title;
    private String body;
    private String boardId;
    private int memberId; // memberId 필드 추가
    private Date regDate; // 작성일
    private Date updateDate; // 최종 수정일
    private int hitCount;
    private int recommendCount;
    private int point;
    private List<Integer> recommendPointUsers; // 추천한 사용자 ID 목록

    public void setRecommendPointUsers(List<Integer> recommendPointUsers) {
        this.recommendPointUsers = recommendPointUsers;
    }
}


