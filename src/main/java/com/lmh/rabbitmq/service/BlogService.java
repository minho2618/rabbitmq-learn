package com.lmh.rabbitmq.service;

import com.lmh.rabbitmq.domain.Order;
import com.lmh.rabbitmq.dto.OrderCreatedEvent;
import com.lmh.rabbitmq.repository.BlogRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Order save(OrderCreatedEvent request) {
        return blogRepository.save(request.toEntity());
    }
}
