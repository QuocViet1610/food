package com.example.osahaneat.repository;

import com.example.osahaneat.enity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    // select * from User where UserName=? and Password =?
    List<Users> findByUserNameAndPassword(String userName,String password);
    Users findByUserName(String userName);
}
