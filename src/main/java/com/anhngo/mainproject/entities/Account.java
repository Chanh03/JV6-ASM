package com.anhngo.mainproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Nationalized
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Column(name = "Password", nullable = false)
    private String password;

    @Nationalized
    @Column(name = "Fullname", nullable = false, length = 50)
    private String fullname;

    @Nationalized
    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Enabled", nullable = false)
    private boolean enabled;

    @Nationalized
    @ColumnDefault("N'Photo.gif'")
    @Column(name = "Photo", nullable = false, length = 50)
    private String photo;

}