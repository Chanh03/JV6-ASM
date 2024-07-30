package com.anhngo.mainproject.services;

import com.anhngo.mainproject.entities.User;
import com.anhngo.mainproject.repository.AccountRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserServicesInterface {
    private AccountRepo accountRepo;

    public UserServices(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Iterable<User> getAllAccounts() {
        return accountRepo.findAll();
    }

    public Page<User> getAllAccountsByPage() {
        return accountRepo.findAll(Pageable.unpaged());
    }

    public void saveAccount(User account) {
        accountRepo.save(account);
    }

    public User saveApiAccount(User account) {
        return accountRepo.save(account);
    }

    public void deleteAccountById(String id) {
        accountRepo.deleteById(id);
    }

    public User getAccountById(String id) {
        return accountRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public void updateAccount(User account) {
        accountRepo.save(account);
    }

    public Page<User> search(String search) {
        return accountRepo.findAllByFullnameContaining(search, Pageable.unpaged());
    }

    public boolean existsByUsername(String username) {
        return accountRepo.existsByUsername(username);
    }

    public Iterable<User> searchAccount(String s, String s1, String s2) {
        return accountRepo.findAllByUsernameContainingOrEmailContainingOrFullnameContaining(s, s1, s2);
    }
}
