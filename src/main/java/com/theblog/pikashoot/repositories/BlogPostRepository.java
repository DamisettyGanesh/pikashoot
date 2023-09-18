package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    BlogPost findByPostId(int postId);
}