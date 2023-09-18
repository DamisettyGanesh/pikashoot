package com.theblog.pikashoot.controllers;


import com.theblog.pikashoot.dto.CommentDTO;
import com.theblog.pikashoot.models.Comments;
import com.theblog.pikashoot.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("{postId}")
    public ResponseEntity<List<Comments>> getAllComments(@PathVariable int postId){
        List<Comments> comments = commentService.getAllComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comments> createNewComment(@RequestBody CommentDTO commentDTO){
        Comments comments = commentService.createNewComment(commentDTO);
        return new ResponseEntity<>(comments, HttpStatus.CREATED);
    }

    @PutMapping("{commentId}")
    public ResponseEntity<Comments> updateComment(@RequestBody CommentDTO commentDTO, @PathVariable int commentId){
        Comments comments = commentService.updateTheExistingComment(commentDTO, commentId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }

}
