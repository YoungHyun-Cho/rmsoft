package com.example.rmsoft.domain.auth.filter;

import com.example.rmsoft.domain.auth.jwt.JwtTokenizer;
import com.example.rmsoft.domain.auth.userdetails.UserDetail;
import com.example.rmsoft.domain.auth.userdetails.UserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final UserDetailService userDetailService;
    private final JwtTokenizer jwtTokenizer;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        Boolean isSignUp = request.getRequestURI().equals("/users") && request.getMethod().equals("POST");
        Boolean isLogin = request.getRequestURI().equals("/auth/login") && request.getMethod().equals("POST");
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer") || isSignUp || isLogin;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);

            filterChain.doFilter(request, response);
        }
        catch (MalformedJwtException | ExpiredJwtException e) {

            handleExpiredJwtException(response, e);
        }

    }

    private static void handleExpiredJwtException(HttpServletResponse response, JwtException e) {

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String json = new ObjectMapper().writeValueAsString(e.getMessage());
            response.getWriter().write(json);
        }
        catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    private Map<String, Object> verifyJws(HttpServletRequest request) {

        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        Map<String, Object> claims = jwtTokenizer.getClaims(jws).getBody();

        return claims;
    }

    private void setAuthenticationToContext(Map<String, Object> claims) throws NoSuchElementException {

        String email = (String) claims.get("email");

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetailService.loadUserByUsername(email),
                null
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
