package com.hospitalmanagement.securityfilters;


import com.hospitalmanagement.entities.User;
import com.hospitalmanagement.repositories.UserRepository;
import com.hospitalmanagement.utility.JWTUtility;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTUtility jwtUtility ;
    private final UserRepository userRepository;

    public JWTAuthFilter(JWTUtility jwtUtility, UserRepository userRepository) {
        this.jwtUtility = jwtUtility;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           String authHeader =  request.getHeader("Authorization") ;
        System.out.println(authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

            String jwtToken = authHeader.substring(7); // remove "Bearer "
               System.out.println("JWT Token ==> " + jwtToken );
               String userId = jwtUtility.extractUserId(jwtToken);
               User retrivedUser =  userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException()) ;
               System.out.println(retrivedUser);

               if( retrivedUser != null && SecurityContextHolder.getContext().getAuthentication() == null ){

                   System.out.println("INSIDE");

                   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(retrivedUser,null,retrivedUser.getAuthorities());
                   System.out.println(usernamePasswordAuthenticationToken);
                   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

               }

               filterChain.doFilter(request,response);


    }
}
