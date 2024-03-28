package com.example.osahaneat.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "promo")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name ="res_id")
    private Restaurant restaurant;

    @Column(name ="percent")
    private int percent;

    @Column(name ="start_date")
    private Date startDate;

    @Column(name ="End_date")
    private Date endDate;
}
