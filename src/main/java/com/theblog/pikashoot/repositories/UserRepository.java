package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.Users;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
}
