package com.example.osahaneat.security;

import com.example.osahaneat.enity.Users;
import com.example.osahaneat.service.LoginService;
import com.example.osahaneat.service.imp.LoginServiceImp;
import com.example.osahaneat.util.JwtUtilHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtilHelper jwtUtilHelper;

//    @Autowired
//    LoginServiceImp loginService;

    @Autowired
    CustomUserDetailService customJwtFilter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromHeader(request);

        if(token != null){
            if(jwtUtilHelper.verifyToken(token)){
                int userId = jwtUtilHelper.getUserIdFromJWT(token);
//                Users userDetails = loginService.findUserById(userId);
                UserDetails userDetails = customJwtFilter.loadUserById(userId);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,new ArrayList<>());
                SecurityContext securityContext= SecurityContextHolder.getContext();
                securityContext.setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    public String getTokenFromHeader(HttpServletRequest request) {
        String requestToKen = request.getHeader("authorization");
        String token = null;
        if (requestToKen != null && requestToKen.startsWith("Bearer ")) {
            token = requestToKen.substring(7);
        }
        return token;
    }
}