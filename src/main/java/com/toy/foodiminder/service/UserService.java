package com.toy.foodiminder.service;

import com.toy.foodiminder.common.model.ApiResult;
import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.entity.User;

public interface UserService {

    /**
     * 회원가입
     * @param userDto
     * @return String
     * @throws Exception
     */
    public String signUp(UserDto userDto) throws Exception;

    /**
     * id 중복체크
     * @param userDto
     * @return Boolean
     * @throws Exception
     */
    Boolean idDuplChk(UserDto userDto) throws Exception;

    /**
     * 카카오 엑세스 토큰 가져오기
     * @param code
     * @return String
     * @throws Exception
     */
    String getKakaoAuthToken(String code) throws Exception;

    /**
     * 카카오 회원 정보 가져오기
     * @param token
     * @return String
     * @throws Exception
     */
    String getKakaoUserInfo(String token) throws Exception;
}
