package com.example.osahaneat.service;

import com.example.osahaneat.dto.RestaurentDTO;
import com.example.osahaneat.enity.Food;
import com.example.osahaneat.enity.FoodRestaurant;
import com.example.osahaneat.repository.FoodRestaurantRepository;
import com.example.osahaneat.service.imp.FoodRestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodRestaurantService implements FoodRestaurantServiceImp {

    @Autowired
    FoodRestaurantRepository foodRestaurantRepository;

    @Override
    public RestaurentDTO findFoodInRes(int foodId) {

        Food food = new Food();
        List<FoodRestaurant> foodRestaurant = foodRestaurantRepository.findByFood(food);
        for(FoodRestaurant data : foodRestaurant){
        }

        return null;
    }


}
