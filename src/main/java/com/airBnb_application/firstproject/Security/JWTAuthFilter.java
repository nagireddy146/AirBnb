package com.airBnb_application.firstproject.Security;

import com.airBnb_application.firstproject.Entities.User;
import com.airBnb_application.firstproject.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestHeaderToken = request.getHeader("Authorization");

        if(requestHeaderToken == null || !requestHeaderToken.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestHeaderToken.substring(7).trim();
        System.out.println("JWT Token: " + token);


        Long userid = jwtService.getUserFromToken(token);

        if( userid != null && SecurityContextHolder.getContext().getAuthentication()==null){

            User user = userService.getUserFromId(userid);

            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());

//            authenticationToken.setDetails(
//                    new WebAuthenticationDetailsSource().buildDetails(request)
//            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("UserId got from token is"+SecurityContextHolder.getContext());

        }
        filterChain.doFilter(request,response);

    }
}
