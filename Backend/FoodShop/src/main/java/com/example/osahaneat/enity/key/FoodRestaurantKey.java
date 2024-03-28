package com.example.osahaneat.enity.key;

import com.example.osahaneat.enity.Category;
import com.example.osahaneat.enity.Food;
import com.example.osahaneat.enity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class FoodRestaurantKey implements Serializable {
    @Column(name = "foodId")
    private int FoodId;

    @Column(name = "RestaurantId")
    private int RestuarantId;

    public FoodRestaurantKey() {
    }

    public FoodRestaurantKey(int foodId, int restuarantId) {
        FoodId = foodId;
        RestuarantId = restuarantId;
    }
}
