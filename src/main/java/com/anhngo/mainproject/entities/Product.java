package com.anhngo.mainproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @ColumnDefault("N'Product.gif'")
    @Column(name = "Image", nullable = false, length = 50)
    private String image;

    @ColumnDefault("0")
    @Column(name = "Price", nullable = false)
    private Double price;

    @Nationalized
    @Column(name = "Description", nullable = false)
    private String description;

    @ColumnDefault("getdate()")
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;

    @ColumnDefault("1")
    @Column(name = "Available", nullable = false)
    private Boolean available = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Category_Id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}