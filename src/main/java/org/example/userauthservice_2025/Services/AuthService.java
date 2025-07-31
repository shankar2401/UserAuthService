package org.example.userauthservice_2025.Services;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_2025.Exceptions.PasswordMismatchException;
import org.example.userauthservice_2025.Exceptions.UserAlreadySignedUpException;
import org.example.userauthservice_2025.Exceptions.UserNotRegisteredException;
import org.example.userauthservice_2025.Models.User;
import org.example.userauthservice_2025.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    @Autowired
    UserRepo userRepo;
    @Override
    public User signUp(String name, String email, String password, String phoneNumber) {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isPresent()) {
            throw new UserAlreadySignedUpException("Please login directly... or Sign up Some other email...");
        }
        User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(password);
        user1.setName(name);
        user1.setPhonenumber(phoneNumber);
        return userRepo.save(user1);

    }

    @Override
    public Pair<User, String> login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UserNotRegisteredException("please sign up first...");
        }
        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            throw new PasswordMismatchException("Please add correct password...");
        }
        return new Pair<>(user, user.getEmail());
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        return null;
    }
}
