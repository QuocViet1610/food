package com.example.osahaneat.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private int id;
    private int restaurantID;
    private int userID;
    private double price;

    private List<FoodRequest> food;
}
