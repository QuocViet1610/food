package com.example.osahaneat.service;

import com.example.osahaneat.dto.CategoryDTO;
import com.example.osahaneat.dto.FoodDTO;
import com.example.osahaneat.dto.RestauranDetailDTO;
import com.example.osahaneat.dto.RestaurentDTO;
import com.example.osahaneat.enity.*;
import com.example.osahaneat.repository.RestaurentPository;
import com.example.osahaneat.service.imp.FileServiceImp;
import com.example.osahaneat.service.imp.LoginServiceImp;
import com.example.osahaneat.service.imp.RestaurentSeviceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RetaurentService implements RestaurentSeviceImp {

    @Autowired
    RestaurentPository restaurentPository;

    @Autowired
    FileServiceImp fileService;

    @Override
    public boolean save(MultipartFile file, String titel, String subtitle, String descriptions, boolean is_freeship, String address, String open_date) {
      boolean isSuccess = false;
        try{
          fileService.save(file);
          Restaurant restaurant =new Restaurant();
          restaurant.setTitle(titel);
          restaurant.setSubtitle(subtitle);
          restaurant.setDescriptions(descriptions);
          restaurant.setImage(file.getOriginalFilename());
          restaurant.setFreeShip(is_freeship);
          restaurant.setAddress(address);
          // convert string to variable Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
          Date openDate =sdf.parse(open_date);// convert
          restaurant.setOpenDatele(openDate);

            restaurentPository.save(restaurant);
          isSuccess =true;
      }catch (Exception e){
          System.out.println("insert false "+e.getMessage());
      }

        return isSuccess;
    }

    @Override
    public List<RestaurentDTO> getHomePageRestaurent() {
        Page<Restaurant> page = restaurentPository.findAll(
                PageRequest.of(0, 6));

//        List<Restaurant> page = restaurentPository.findAll();
        List<RestaurentDTO> restaurentDTOList = new ArrayList<>();
//        RestaurentDTO restaurentDTO = new RestaurentDTO();
        for(Restaurant data : page ){
            RestaurentDTO restaurentDTO = new RestaurentDTO();
            restaurentDTO.setId(data.getId());
            restaurentDTO.setTitel(data.getTitle());
            restaurentDTO.setSubtitle(data.getSubtitle());
            restaurentDTO.setImage(data.getImage());
            restaurentDTO.set_freeship(data.isFreeShip());
            restaurentDTO.setAddress(data.getAddress());
            restaurentDTO.setDescriptions(data.getDescriptions());
            restaurentDTO.setOpen_date(data.getOpenDatele());
            restaurentDTO.setRating(Rating(data.getRatingRestaurant()));
            restaurentDTOList.add(restaurentDTO);
        }

        return restaurentDTOList;
    }

    @Override
    public RestauranDetailDTO getRestaurentDetail(int id ) {
        Optional<Restaurant> restaurant = restaurentPository.findById(id);
        RestauranDetailDTO restaurentDTO =new RestauranDetailDTO();

        if(restaurant.isPresent()){
            Restaurant getRestaurant = restaurant.get();
            restaurentDTO.setId(getRestaurant.getId());
            restaurentDTO.setImage(getRestaurant.getImage());
            restaurentDTO.setAddress(getRestaurant.getAddress());
            restaurentDTO.setTitle(getRestaurant.getTitle());
            restaurentDTO.setDescriptions(getRestaurant.getDescriptions());
            restaurentDTO.set_freeship(getRestaurant.isFreeShip());
            restaurentDTO.setOpen_date(getRestaurant.getOpenDatele());
            restaurentDTO.setRating(Rating(getRestaurant.getRatingRestaurant()));

//        for (Menurestaurant menu : getRestaurant.getMenurestaurant() ) {
//            CategoryDTO categoryDTO=new CategoryDTO();
//            categoryDTO.setNameCate(menu.getCategory().getNameCate());
//            restaurentDTO.getCategoryDTO().add(categoryDTO);
//        }

        for (FoodRestaurant foodRestaurant: getRestaurant.getFoodRestaurants() ){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setID(foodRestaurant.getFood().getId());
            foodDTO.setImage(foodRestaurant.getFood().getImage());
            foodDTO.setTittle(foodRestaurant.getFood().getTitle());
            foodDTO.setCategoryDTO(foodRestaurant.getFood().getCategory().getNameCate());
            foodDTO.setFreesip(foodRestaurant.getFood().isFreeShip());
            foodDTO.setRating(RatingFood(foodRestaurant.getFood().getRatingFoods()));
            foodDTO.setPrice(foodRestaurant.getFood().getPrice());
            restaurentDTO.getCategory().add(foodRestaurant.getFood().getCategory().getNameCate());
            restaurentDTO.getFood().add(foodDTO);
        }

        }
        return restaurentDTO;
    }

    public double Rating(Set<RatingRestaurant> ratingRestaurant){
        double totalPoint = 1;

        for(RatingRestaurant rate : ratingRestaurant){
            totalPoint += rate.getRatePoint();
        }

        return totalPoint/2;
    }


    public double RatingFood(Set<RatingFood> ratingFood){
        double totalPoint = 1;

        for(RatingFood rate : ratingFood){
            totalPoint += rate.getRatePoint();
        }

        return totalPoint/2;
    }
}
