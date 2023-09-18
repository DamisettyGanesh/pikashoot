package com.theblog.pikashoot.dto;


import lombok.Data;

@Data
public class CommentDTO {
    String content;
    int postId;
}
