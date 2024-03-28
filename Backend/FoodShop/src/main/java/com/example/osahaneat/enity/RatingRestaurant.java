package com.example.osahaneat.enity;

import com.example.osahaneat.enity.Food;
import com.example.osahaneat.enity.Restaurant;
import com.example.osahaneat.enity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ratingrestaurant ")
public class RatingRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;

    @Column(name ="content")
    private String content;

    @Column(name ="rate_point")
    private int ratePoint;

}
