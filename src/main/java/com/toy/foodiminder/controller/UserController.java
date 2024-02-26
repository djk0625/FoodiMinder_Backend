package com.toy.foodiminder.controller;

import com.toy.foodiminder.common.enums.ResultCode;
import com.toy.foodiminder.common.model.ApiResult;
import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.entity.User;
import com.toy.foodiminder.repository.UserRepository;
import com.toy.foodiminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String join(@Valid @RequestBody UserDto userDto) throws Exception {
        return userService.signUp(userDto);
    }

    @PostMapping("/idDuplChk")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult idDuplChk(@RequestBody UserDto userDto) throws Exception {

        ApiResult result = new ApiResult();

        try {
            result.setResultCode(ResultCode.SUCCESS.getResultCode());
            result.setResultMessage(ResultCode.SUCCESS.getResultMessage());
            result.setResultData(userService.idDuplChk(userDto));
        } catch (Exception e) {
            // 예외 로그
            result.setResultCode(ResultCode.FAIL.getResultCode());
            result.setResultMessage(ResultCode.FAIL.getResultMessage());
            result.setResultData(false);
        }
        
        return result;
    }
}
