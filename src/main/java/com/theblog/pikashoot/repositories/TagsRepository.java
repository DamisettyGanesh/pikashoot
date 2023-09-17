package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.Tags;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepositoryImplementation<Tags, Integer> {
}
