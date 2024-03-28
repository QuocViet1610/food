package com.example.osahaneat.repository;

import com.example.osahaneat.enity.Food;
import com.example.osahaneat.enity.FoodRestaurant;
import com.example.osahaneat.enity.Restaurant;
import com.example.osahaneat.enity.key.FoodRestaurantKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRestaurantRepository extends JpaRepository<FoodRestaurant, FoodRestaurantKey> {
    List<FoodRestaurant> findByRestaurant(Restaurant restaurant);
    List<FoodRestaurant> findByFood(Food food);
}
