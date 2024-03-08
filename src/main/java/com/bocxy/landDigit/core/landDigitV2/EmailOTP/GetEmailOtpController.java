package com.bocxy.landDigit.core.landDigitV2.EmailOTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.bocxy.landDigit.core.landDigitV2.entity.User;
import com.bocxy.landDigit.core.landDigitV2.repository.RepoVerifyOtp;
import com.bocxy.landDigit.core.landDigitV2.repository.UserRepo;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController

@RequestMapping(value = "/api")
public class GetEmailOtpController {

  @Autowired
  GetEmailOtpServiceimp lEmailServices;

  @Autowired
  private RepoVerifyOtp verifyotp;
  
  @Autowired
  UserRepo userRepository;

  @PostMapping("/SendOtp")
  public String ForgetPassword(@RequestBody User lSignUpdetail) throws Exception {

    String msg = "";
    String OTP;
    String username = lSignUpdetail.getUsername();
    String EmailId = "yazhinikuzhali98@gmail.com";
    GetEmailOtpDaoimp lLoginlogic = new GetEmailOtpDaoimp();
    try {
    	
    	User uservalue = userRepository.findByUsername(username);
    	if(uservalue != null){
      OTP = lLoginlogic.GenetrateOTP();
      if (username != "" && username != null) {

        msg = lEmailServices.SendMailtoUser(username, EmailId, OTP);

        if (msg == "Success") {
          msg = lLoginlogic.UpdateOTPbyEmailSent(username, EmailId, OTP);
        }
      }
      
    }
      return msg;
    } 
    
    
    catch (Exception ex) {
      throw ex;
    }

  }


  @RequestMapping(method = RequestMethod.POST, path = "/VerifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE)
  public List<User> getAllemployee(@RequestBody User user) {


    return verifyotp.findByUsernameAndOtp
            (user.getUsername(), user.otp);

  }


}
