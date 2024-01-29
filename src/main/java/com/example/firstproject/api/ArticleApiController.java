package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")  // URL 요청 접수
    public List<Article> index() {   // index() 메서드 정의
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")  // 코드를 붙여 넣고 URL 수정
    public Article show(@PathVariable Long id) {   // show() 메서드로 수정
        return articleRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/articles")  // URL 요청 접수
    public Article create(@RequestBody ArticleForm dto) {  // create() 메서드 정의
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")  // URL 요청 접수
    public ResponseEntity<Article> update(@PathVariable Long id,   // update() 메서드 정의
                                 @RequestBody ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();  // dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());  // 로그 찍기
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);  // 기존 데이터에 새 데이터 붙이기
        Article updated = articleRepository.save(target);  // article 엔티티 DB에 저장 // 수정 내용 DB에 최종 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated);  // 정상 응답

    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")  // URL 요청 접수
    public ResponseEntity<Article> delete(@PathVariable Long id) {  // 메서드 정의
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
