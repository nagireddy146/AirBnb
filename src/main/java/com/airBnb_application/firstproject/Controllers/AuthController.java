package com.airBnb_application.firstproject.Controllers;

import com.airBnb_application.firstproject.DTO.LoginDto;
import com.airBnb_application.firstproject.DTO.LoginResponseDto;
import com.airBnb_application.firstproject.DTO.SignUpDto;
import com.airBnb_application.firstproject.DTO.UserDto;
import com.airBnb_application.firstproject.Security.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.CookieProcessor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>( authService.signup(signUpDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String[] arr= authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken",arr[1]);
        cookie.setHttpOnly(true);

        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(arr[0]) );
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest httpServletRequest){

        String refreshToken = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("No refresh Token was found"));

        String accessToken=authService.refresh(refreshToken);

        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }
}
