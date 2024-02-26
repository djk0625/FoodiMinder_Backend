package com.toy.foodiminder.repository;

import com.toy.foodiminder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);   // ID로 회원 조회

    List<User> findAll();
}
