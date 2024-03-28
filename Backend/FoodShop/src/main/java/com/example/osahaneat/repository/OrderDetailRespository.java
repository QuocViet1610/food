package com.example.osahaneat.repository;

import com.example.osahaneat.enity.OrderDetail;
import com.example.osahaneat.enity.key.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRespository extends JpaRepository<OrderDetail, OrderDetailKey> {
}
