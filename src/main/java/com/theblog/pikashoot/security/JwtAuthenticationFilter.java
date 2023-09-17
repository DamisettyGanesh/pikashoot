package com.theblog.pikashoot.security;


import com.theblog.pikashoot.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig tokenGenerator;

    @Autowired
    private AuthService userDetailsService;

    // This method is called for each incoming HTTP request.
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Extract the JWT token from the request.
        String token = getJWTFromRequest(request);

        // Check if the token is not empty and is valid.
        if (StringUtils.hasText(token) && tokenGenerator.validateToken(token)) {
            // Extract the username from the token.
            String username = tokenGenerator.getUsernameFromToken(token);

            // Load user details (including authorities/roles) from the database based on the username.
            UserDetails user = userDetailsService.loadUserByUsername(username);

            // Create an authentication token for the user.
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null, // Credentials (password) are not used in this case
                    user.getAuthorities() // Granted authorities (roles) from the user details
            );

            // Set authentication details based on the incoming request.
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set the user's authentication in the security context.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continue processing the request chain.
        filterChain.doFilter(request, response);
    }

    // Helper method to extract the JWT token from the request.
    public String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Extract the token part after "Bearer "
            return bearerToken.substring(7);
        }

        return null; // No token found in the request.
    }

}
