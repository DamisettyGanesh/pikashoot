package com.theblog.pikashoot.services.comment;

import com.theblog.pikashoot.dto.CommentDTO;
import com.theblog.pikashoot.models.Comments;

import java.util.List;

public interface CommentService {
    List<Comments> getAllComments(int postId);

    Comments createNewComment(CommentDTO commentDTO);

    Comments updateTheExistingComment(CommentDTO commentDTO, int commentId);

    void deleteComment(int commentId);
}
