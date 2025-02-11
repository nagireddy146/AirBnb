package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.Entities.User;
import com.airBnb_application.firstproject.Exception.ResourceNotFoundException;
import com.airBnb_application.firstproject.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public User getUserFromId(Long id) {


        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with ID"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User not found with given details"));

    }
}
