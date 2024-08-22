package com.anhngo.mainproject.repository;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    Authority findByAccount(Account account);
}
