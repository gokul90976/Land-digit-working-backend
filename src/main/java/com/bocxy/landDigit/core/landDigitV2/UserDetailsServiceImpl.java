package com.bocxy.landDigit.core.landDigitV2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocxy.landDigit.core.landDigitV2.entity.User;
import com.bocxy.landDigit.core.landDigitV2.repository.UserRepo;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepo userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);


    return UserDetailsImpl.build(user);
  }

}
