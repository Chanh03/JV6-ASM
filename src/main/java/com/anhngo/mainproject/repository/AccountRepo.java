package com.anhngo.mainproject.repository;

import com.anhngo.mainproject.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {
    Page<Account> findAll(Pageable pageable);

    Page<Account> findAllByFullnameContaining(String search, Pageable pageable);

    boolean existsByUsername(String username);

    Iterable<Account> findAllByUsernameContainingOrEmailContainingOrFullnameContaining(String s, String s1, String s2);

    Account findByEmail(String email);
}