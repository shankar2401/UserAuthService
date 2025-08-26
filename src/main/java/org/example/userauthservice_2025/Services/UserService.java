package org.example.userauthservice_2025.Services;

import org.example.userauthservice_2025.Models.User;
import org.example.userauthservice_2025.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User getUserDetailsBasedonId(Long id) {
        Optional<User> user = userRepo.findById(id);
        System.out.println(user.get().getEmail());
        if (!user.isPresent()) {
            return null;
        }
        System.out.println(user.get().getEmail());
        return user.get();
    }
}
