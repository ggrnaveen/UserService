package com.example.userservice_3.security.services;

import com.example.userservice_3.models.User;
import com.example.userservice_3.repositories.UserRepository;
import com.example.userservice_3.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> userOptional = userRepository.findByEmail(username);

        if(userOptional.isEmpty())
            throw  new UsernameNotFoundException("User by email "+ username +" not exists");

        CustomUserDetails userDetails = new CustomUserDetails(userOptional.get());

        return userDetails;
    }
}
