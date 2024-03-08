package com.bocxy.landDigit.core.landDigitV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.User;



@Repository
public interface UserRepo extends JpaRepository<User, Long> {

  User findByAdmin(String admin);

  User findByUsername(String username);


}
