package com.bocxy.landDigit.core.landDigitV2.ChangePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.bocxy.landDigit.core.landDigitV2.entity.User;
import java.util.List;


@RestController
@CrossOrigin("*")


@RequestMapping(value = "/api")
public class GetChangePasswordController {

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private GetChangePasswordService service;


  @RequestMapping(method = RequestMethod.POST, path = "/changepassword", consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<User> updateAllemployees(@RequestBody User user) {

	  return (service.updatealldetails(user.getUsername(), encoder.encode(user.getPassword())));
	  
  }

}




	
	






