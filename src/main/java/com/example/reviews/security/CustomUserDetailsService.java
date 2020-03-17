package com.example.reviews.security;

import com.example.reviews.model.Role;
import com.example.reviews.model.User;
import com.example.reviews.service.UserService;

import java.util.Optional;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByEmail(email);
        UserBuilder userBuilder = null;
        if (user.isPresent()) {
            userBuilder =  org.springframework.security.core.userdetails.User.withUsername(email);
            userBuilder.password(user.get().getPassword());
            String[] roleNames = user.get().getRoles()
                    .stream()
                    .map(Role::getRoleName)
                    .toArray(String[]::new);
            userBuilder.roles(roleNames);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return userBuilder.build();
    }
}
