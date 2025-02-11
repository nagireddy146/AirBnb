package com.airBnb_application.firstproject.Security;

import com.airBnb_application.firstproject.DTO.SignUpDto;
import com.airBnb_application.firstproject.DTO.UserDto;
import com.airBnb_application.firstproject.DTO.LoginDto;
import com.airBnb_application.firstproject.Entities.Enums.Roles;
import com.airBnb_application.firstproject.Entities.User;
import com.airBnb_application.firstproject.Exception.ResourceNotFoundException;
import com.airBnb_application.firstproject.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto signup(SignUpDto signUpDto){

        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());

        if(user.isPresent()){
            throw new IllegalStateException("User with email {} already found"+signUpDto.getEmail());

        }
        User newUser = modelMapper.map(signUpDto,User.class);
        newUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        newUser.setRoles( Set.of(Roles.GUEST));
        userRepository.save(newUser);

        return modelMapper.map(newUser, UserDto.class);


    }

    public String[] login(LoginDto loginDto){

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),loginDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        //if(authentication.isAuthenticated()){
            String[] tokens = new String[2];
            tokens[0]= jwtService.generateAccessToken(user);
            tokens[1]= jwtService.generateRefreshToken(user);
        //}
        return tokens;

    }
    public String refresh(String refreshToken){
        Long id = jwtService.getUserFromToken(refreshToken);
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found"));

        return jwtService.generateAccessToken(user);
    }
}
