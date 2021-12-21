package com.sojicute.cradle.services;

import com.sojicute.cradle.domain.Comment;
import com.sojicute.cradle.exception.ResourceNotFoundException;
import com.sojicute.cradle.repository.CommentRepository;
import com.sojicute.cradle.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RoadRepository roadRepository;

    @Override
    public List<Comment> findCommentsByRoadId(Long id) {
        return commentRepository.findCommentsByRoadId(id);
    }

    @Override
    public Comment createComment(Long roadId, Comment comment) {
        return roadRepository.findById(roadId).map(road -> {
            comment.setRoad(road);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Road with id "+ roadId + " not found"));
    }

    @Override
    public Comment updateComment(Long roadId, Long commentId, Comment comment) {

        if(!commentRepository.existsById(roadId)) {
            throw new ResourceNotFoundException("Road with id "+ roadId + " not found");
        }

        return commentRepository.findById(commentId).map(comm -> {
            comm.setText(comment.getText());
            return commentRepository.save(comm);
        }).orElseThrow(()-> new ResourceNotFoundException("Comment with id "+ commentId +" not found"));
    }

    @Override
    public void deleteComment(Long roadId, Long commentId) {
        if(!commentRepository.existsById(roadId)) {
            throw new ResourceNotFoundException("Road with id "+ roadId + " not found");
        }

        commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return "Ok";
        }).orElseThrow(()-> new ResourceNotFoundException("Comment with id "+ commentId + "not found or already deleted"));
    }
}
