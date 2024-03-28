package com.example.osahaneat.service.imp;

import com.example.osahaneat.dto.CategoryDTO;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryDTO> getCategoryPageHome();

    CategoryDTO getCategoryByBread();
    List<CategoryDTO> getAll();
}
