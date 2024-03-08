package com.bocxy.landDigit.core.landDigitV2.EmailOTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;


@Service
public class GetEmailOtpServiceimp {
  @Autowired
  private JavaMailSender javaMailSender;

  public String SendMailtoUser(String username, String emailId, String oTP) throws Exception {
    String infomsg = "";
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      msg.setFrom("yazhinikuzhali98@gmail.com");
      MimeMessageHelper helper = new MimeMessageHelper(msg, true);
      helper.setTo(emailId);
      helper.setSubject("TNHB-Land-Digitization- OTP Request for Password Change from" + username);

      helper.setText("Hi,"
              + "User " + username + "  OTP detail is:" + oTP, true);


      javaMailSender.send(msg);

      infomsg = "Success";
    } catch (Exception ex) {
      throw ex;
    }
    return infomsg;
  }

}
