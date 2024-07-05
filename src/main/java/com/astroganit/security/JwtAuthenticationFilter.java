package com.astroganit.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   @Autowired
   private UserDetailsService userDetailService;
   @Autowired
   private JwtTokenHelper jstTokenHelper;

   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String requestToken = request.getHeader("Authorization");
      System.out.println("requestToken -> " + requestToken);
      String username = null;
      String token = null;
      if (requestToken != null && requestToken.startsWith("Bearer")) {
         token = requestToken.substring(7);

         try {
            username = this.jstTokenHelper.getUsernameFromToken(token);
         } catch (IllegalArgumentException var9) {
            System.out.println("unable to get jwt token");
         } catch (ExpiredJwtException var10) {
            System.out.println("jwt token has expired");
         } catch (MalformedJwtException var11) {
            System.out.println("Invalid jwt");
         }
      } else {
         System.out.println("jwt token does not begin with bearer or requestToken is null");
      }

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
         if (this.jstTokenHelper.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, (Object)null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("token generated successfully");
         } else {
            System.out.println("invalid jwt token");
         }
      } else {
         System.out.println("username is null or context is null");
      }

      filterChain.doFilter(request, response);
   }
}
