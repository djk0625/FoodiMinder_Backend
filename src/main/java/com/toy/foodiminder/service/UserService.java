package com.toy.foodiminder.service;

import com.toy.foodiminder.common.model.ApiResult;
import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.entity.User;

public interface UserService {

    // 회원가입
    public String signUp(UserDto userDto) throws Exception;

    // id 중복체크
    Boolean idDuplChk(UserDto userDto) throws Exception;
}
