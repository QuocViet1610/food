package com.example.osahaneat.enity;

import com.example.osahaneat.enity.key.OrderDetailKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey OrderDetailKey;

    @ManyToOne
    @JoinColumn(name ="order_id",insertable=false, updatable=false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name ="food_id",insertable=false, updatable=false)
    private Food food;

//    @Column(name="create_date")
//    private Date createDate;

    @Column(name="quanity")
    private int quanity;



}
