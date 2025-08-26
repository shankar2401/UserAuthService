package org.example.userauthservice_2025.Controllers;

import jakarta.annotation.security.PermitAll;
import org.example.userauthservice_2025.Models.User;
import org.example.userauthservice_2025.Repos.UserRepo;
import org.example.userauthservice_2025.Services.IAuthService;
import org.example.userauthservice_2025.Services.IUserService;
import org.example.userauthservice_2025.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/users")
@RestController
public class UserController {
@Autowired
private IUserService iUserService;

@GetMapping("/users/{Id}")
  public UserDto getUserdetails(@PathVariable Long Id) {
    return from(iUserService.getUserDetailsBasedonId(Id));
}
private UserDto from(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setEmail(user.getEmail());
    userDto.setName(user.getName());
    return userDto;
};

}
