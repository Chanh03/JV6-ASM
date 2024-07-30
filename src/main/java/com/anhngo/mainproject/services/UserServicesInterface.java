package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.User;
import org.springframework.data.domain.Page;

public interface UserServicesInterface {
    Iterable<User> getAllAccounts();

    Page<User> getAllAccountsByPage();

    void saveAccount(User account);

    User saveApiAccount(User account);

    void deleteAccountById(String id);

    User getAccountById(String id);

    void updateAccount(User account);

    Page<User> search(String search);

    boolean existsByUsername(String username);

    Iterable<User> searchAccount(String s, String s1, String s2);
}
