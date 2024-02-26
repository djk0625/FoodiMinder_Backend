package com.toy.foodiminder.service.impl;

import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.entity.User;
import com.toy.foodiminder.repository.UserRepository;
import com.toy.foodiminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public String signUp(UserDto userDto) throws Exception {

        if(!userDto.getUserPass().equals(userDto.getCheckedPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.save(userDto.toEntity());

        return user.getUserId();
    }

    @Transactional
    @Override
    public Boolean idDuplChk(UserDto userDto) throws Exception {

        if(userRepository.findByUserId(userDto.getUserId()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        return true;
    }

}
