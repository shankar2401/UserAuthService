package org.example.userauthservice_2025.Controllers;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_2025.Models.User;
import org.example.userauthservice_2025.Services.IAuthService;
import org.example.userauthservice_2025.dtos.LoginRequestDto;
import org.example.userauthservice_2025.dtos.SignUpRequestDto;
import org.example.userauthservice_2025.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    IAuthService authService;
    @PostMapping("/signup")
    public UserDto SignUp(@RequestBody SignUpRequestDto signUpRequestDto) {
      User user = authService.signUp(signUpRequestDto.getName(),signUpRequestDto.getEmail(),signUpRequestDto.getPassword(),signUpRequestDto.getPhoneNumber());
      return from(user);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Pair<User, String> pair1 = authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        UserDto userDto = from(pair1.a);
        String token = pair1.b;
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
    public  UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
