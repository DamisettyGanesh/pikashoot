package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.Category;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepositoryImplementation<Category, Integer> {
    Optional<Category> findByName(String name);
}
