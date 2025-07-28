package com.lmh.blog.repository;

import com.lmh.blog.domain.Article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
