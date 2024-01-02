package com.example.blackout.test.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendPoint {
    private int id;
    private int memberId;      // 추천한 회원의 ID
    private String relTypeCode; // 관련 데이터 타입 코드 (예: 'article')
    private int relId;          // 관련 데이터 번호 (예: 게시글 ID)
    private int point;          // 추천 수 (예: 1 또는 -1)
}

