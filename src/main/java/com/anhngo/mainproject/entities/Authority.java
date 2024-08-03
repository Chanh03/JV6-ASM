package com.anhngo.mainproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Entity
@Table(name = "Authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Username", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Role_Id", nullable = false)
    private Role role;

    //    CHỊU
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
