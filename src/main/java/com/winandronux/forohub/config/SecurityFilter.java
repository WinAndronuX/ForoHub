package com.winandronux.forohub.config;

import com.winandronux.forohub.repository.UserRepository;
import com.winandronux.forohub.service.impl.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenServiceImpl tokenService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenServiceImpl tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var username = tokenService.getSubject(token);

            if (username != null) {
                var user = userRepository.findByUsername(username);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
