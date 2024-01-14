package com.example.rmsoft.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "비밀번호는 8~16자의 알파벳 문자와 숫자의 조합이어야 합니다.")
    private String password;

    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$", message = "휴대폰 번호는 010을 포함한 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String mobile;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "회사명을 입력해주세요.")
    private String company;
}
