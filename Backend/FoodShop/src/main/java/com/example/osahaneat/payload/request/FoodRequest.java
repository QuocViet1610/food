package com.example.osahaneat.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequest {
    private int id ;
    private int quanity;
    private double price;
}
