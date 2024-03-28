package com.example.osahaneat.enity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailKey implements Serializable {

    @Column(name="order_id")
    private int OrderId;

    @Column(name="food_id")
    private int foodId;

    public OrderDetailKey() {
    }

    public OrderDetailKey(int orderId, int foodId) {
        OrderId = orderId;
        this.foodId = foodId;
    }


}
