package com.example.osahaneat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RestaurentDTO {
    private int id ;
    private String titel;
    private String subtitle;
    private String descriptions ;
    private String image ;
    private boolean is_freeship ;
    private String address ;
    private Date open_date ;
    private double rating ;

    public RestaurentDTO() {
    }


}
