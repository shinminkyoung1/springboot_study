package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Article toEntity() {
        return new Article(null, title, content);
    }
}


//package com.example.firstproject.dto;
//
//        import com.example.firstproject.controller.Article;
//        import com.example.firstproject.entity.Article;
//        import lombok.AllArgsConstructor;
//        import lombok.ToString;
//
//@AllArgsConstructor
//@ToString
//public class ArticleForm {
//    private String title; // 제목을 받을 필드
//    private String content; // 내용을 받을 필드
//
//    public ArticleForm(String title, String content) {     //생성자
//        this.title = title;
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {                            //toString() 메서드
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
//
//    public Article toEntity() {
//        return new Article(null, title, content);
//    }