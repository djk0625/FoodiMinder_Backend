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

    // 카카오 회원 정보 및 가입 여부 조회
    @GetMapping("/kakao/getAuthCode")
    @ResponseStatus
    public ApiResult getAuthCodeKakao(@RequestParam String code) {

        ApiResult result = new ApiResult();
        UserDto userDto = new UserDto();

        try {
            // 엑세스 토큰 발급
            System.out.println(code);
            String token = userService.getKakaoAuthToken(code);

            // 토큰 가지고 유저 정보 가져오기
            userDto.setUserEmail(userService.getKakaoUserInfo(token));

            if (!("".equals(userDto.getUserEmail()) || userDto.getUserEmail() == null)) {
                System.out.println(userDto.getUserEmail());
                // 해당 이메일로 DB 뒤져서 카운트 있으면 로그인 처리 없으면 신규가입
                // 있으면 로그인처리랑 유저데이터, 플래그 없으면 없는 플래그 프론트로 넘기면 될까
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String join(@Valid @RequestBody UserDto userDto) throws Exception {
        return userService.signUp(userDto);
    }

    @PostMapping("/idDuplChk")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult idDuplChk(@RequestBody UserDto userDto) {

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
