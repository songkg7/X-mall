package com.xmall.xmall.repository;

import com.xmall.xmall.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
};
