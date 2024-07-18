package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ApiController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public Iterable<Account> index() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/add")
    public void add(@RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @PutMapping("/update")
    public void update(@RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        accountService.deleteAccountById(id);
    }

    @GetMapping("/edit/{id}")
    public Account edit(@PathVariable String id) {
        return accountService.getAccountById(id);
    }
}
