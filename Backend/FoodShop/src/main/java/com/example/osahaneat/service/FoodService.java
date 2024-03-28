package com.example.osahaneat.service;

import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.enity.Category;
import com.example.osahaneat.enity.Food;
import com.example.osahaneat.enity.FoodRestaurant;
import com.example.osahaneat.enity.Restaurant;
import com.example.osahaneat.enity.key.FoodRestaurantKey;
import com.example.osahaneat.repository.FoodRepository;
import com.example.osahaneat.repository.FoodRestaurantRepository;
import com.example.osahaneat.repository.RestaurentPository;
import com.example.osahaneat.service.imp.FoodServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FoodService implements FoodServiceImp {
    @Autowired
    FileService fileService;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodRestaurantRepository foodRestaurantRepository;

    @Autowired
    RestaurentPository restaurentPository;

    @Override
    public boolean save(MultipartFile file,String titel,  double price, boolean is_freeship, int cated_id, int resId) {
        boolean checkSave =false;
        try{
            Food food = new Food();
            fileService.save(file);
            food.setImage(file.getOriginalFilename());
            food.setTitle(titel);
            food.setPrice(price);
            food.setFreeShip(is_freeship);
            Category category =new Category();
            category.setId(cated_id);
            food.setCategory(category);
            foodRepository.save(food);

            FoodRestaurant foodRestaurant=new FoodRestaurant();
            FoodRestaurantKey foodRestaurantKey =new FoodRestaurantKey(food.getId(),resId);
            foodRestaurant.setFoodRestaurantKey(foodRestaurantKey);

            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = formatter.format(currentTime);
            Date date = formatter.parse(formattedDate);
            foodRestaurant.setCreateDate(date);
            foodRestaurantRepository.save(foodRestaurant);

            checkSave = true;


        }catch (Exception e){

            System.out.println("error save: "+ e.getMessage());
        }
    return checkSave;
    }

    @Override
    public List<FoodDTO> foodHomePage() {
        Page<Food> page = foodRepository.findAll(
                PageRequest.of(0, 4));
        List<FoodDTO> foodDTOList = new ArrayList<>();
            for(Food data : page){
                FoodDTO foodDTO =new FoodDTO();
                foodDTO.setImage(data.getImage());
                foodDTO.setRating(5);
                foodDTO.setPrice(data.getPrice());
                foodDTO.setTittle(data.getTitle());
                foodDTO.setID(data.getId());
                foodDTOList.add(foodDTO);
        }
        return foodDTOList;
    }

    @Override
    public List<FoodDTO> foodByRestaurant(int idRes) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(idRes);
        List<FoodDTO> foodDTOs =new ArrayList<>();
        List<FoodRestaurant> foodRestaurants =foodRestaurantRepository.findByRestaurant(restaurant);
        for (FoodRestaurant data: foodRestaurants){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setID(data.getFood().getId());
            foodDTO.setTittle(data.getFood().getTitle());
            foodDTO.setImage(data.getFood().getImage());
            foodDTO.setPrice(data.getFood().getPrice());
            foodDTO.setFreesip(data.getFood().isFreeShip());
            foodDTO.setCategoryDTO(data.getFood().getCategory().getNameCate());
            foodDTO.setImage(data.getFood().getImage());
            foodDTO.setIdcategoryDTO(data.getFood().getCategory().getId());
            foodDTOs.add(foodDTO);
        }
        return foodDTOs;
    }

    @Override
    public boolean delete(int id) {
        boolean success = false;
        try{
            foodRepository.deleteById(id);
            success =true;
        }catch (Exception e){
            success = false;
        }

        return success;
    }

    @Override
    public boolean update(String titel,String img, double price, boolean is_freeship, int cated_id, int resId) {
        boolean checkSave =false;
        try{
            Food food = new Food();
            food.setImage(img);
            food.setTitle(titel);
            food.setPrice(price);
            food.setFreeShip(is_freeship);
            Category category =new Category();
            category.setId(cated_id);
            food.setCategory(category);
            foodRepository.save(food);
            checkSave = true;


        }catch (Exception e){

            System.out.println("error save: "+ e.getMessage());
        }
        return checkSave;
    }
}
