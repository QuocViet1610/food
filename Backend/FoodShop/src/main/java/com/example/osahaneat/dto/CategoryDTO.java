package com.example.osahaneat.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    private int idCategoryDTO;
    private String nameCate;
    private List<FoodDTO> listFood;

}
