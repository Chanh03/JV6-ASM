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
@Table(name = "Students")
public class Student {
    @Id
    @Nationalized
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Nationalized
    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "marks", nullable = false)
    private Double marks;

    @ColumnDefault("1")
    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Nationalized
    @Column(name = "country", nullable = false, length = 2)
    private String country;

}