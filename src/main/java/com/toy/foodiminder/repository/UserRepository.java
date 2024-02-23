package com.toy.foodiminder.repository;

import com.toy.foodiminder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(String userId);   // ID로 회원 조회

    List<User> findAll();
}
