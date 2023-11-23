package com.meong.meongtwork.dto;

import lombok.Getter;
import lombok.Setter;

// 회원 가입 할때 들어오는 DTO
@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
}
