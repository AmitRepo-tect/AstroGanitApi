package com.astroganit.api.repository;

import com.astroganit.api.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
   Optional<User> findByMobile(String mobile);
   Optional<User> findById(int id);
   Optional<User> findByLoginId(String id);
   Optional<User> findByMobileAndDcrptpassword(String mobile, String password);
   Optional<User> findByLoginIdAndDcrptpassword(String mobile, String password);
}
