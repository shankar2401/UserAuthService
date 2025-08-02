package org.example.userauthservice_2025.Services;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_2025.Exceptions.PasswordMismatchException;
import org.example.userauthservice_2025.Exceptions.UserAlreadySignedUpException;
import org.example.userauthservice_2025.Exceptions.UserNotRegisteredException;
import org.example.userauthservice_2025.Models.User;
import org.example.userauthservice_2025.Repos.UserRepo;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AuthService implements IAuthService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User signUp(String name, String email, String password, String phoneNumber) {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isPresent()) {
            throw new UserAlreadySignedUpException("Please login directly... or Sign up Some other email...");
        }
        User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(bCryptPasswordEncoder.encode(password));
        user1.setName(name);
        user1.setPhoneNumber(phoneNumber);
        return userRepo.save(user1);

    }

    @Override
    public Pair<User, String> login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UserNotRegisteredException("please sign up first...");
        }
        User user = userOptional.get();
        //if (!user.getPassword().equals(password)) {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMismatchException("Please add correct password...");
        }
        //token generation
//        String message = "{\n" +
//                "   \"email\": \"shankar@gmail.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"student\",\n" +
//                "      \"ta\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"2ndApril2026\"\n" +
//                "}";
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);
        Map<String,Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("userid", user.getId());
        claims.put("iss", "scaler");
         Long currentTimeMillis = System.currentTimeMillis();
        claims.put("gen", currentTimeMillis);
        claims.put("exp", currentTimeMillis+1000);
        MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
        SecretKey secretKey = macAlgorithm.key().build();
       // String token = Jwts.builder().content(content).compact();
         String token = Jwts.builder().claims(claims).signWith(secretKey).compact();


        return new Pair<>(user, token);
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        return null;
    }
}
