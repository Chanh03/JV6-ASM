package com.anhngo.mainproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
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

    @JsonIgnore
    @OneToMany(mappedBy = "username")
    private Set<Authority> authorities = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "username")
    private Set<Order> orders = new LinkedHashSet<>();

}