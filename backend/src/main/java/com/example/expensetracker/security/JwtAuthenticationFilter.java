package com.example.expensetracker.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.MalformedJwtException;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
@AllArgsConstructor 
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain)
    throws ServletException, IOException{

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.substring(7);

        String userName = null;
        try{
            userName = jwtService.extractUserName(jwt);
        }catch(SignatureException e){
            log.warn("Jwt signature verification failed");
            filterChain.doFilter(request, response);
            return ;
        }catch(ExpiredJwtException e){
             log.warn("JWT Expired");
            filterChain.doFilter(request, response);
            return ;
        }catch(MalformedJwtException e){
             log.warn("JWT Malformed");
            filterChain.doFilter(request, response);
            return ;
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null ){

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

            if(!userDetails.isEnabled()){
                log.warn("Current User is disabled");
            filterChain.doFilter(request, response);
            return ;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );

            authentication.setDetails(
                new WebAuthenticationDetailsSource()
                .buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }
    
}
