package com.example.osahaneat.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="title")
    private String title;

    @Column(name ="image")
    private String image;

    @Column(name ="time_ship")
    private String timeShip;

    @Column(name ="is_freeShip")
    private boolean isFreeShip;

    @Column(name ="price")
    private double price;

    @ManyToOne
    @JoinColumn(name ="cated_id")
    private Category category;


    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
    private Set<RatingFood> ratingFoods;


    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;


    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<FoodRestaurant> foodRestaurant;
}
