package org.example.userauthservice_2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String password;
    private String email;
}
