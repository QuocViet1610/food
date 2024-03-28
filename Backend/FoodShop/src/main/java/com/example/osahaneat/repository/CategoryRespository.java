package com.example.osahaneat.repository;

import com.example.osahaneat.enity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends JpaRepository<Category,Integer> {
}
