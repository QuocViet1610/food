package com.example.osahaneat.enity;

import com.example.osahaneat.enity.key.MenuKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "menurestaurant")
public class Menurestaurant {
    @EmbeddedId
    private MenuKey menuKey;

    @ManyToOne
    @JoinColumn(name = "cate_id",insertable=false, updatable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "res_id",insertable=false, updatable=false)
    private Restaurant restaurant;

    @Column(name="create_date")
    private Date createDate;


}
