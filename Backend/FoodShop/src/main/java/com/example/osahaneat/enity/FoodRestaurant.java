package com.example.osahaneat.enity;


import com.example.osahaneat.enity.key.FoodRestaurantKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "foodrestaurant")
public class FoodRestaurant {
    @EmbeddedId
    private FoodRestaurantKey foodRestaurantKey;

    @ManyToOne
    @JoinColumn(name = "foodId",insertable=false, updatable=false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "restaurantId",insertable=false, updatable=false)
    private Restaurant restaurant;

    @Column(name = "createDate")
    private Date CreateDate;

}
