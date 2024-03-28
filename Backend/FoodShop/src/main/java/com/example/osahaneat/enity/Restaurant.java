package com.example.osahaneat.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="title")
    private String title;

    @Column(name ="subtitle")
    private String subtitle;

    @Column(name ="descriptions")
    private String descriptions;

    @Column(name ="image")
    private String image;

    @Column(name ="is_freeship")
    private boolean isFreeShip;

    @Column(name ="address")
    private String address;

    @Column(name ="open_date")
    private Date openDatele;

    @OneToMany(mappedBy = "restaurant")
    private Set<Menurestaurant> menurestaurant;

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> ratingRestaurant;

    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> orders;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promotion> Promotion;

    @OneToMany(mappedBy = "restaurant")
    private Set<FoodRestaurant> FoodRestaurants;
}
