package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.Comments;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepositoryImplementation<Comments, Integer> {
}
