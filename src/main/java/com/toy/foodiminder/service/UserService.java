package com.toy.foodiminder.service;

import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface UserService {

    // 회원가입
    public String signUp(UserDto userDto) throws Exception;
}
