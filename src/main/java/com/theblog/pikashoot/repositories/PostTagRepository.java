package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.PostTag;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends JpaRepositoryImplementation<PostTag, Integer> {
}
