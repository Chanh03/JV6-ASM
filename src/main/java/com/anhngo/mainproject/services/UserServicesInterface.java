package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.Account;
import org.springframework.data.domain.Page;

public interface UserServicesInterface {
    Iterable<Account> getAllAccounts();

    Page<Account> getAllAccountsByPage();

    void saveAccount(Account account);

    Account saveApiAccount(Account account);

    void deleteAccountById(String id);

    Account getAccountById(String id);

    void updateAccount(Account account);

    Page<Account> search(String search);

    boolean existsByUsername(String username);

    Iterable<Account> searchAccount(String s, String s1, String s2);

    Account findByEmail(String email);
}
