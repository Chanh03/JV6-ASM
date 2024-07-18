package com.anhngo.mainproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @Nationalized
    @Column(name = "Id", nullable = false, length = 10)
    private String id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<Authority> authorities = new LinkedHashSet<>();

}