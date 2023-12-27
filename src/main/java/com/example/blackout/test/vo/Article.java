package com.example.blackout.test.vo;

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
    // 기타 필드
}


