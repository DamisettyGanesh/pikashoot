package com.theblog.pikashoot.services.blogPost;

import com.theblog.pikashoot.dto.BlogPostDTO;
import com.theblog.pikashoot.models.BlogPost;

import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    BlogPost createBlogPost(BlogPostDTO blogPostDTO, String username);

    List<BlogPost> getAllBlogPostsByUser();

    BlogPost getBlogPostById(int id);

    BlogPost saveBlogPost(BlogPost existingBlogPost);

    void deleteBlogPost(int id);
}
