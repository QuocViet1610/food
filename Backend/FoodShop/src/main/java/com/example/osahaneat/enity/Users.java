package com.example.osahaneat.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity(name ="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username" , unique=true )
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name= "role_id")
    private Roles role;

   /* @JsonIgnore*/
    @OneToMany(mappedBy = "users")
    private Set<RatingFood> ratingFoods;

    @OneToMany(mappedBy = "users")
    private Set<RatingRestaurant> ratingRestaurant;

    @OneToMany(mappedBy = "users")
    private Set<Orders> orders;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
