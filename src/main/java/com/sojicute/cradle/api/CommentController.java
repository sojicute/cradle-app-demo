package com.sojicute.cradle.api;

import com.sojicute.cradle.domain.Comment;
import com.sojicute.cradle.domain.Element;
import com.sojicute.cradle.services.CommentServiceImpl;
import com.sojicute.cradle.services.RoadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/road/{roadId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByRoadId(@PathVariable("roadId") Long roadId) {
        return new ResponseEntity<>(commentService.findCommentsByRoadId(roadId), HttpStatus.OK);
    }

    @PostMapping("/road/{roadId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("roadId") Long roadId, @RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.createComment(roadId, comment), HttpStatus.CREATED);
    }

    @PostMapping("/road/{roadId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("roadId") Long roadId, @PathVariable("commentId") Long commentId, @RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.updateComment(roadId, commentId, comment), HttpStatus.OK);
    }

    @DeleteMapping("/road/{roadId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("roadId") Long roadId, @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(roadId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
