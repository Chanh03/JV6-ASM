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
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "Id", nullable = false, length = 4)
    private String id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new LinkedHashSet<>();

}