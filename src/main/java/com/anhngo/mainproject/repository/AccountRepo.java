package com.anhngo.mainproject.repository;

import com.anhngo.mainproject.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<User, String> {
    Page<User> findAll(Pageable pageable);

    Page<User> findAllByFullnameContaining(String search, Pageable pageable);

    boolean existsByUsername(String username);

    Iterable<User> findAllByUsernameContainingOrEmailContainingOrFullnameContaining(String s, String s1, String s2);
}