package org.example.userauthservice_2025.OAuth;

import org.example.userauthservice_2025.Models.User;
import org.example.userauthservice_2025.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("user not found");
        }
        return new StorageUserDetails(optionalUser.get());
    }
}
