package org.example.userauthservice_2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequest {
    String token;
    Long id;
}
