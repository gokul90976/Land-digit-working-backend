package com.bocxy.landDigit.core.landDigitV2;


public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String role;
  private String group_name;
  private String privilege;
  private String accesslevel1;
  private String accesslevel2;
  private String loggedin;
  public JwtResponse(String accessToken, Long id, String username, String role, String group_name, String privilege, String accesslevel1, String accesslevel2,String loggedin) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.role = role;
    this.group_name = group_name;
    this.privilege = privilege;
    this.accesslevel1 = accesslevel1;
    this.accesslevel2 = accesslevel2;
    this.setLoggedin(loggedin);
  }
  public String getAccessToken() {
    return token;
  }
  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }
  public String getTokenType() {
    return type;
  }
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
  public String getGroup_name() {
    return group_name;
  }
  public void setGroup_name(String group_name) {
    this.group_name = group_name;
  }
  public String getPrivilege() {
    return privilege;
  }
  public void setPrivilege(String privilege) {
    this.privilege = privilege;
  }
  public String getAccesslevel1() {
    return accesslevel1;
  }
  public void setAccesslevel1(String accesslevel1) {
    this.accesslevel1 = accesslevel1;
  }
  public String getAccesslevel2() {
    return accesslevel2;
  }
  public void setAccesslevel2(String accesslevel2) {
    this.accesslevel2 = accesslevel2;
  }
  public String getLoggedin() {
    return loggedin;
  }

  public void setLoggedin(String loggedin) {
    this.loggedin = loggedin;
  }
}