package com.anhngo.mainproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account implements UserDetails {
    @Id
    @Nationalized
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Column(name = "Password", nullable = false, length = 50)
    private String password;

    @Nationalized
    @Column(name = "Fullname", nullable = false, length = 50)
    private String fullname;

    @Nationalized
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Nationalized
    @ColumnDefault("N'Photo.gif'")
    @Column(name = "Photo", nullable = false, length = 50)
    private String photo;

    @Column(name = "Enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "username")
    private Set<Order> orders = new LinkedHashSet<>();

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}