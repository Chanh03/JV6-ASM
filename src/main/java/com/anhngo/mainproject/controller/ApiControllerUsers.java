package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.services.UserServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class ApiControllerUsers {

    @Autowired
    private UserServicesInterface accountService;

    @GetMapping()
    public Iterable<Account> index() {
        return accountService.getAllAccounts();
    }

    @PostMapping()
    public void add(@RequestBody Account account) {
        accountService.saveAccount(account);
    }

    @PutMapping("/{username}")
    public void update(@RequestBody Account account, @PathVariable(name = "username") String id) {
        if (accountService.existsByUsername(id)) {
            accountService.updateAccount(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable(name = "username") String id) {
        if (accountService.existsByUsername(id)) {
            accountService.deleteAccountById(id);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @GetMapping("/{username}")
    public Account edit(@PathVariable(name = "username") String id) {
        try {
            return accountService.getAccountById(id);
        } catch (Exception e) {
            throw new RuntimeException("Account not found");
        }
    }

    @GetMapping("/filter")
    public Iterable<Account> search(@RequestParam(name = "username") Optional<String> username, @RequestParam(name = "email") Optional<String> email, @RequestParam(name = "fullname") Optional<String> fullname) {
        return accountService.searchAccount(username.orElse(""), email.orElse(""), fullname.orElse(""));
    }
}
