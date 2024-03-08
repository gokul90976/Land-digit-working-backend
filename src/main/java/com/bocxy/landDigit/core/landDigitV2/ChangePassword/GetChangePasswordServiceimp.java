package com.bocxy.landDigit.core.landDigitV2.ChangePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bocxy.landDigit.core.landDigitV2.entity.User;

import java.util.List;


@Service

@Repository
public class GetChangePasswordServiceimp implements com.bocxy.landDigit.core.landDigitV2.ChangePassword.GetChangePasswordService {


  @Autowired
  PasswordEncoder encoder;

  @Autowired
  GetChangePasswordDao dao;


  public List<User> updatealldetails(String username, String password) {

    encoder.encode(password);

    return dao.updatealldetails(username, password);
  }


}


