package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.services.UserServicesInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private UserServicesInterface accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping({"", "/edit/{username}"})
    public String index(Model model, @PathVariable(value = "username", required = false) String username) {
        Page<Account> page = accountService.getAllAccountsByPage();
        if (username != null) {
            Account account = accountService.getAccountById(username);
            model.addAttribute("account", account);
        } else {
            model.addAttribute("account", new Account());
        }
        model.addAttribute("accounts", page.getContent());
        return "admin/users";
    }

    @RequestMapping("/add")
    public String update(@ModelAttribute Account account) {
        String password = account.getPassword();
        account.setPassword(passwordEncoder.encode(password));
        accountService.saveAccount(account);
        return "redirect:/users";
    }

    @RequestMapping("/delete/{username}")
    public String delete(@PathVariable("username") String username) {
        accountService.deleteAccountById(username);
        return "redirect:/users";
    }

    @RequestMapping("/search")
    public String search(@RequestParam(name = "search") String search, Model model, HttpServletRequest request) {
        if (search.isEmpty()) {
            return "redirect:/users";
        }
        Page<Account> accounts = accountService.search(search);
        model.addAttribute("account", new Account());
        model.addAttribute("accounts", accounts.getContent());
        return "admin/users";
    }
}
