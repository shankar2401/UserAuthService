package org.example.userauthservice_2025.Services;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_2025.Models.User;

public interface IAuthService {
    User signUp(String name, String email, String password, String phoneNumber);
    Pair<User,String> login(String email, String password);
    Boolean validateToken(String token, Long userId);

}
