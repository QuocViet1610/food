package com.example.osahaneat.service.imp;

import com.example.osahaneat.dto.RestauranDetailDTO;
import com.example.osahaneat.dto.RestaurentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurentSeviceImp {

    boolean save(MultipartFile file,
                 String titel, String subtitle, String descriptions, boolean is_freeship, String address, String open_date);

    List<RestaurentDTO> getHomePageRestaurent();

    RestauranDetailDTO getRestaurentDetail(int id);
}
