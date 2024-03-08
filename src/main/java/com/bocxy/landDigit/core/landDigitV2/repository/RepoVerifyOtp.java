package com.bocxy.landDigit.core.landDigitV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bocxy.landDigit.core.landDigitV2.entity.User;
import java.util.List;

@Repository
public interface RepoVerifyOtp extends JpaRepository<User, Long> {

  List<User> findByUsernameAndOtp(String username, String otp);

  List<User> findByEmailAndOtp(String email, String otp);

}
