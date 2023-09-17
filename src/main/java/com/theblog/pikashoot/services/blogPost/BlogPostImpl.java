package com.theblog.pikashoot.services.blogPost;

import com.theblog.pikashoot.dto.BlogPostDTO;
import com.theblog.pikashoot.models.BlogPost;
import com.theblog.pikashoot.models.Users;
import com.theblog.pikashoot.repositories.BlogPostRepository;
import com.theblog.pikashoot.repositories.CategoryRepository;
import com.theblog.pikashoot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostImpl implements BlogPostService{

    BlogPostRepository blogPostRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public BlogPostImpl(BlogPostRepository blogPostRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.blogPostRepository = blogPostRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public BlogPost createBlogPost(BlogPostDTO blogPostDTO, String username) {
        // Create a new BlogPost entity and populate it with data from the DTO
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPostDTO.getContent());

        // Set other fields like user, category, and date if needed
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        blogPost.setUsers(user);

        // Save the timestamp
        LocalDateTime now = LocalDateTime.now();
        blogPost.setCreatedAt(now);
        blogPost.setUpdatedAt(now);


        // Save the blog post to the database
        return blogPostRepository.save(blogPost);
    }


    @Override
    public List<BlogPost> getAllBlogPostsByUser() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost getBlogPostById(int id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);
        return blogPostOptional.orElse(null);
    }

    @Override
    public BlogPost saveBlogPost(BlogPost existingBlogPost) {
        return blogPostRepository.save(existingBlogPost);
    }

    @Override
    public void deleteBlogPost(int id) {
        blogPostRepository.deleteById(id);
    }
}
