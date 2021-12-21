package com.sojicute.cradle.repository;

import com.sojicute.cradle.domain.Comment;
import com.sojicute.cradle.domain.Element;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByRoadId(Long roadId);
}
