package com.theblog.pikashoot.controllers;



import com.theblog.pikashoot.dto.BlogPostDTO;
import com.theblog.pikashoot.models.BlogPost;
import com.theblog.pikashoot.services.blogPost.BlogPostImpl;
import com.theblog.pikashoot.services.blogPost.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;


    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping
    public ResponseEntity<BlogPost> createNewBlog(@RequestBody BlogPostDTO blogPostDTO){
        // Change the userId / work on it later
        BlogPost createdPost = blogPostService.createBlogPost(blogPostDTO, "nappa");
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPostsByUser(){
        List<BlogPost> blogPosts = blogPostService.getAllBlogPostsByUser();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable int id){
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if(blogPost != null){
            return new ResponseEntity<>(blogPost, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateTheBlogPost(@RequestBody BlogPostDTO blogPostDTO, @PathVariable int id){
        BlogPost existingBlogPost = blogPostService.getBlogPostById(id);
        if (existingBlogPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update only the fields provided in the DTO
        if (blogPostDTO.getTitle() != null) {
            existingBlogPost.setTitle(blogPostDTO.getTitle());
        }
        if (blogPostDTO.getContent() != null) {
            existingBlogPost.setContent(blogPostDTO.getContent());
        }

        existingBlogPost.setUpdatedAt(LocalDateTime.now());

        BlogPost savedBlogPost  = blogPostService.saveBlogPost(existingBlogPost);
        return new ResponseEntity<>(savedBlogPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable int id){
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>("Blog deleted", HttpStatus.OK);
    }
}
