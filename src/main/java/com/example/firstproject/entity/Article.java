package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // DB가 id 자동 생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
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
//    public String toString() {                              //toString() 메서드
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
//
//    public Article toEntity() {
//        return new Article(null, title, content);
//    }