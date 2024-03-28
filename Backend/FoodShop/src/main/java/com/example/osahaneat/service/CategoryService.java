package com.example.osahaneat.service;
import com.example.osahaneat.dto.CategoryDTO;
import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.enity.Category;
import com.example.osahaneat.enity.Food;
import com.example.osahaneat.repository.CategoryRespository;
import com.example.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRespository categoryRespository;

    @Override
    public List<CategoryDTO> getCategoryPageHome() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Category> CategoryPage = categoryRespository.findAll(pageRequest);
        List<CategoryDTO> categoryDTOList= new ArrayList<>();
        for (Category dataCategory: CategoryPage) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setNameCate(dataCategory.getNameCate());

                List<FoodDTO> food= new ArrayList<>();
                for(Food dataFood : dataCategory.getFood()){
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setTittle(dataFood.getTitle());
                    foodDTO.setImage(dataFood.getImage());
                    foodDTO.setFreesip(dataFood.isFreeShip());
                    food.add(foodDTO);
                }
                categoryDTO.setListFood(food);
            categoryDTOList.add(categoryDTO);
        }

        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryByBread() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Optional<Category> categoryPage = categoryRespository.findById(1);
        CategoryDTO categoryDTO = new CategoryDTO();
        if (categoryPage.isPresent()) {
            Category dataCategory = categoryPage.get();

            categoryDTO.setNameCate(dataCategory.getNameCate());


            List<FoodDTO> food= new ArrayList<>();
            for(Food dataFood : dataCategory.getFood()){
                FoodDTO foodDTO = new FoodDTO();
                foodDTO.setTittle(dataFood.getTitle());
                foodDTO.setImage(dataFood.getImage());
                foodDTO.setFreesip(dataFood.isFreeShip());
                food.add(foodDTO);
            }
            categoryDTO.setListFood(food);


        }

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> CategoryPage = categoryRespository.findAll();
        List<CategoryDTO> categoryDTOList= new ArrayList<>();
        for (Category dataCategory: CategoryPage) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setNameCate(dataCategory.getNameCate());

            categoryDTO.setIdCategoryDTO(dataCategory.getId());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }
}
