package com.example.osahaneat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    private  int ID;
    private String Tittle;
    private String image;
    private boolean isFreesip;
    private String  categoryDTO;
    private double rating;
    private double price;
    private int idcategoryDTO;
}
