package com.toy.foodiminder.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.toy.foodiminder.dto.UserDto;
import com.toy.foodiminder.entity.User;
import com.toy.foodiminder.repository.UserRepository;
import com.toy.foodiminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

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

        try {
            if (userRepository.findByUserId(userDto.getUserId()).isPresent()) {
                return false;
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return true;
    }

    @Transactional
    @Override
    public String getKakaoAuthToken(String code) throws Exception {
        String accessToken = "";
        String refreshToken = "";
        String requestURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=5d6866579ca70b4f2ea3e7dd9ab419d7");
            // 운영 반영 시 수정
            sb.append("&redirect_uri=http://localhost:8080/api/user/kakao/getAuthCode");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                // TODO:: 커스텀 Exception 핸들러 구현 필요
                throw new Exception("카카오톡 로그인 API 인증 토큰 조회 실패");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println("response body : " + result);

            JsonElement element = JsonParser.parseString(String.valueOf(result));

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);

            br.close();
            bw.close();

        } catch (MalformedURLException e) {
            throw new Exception(e);
        } catch (ProtocolException e) {
            throw new Exception(e);
        } catch (IOException e) {
            throw new Exception(e);
        }

        return accessToken;
    }

    @Transactional
    @Override
    public String getKakaoUserInfo(String token) throws Exception {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String email = "";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("카카오톡 로그인 API 사용자 조회 실패");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();

            String line = "";
            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            JsonElement element = JsonParser.parseString(result.toString());
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            if (!("".equals(kakaoAccount.getAsJsonObject().get("email")) || kakaoAccount.getAsJsonObject().get("email") == null)) {
                email = kakaoAccount.getAsJsonObject().get("email").getAsString();
            }
            System.out.println("email ==== " + email);

        } catch (MalformedURLException e) {
            throw new Exception();
        } catch (IOException e) {
            throw new Exception();
        }

        return email;
    }

}
