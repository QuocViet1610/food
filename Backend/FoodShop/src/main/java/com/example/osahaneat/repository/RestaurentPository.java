package com.example.osahaneat.repository;

import com.example.osahaneat.enity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurentPository extends JpaRepository<Restaurant,Integer> {
}
