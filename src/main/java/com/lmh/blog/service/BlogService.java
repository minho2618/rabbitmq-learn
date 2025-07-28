package com.lmh.blog.service;

import com.lmh.blog.domain.Article;
import com.lmh.blog.dto.AddArticleRequest;
import com.lmh.blog.repository.BlogRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
