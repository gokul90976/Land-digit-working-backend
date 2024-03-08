package com.bocxy.landDigit.core.landDigitV2.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private String role;
  private String group_name;
  private String admin;
  private String password;
  private String devteam;
  private String prodaccess;
  private String privilege;
  private String accesslevel1;
  private String accesslevel2;
  public String loggedin;
  public String phoneNumber ;
  private String username;
  public String email;
  public String otp;
  public String citynrural;
  public String division;

  public User(String username, String email, String password, String role, String phoneNumber) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.phoneNumber=phoneNumber;
  }



}