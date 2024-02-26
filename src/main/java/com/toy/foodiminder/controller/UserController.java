package com.toy.foodiminder.controller;

import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.repository.UserRepository;
import com.toy.foodiminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String join(@Valid @RequestBody UserDto userDto) throws Exception {
        return userService.signUp(userDto);
    }
}
