package com.afterdrawing.backendapi.sercurity;

import com.afterdrawing.backendapi.service.UserDetailsService;
import com.afterdrawing.backendapi.service.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final UserDetailsServiceImpl userDetailService;

    private  JwtCenter jwtCenter;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String token;
        SecurityContext context = SecurityContextHolder.getContext();
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            token = requestTokenHeader.substring(7);
            System.out.println("Token: " + token);
            jwtCenter.setToken(token);
            try {
                username = jwtCenter.getUsername();
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not start with Bearer String.");
        }
        if(username != null && context.getAuthentication() == null) {
            UserDetails userDetails = userDetailService
                    .loadUserByUsername(username);
            if (jwtCenter.validate(userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
