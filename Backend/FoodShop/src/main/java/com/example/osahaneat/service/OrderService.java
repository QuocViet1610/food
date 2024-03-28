package com.example.osahaneat.service;

import com.example.osahaneat.enity.*;
import com.example.osahaneat.enity.key.OrderDetailKey;
import com.example.osahaneat.payload.request.FoodRequest;
import com.example.osahaneat.payload.request.OrderRequest;
import com.example.osahaneat.repository.OrderDetailRespository;
import com.example.osahaneat.repository.OrderRepository;
import com.example.osahaneat.service.imp.OrderServiceImp;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderService implements OrderServiceImp {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Override
    public boolean save(OrderRequest orderRequest) {
        try{
            Users user = new Users();
            user.setId(orderRequest.getUserID());

            Restaurant restaurant= new Restaurant();
            restaurant.setId(orderRequest.getRestaurantID());

            Orders order = new Orders();
            order.setUsers(user);
            order.setRestaurant(restaurant);
            order.setPrice(orderRequest.getPrice());
            Date currentTime = new Date();

            // Định dạng ngày giờ theo định dạng bạn muốn, ví dụ "dd/MM/yyyy HH:mm:ss"
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = formatter.format(currentTime);
            Date date = formatter.parse(formattedDate);
            order.setCreateDate(date);

            orderRepository.save(order);

            List<OrderDetail> orderDetails = new ArrayList<>();
            for (FoodRequest foodRequest: orderRequest.getFood()){
                OrderDetail orderDetail=new OrderDetail();
                OrderDetailKey orderDetailKey = new OrderDetailKey(order.getId(),foodRequest.getId());

                orderDetail.setOrderDetailKey(orderDetailKey);
                orderDetail.setQuanity(foodRequest.getQuanity());
                orderDetails.add(orderDetail);
            }
            orderDetailRespository.saveAll(orderDetails);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
