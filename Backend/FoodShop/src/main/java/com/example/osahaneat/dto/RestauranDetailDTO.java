package com.example.osahaneat.dto;

import com.example.osahaneat.enity.Menurestaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class RestauranDetailDTO {
    private int id ;
    private String title;
    private String subtitle;
    private String descriptions ;
    private String image ;
    private boolean is_freeship ;
    private String address ;
    private Date open_date ;
    private double rating ;

    private List<FoodDTO> food ;

    private List<Menurestaurant> menurestaurants;

    private Set<String> category;

    public RestauranDetailDTO() {
        food = new ArrayList<>();
        menurestaurants = new ArrayList<>();
        category = new HashSet<>();
    }
}
