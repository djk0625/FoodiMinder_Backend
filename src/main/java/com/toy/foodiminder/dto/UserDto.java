package com.toy.foodiminder.dto;

import com.toy.foodiminder.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;

    @NotBlank(message = "이메일을 입력해주세요")
    private String userEmail;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, message = "이름이 너무 짧습니다.")
    private String userName;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    private String userPhone;

    @NotBlank(message = "비밀번호를 입력해주세요")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
//            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String userPass;

    private String checkedPassword;

    @Builder
    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userEmail(userEmail)
                .userName(userName)
                .userPhone(userPhone)
                .userPass(userPass)
                .build();
    }
}
