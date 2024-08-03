package com.anhngo.mainproject.services;

import com.anhngo.mainproject.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceDetails implements UserDetailsService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
