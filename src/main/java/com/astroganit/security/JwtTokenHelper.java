package com.astroganit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenHelper {
   private String SECRET_KEY = "jwtTokenKey";
   public static final long JWT_TOKEN_VALIDITY = 18000L;

   public String getUsernameFromToken(String token) {
      return (String)this.getClaimFromToken(token, Claims::getSubject);
   }

   public Date getExpirationDateFromToken(String token) {
      return (Date)this.getClaimFromToken(token, Claims::getExpiration);
   }

   public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
      Claims claims = this.getAllClaimsFromToken(token);
      return claimsResolver.apply(claims);
   }

   private Claims getAllClaimsFromToken(String token) {
      return (Claims)Jwts.parser().setSigningKey(this.SECRET_KEY).parseClaimsJws(token).getBody();
   }

   private Boolean isTokenExpired(String token) {
      Date expiration = this.getExpirationDateFromToken(token);
      return expiration.before(new Date());
   }

   public String generateToken(UserDetails userDetails) {
      Map<String, Object> claims = new HashMap();
      return this.doGenerateToken(claims, userDetails.getUsername());
   }

   private String doGenerateToken(Map<String, Object> claims, String subject) {
      return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 18000000L)).signWith(SignatureAlgorithm.HS256, this.SECRET_KEY).compact();
   }

   public Boolean validateToken(String token, UserDetails userDetails) {
      String username = this.getUsernameFromToken(token);
      return username.equals(userDetails.getUsername()) && !this.isTokenExpired(token) ? true : false;
   }
}
