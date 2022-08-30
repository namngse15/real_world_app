package com.example.real_world_app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.real_world_app.entity.User;
import com.example.real_world_app.model.token.TokenPayload;
import com.example.real_world_app.repository.UserRepository;
import com.example.real_world_app.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            final String requestTokenHeader = request.getHeader("Authorization");
            
            String token = null;
            TokenPayload tokenPayload = null;

            if (requestTokenHeader != null && requestTokenHeader.startsWith("Token")) {
                //todo
                token = requestTokenHeader.substring(6).trim();
                try {
                    tokenPayload = jwtTokenUtil.getTokenPayload(token);
                } catch (SignatureException e) {
                    System.out.println("Token is invalid");
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to get JWT token");
                } catch (ExpiredJwtException e) {
                    System.out.println("Token is expired");
                }
            } else {
                System.out.println("Jwt does not contain a token.");
            }           
            if(tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> userOptional = userRepository.findById(tokenPayload.getUid());
                if(userOptional.isPresent()) {
                    User user = userOptional.get();
                    if(jwtTokenUtil.validateToken(token, user)) {

                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        UserDetails userDetails = 
                            new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                             new UsernamePasswordAuthenticationToken(userDetails,null,authorities);

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
    }
    
}
