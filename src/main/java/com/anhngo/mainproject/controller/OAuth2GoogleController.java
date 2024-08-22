package com.anhngo.mainproject.controller;

import com.anhngo.mainproject.entities.Account;
import com.anhngo.mainproject.entities.Authority;
import com.anhngo.mainproject.entities.Role;
import com.anhngo.mainproject.repository.AuthorityRepo;
import com.anhngo.mainproject.repository.RoleRepo;
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
    @Autowired
    private AuthorityRepo authorityRepo;
    @Autowired
    private RoleRepo roleRepo;

    @GetMapping("/login/form")
    public String loginForm() {
        return "security/login";
    }

    @GetMapping("/login/success")
    public String loginSuccess(OAuth2AuthenticationToken accessToken, Model model) {
        String email = accessToken.getPrincipal().getAttribute("email");
        String fullname = accessToken.getPrincipal().getAttribute("name");
        assert email != null;
        Account existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            // Nếu chưa tồn tại, tạo một user mới
            Account newUser = new Account();
            newUser.setUsername(email);
            newUser.setEmail(email);
            newUser.setFullname(fullname);
            newUser.setPassword(""); // Không cần mật khẩu vì user login bằng OAuth2
            newUser.setEnabled(true); // Đảm bảo rằng user được kích hoạt
            newUser.setPhoto("google.png");
            userRepository.saveAccount(newUser);
        }
        Role role = roleRepo.findByName("ROLE_USER");
        Authority existingAuthority = authorityRepo.findByAccount(userRepository.findByEmail(email));
        if (existingAuthority == null) {
            Authority newAuthority = new Authority();
            newAuthority.setAccount(userRepository.findByEmail(email));
            newAuthority.setRole(role);
            authorityRepo.save(newAuthority);
        }
        UserDetails userDetails = User.withUsername(email).password("").roles("USER").build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // In thông tin Authentication để kiểm tra
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current User: " + currentAuth.getName());
        System.out.println("Authorities: " + currentAuth.getAuthorities());

        model.addAttribute("username", fullname);
        return "home/home";
    }


    @GetMapping("/login/failure")
    public String loginFailure(Model model) {
        model.addAttribute("message", false);
        return "security/login";
    }
}
