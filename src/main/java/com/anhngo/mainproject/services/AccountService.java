package com.anhngo.mainproject.services;

import com.anhngo.mainproject.repository.AccountRepo;
import com.anhngo.mainproject.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    public Page<Account> getAllAccountsByPage() {
        return accountRepo.findAll(Pageable.unpaged());
    }

    public void saveAccount(Account account) {
        accountRepo.save(account);
    }

    public Account saveApiAccount(Account account) {
        return accountRepo.save(account);
    }

    public void deleteAccountById(String id) {
        accountRepo.deleteById(id);
    }

    public Account getAccountById(String id) {
        return accountRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public void updateAccount(Account account) {
        accountRepo.save(account);
    }

    public Page<Account> search(String search) {
        return accountRepo.findAllByFullnameContaining(search, Pageable.unpaged());
    }

}
