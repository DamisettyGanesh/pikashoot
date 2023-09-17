package com.theblog.pikashoot.repositories;

import com.theblog.pikashoot.models.Role;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepositoryImplementation<Role, Integer> {
    Optional<Role> findByName(String name);
}
