package com.theblog.pikashoot.services.comment;


import com.theblog.pikashoot.dto.CommentDTO;
import com.theblog.pikashoot.models.BlogPost;
import com.theblog.pikashoot.models.Comments;
import com.theblog.pikashoot.repositories.BlogPostRepository;
import com.theblog.pikashoot.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentsRepository commentRepository;
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public CommentServiceImpl(CommentsRepository commentRepository, BlogPostRepository blogPostRepository) {
        this.commentRepository = commentRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public List<Comments> getAllComments(int postId) {
        return commentRepository.findByPostPostId(postId);
    }

    @Override
    public Comments createNewComment(CommentDTO commentDTO) {
        // Create new comment
        Comments comments = new Comments();

        // Set Post for the comment
        BlogPost post = blogPostRepository.findByPostId(commentDTO.getPostId());

        LocalDateTime now = LocalDateTime.now();

        if(post != null){
            comments.setContent(commentDTO.getContent());
            comments.setPost(post);
            comments.setCreatedAt(now);
            comments.setUpdatedAt(now);
        }

        return commentRepository.save(comments);
    }

    @Override
    public Comments updateTheExistingComment(CommentDTO commentDTO, int commentId) {

        Comments comment = commentRepository.findByCommentId(commentId);

        comment.setContent(commentDTO.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }
}
