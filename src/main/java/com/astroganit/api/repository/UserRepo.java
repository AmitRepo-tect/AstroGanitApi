package com.astroganit.api.repository;

import com.astroganit.api.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
   Optional<User> findByMobile(String mobile);

   Optional<User> findByMobileAndDcrptpassword(String mobile, String password);
}
