package com.bocxy.landDigit.core.landDigitV2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.bocxy.landDigit.core.landDigitV2.repository.UserRepo;



@CrossOrigin(origins = "localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
	
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepo userRepository;


  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @RequestMapping(method = RequestMethod.POST, path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> authenticateUser(@RequestBody com.bocxy.landDigit.core.landDigitV2.model.LoginRequest loginRequest) throws Exception {
    String username=loginRequest.getUsername();
    String msg="";
    Loggedinimp loggedin=new Loggedinimp();
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()  ));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    msg=loggedin.Updateloggedin(username);
    return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),userDetails.getRole(),userDetails.getGroup_name(),userDetails.getPrivilege(),
            userDetails.getAccesslevel1(),userDetails.getAccesslevel2(),userDetails.getLoggedin()));


  }

  @RequestMapping( method=RequestMethod.POST,path="/logout",consumes=MediaType.APPLICATION_JSON_VALUE)
  public boolean authenticateUserlogout(@RequestBody com.bocxy.landDigit.core.landDigitV2.model.LoginRequest loginRequest) throws Exception {
    String username=loginRequest.getUsername();
    String msg="";
    Loggedinimp loggedin=new Loggedinimp();
    msg=loggedin.Updateloggedout(username);
    return true;

  }


}