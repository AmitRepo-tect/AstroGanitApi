package com.astroganit.security;

import com.astroganit.api.entities.User;
import com.astroganit.api.exception.ResourceNotFoundException;
import com.astroganit.api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
   @Autowired
   private UserRepo userRepo;

   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = (User)this.userRepo.findByMobile(username).orElseThrow(() -> {
         return new ResourceNotFoundException("user", "mobile: " + username, 0L);
      });
      return user;
   }
}
