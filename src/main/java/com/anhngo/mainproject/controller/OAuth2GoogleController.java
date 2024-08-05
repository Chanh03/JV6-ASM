package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.services.UserServicesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class OAuth2GoogleController {
    @Autowired
    private UserServicesInterface userRepository;

    @GetMapping("/login/form")
    public String loginForm() {
        return "security/login";
    }

    @GetMapping("/login/success")
    public String loginSuccess(OAuth2AuthenticationToken accessToken, Model model) {
        String email = accessToken.getPrincipal().getAttribute("email");
        String fullname = accessToken.getPrincipal().getAttribute("name");
        assert email != null;
        model.addAttribute("username", fullname);
        UserDetails userDetails = User.withUsername(email).password("").authorities("USER").build();

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "security/login";
    }


    @GetMapping("/login/failure")
    public String loginFailure(Model model) {
        model.addAttribute("errorMessage", "Login failed");
        return "security/login";
    }
}
