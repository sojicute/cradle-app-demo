package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findCommentsByRoadId(Long id);

    Comment createComment(Long roadId, Comment comment);

    Comment updateComment(Long roadId, Long commentId, Comment comment);

    void deleteComment(Long roadId, Long commentId);

}
