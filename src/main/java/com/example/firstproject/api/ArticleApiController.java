package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  // 로그를 찍을 수 있게 어노테이션 추가
@RestController  // REST 컨트롤러 선언
public class ArticleApiController {
    @Autowired   // 게시글 리파지터리 주입
    private ArticleService articleService;  // 서비스 객체 주입
//    // GET
    @GetMapping("/api/articles")  // URL 요청 접수
    public List<Article> index() {   // index() 메서드 정의
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")  // 코드를 붙여 넣고 URL 수정
    public Article show(@PathVariable Long id) {   // show() 메서드로 수정
        return articleService.show(id);
    }
    // POST
    @PostMapping("/api/articles")  // URL 요청 접수
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {  // create() 메서드 정의
        Article created = articleService.create(dto);
        return (created != null) ?  // 생성하면 정상, 실패하면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")  // URL 요청 접수
    public ResponseEntity<Article> update(@PathVariable Long id,   // update() 메서드 정의
                                          @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);  // 서비스를 통해 게시글 수정
        return (updated != null) ?   // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")  // URL 요청 접수
    public ResponseEntity<Article> delete(@PathVariable Long id) {  // 메서드 정의
        Article deleted = articleService.delete(id);  // 서비스를 통해 게시글 삭제
        return (deleted != null) ?   // 삭제 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")   // 여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest
            (@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticle(dtos);  // 서비스 호출
        return (createdList != null) ?    // 생성 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }   // transactionTest() 메서드 정의
}
