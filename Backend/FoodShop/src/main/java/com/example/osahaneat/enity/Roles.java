package com.example.osahaneat.enity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name ="create_date")
    private Date createDate;

    @OneToMany(mappedBy = "role")
    private List<Users> user;
}