package com.example.osahaneat.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_cate")
    private String nameCate;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private List<Food> food;

    @OneToMany(mappedBy ="category")
    private Set<Menurestaurant> Menurestaurant;
}
