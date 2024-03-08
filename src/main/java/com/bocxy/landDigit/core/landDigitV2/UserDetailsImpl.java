package com.bocxy.landDigit.core.landDigitV2;

import com.bocxy.landDigit.core.landDigitV2.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private final Long id;
  private final String username;
  private final String email;
  private final String role;
  private final String group_name;
  private final String privilege;
  private final String accesslevel1;
  private final String accesslevel2;
  private String loggedin;

  @JsonIgnore
  private final String password;


  public UserDetailsImpl(Long id, String username, String email,String role, String group_name, String privilege, String accesslevel1, String accesslevel2,String loggedin, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
    this.group_name = group_name;
    this.privilege = privilege;
    this.accesslevel1 = accesslevel1;
    this.accesslevel2 = accesslevel2;
    this.loggedin=loggedin;
    this.password = password;
  }

  public static UserDetailsImpl build(User user) {


    return new UserDetailsImpl(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole(),
            user.getGroup_name(),
            user.getPrivilege(),
            user.getAccesslevel1(),
            user.getAccesslevel2(),
            user.getLoggedin(),
            user.getPassword()
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public Long getId() {
    return id;
  }


  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public String getRole() {
    return role;
  }

  public String getGroup_name() {
    return group_name;
  }

  public String getPrivilege() {
    return privilege;
  }

  public String getAccesslevel1() {
    return accesslevel1;
  }

  public String getAccesslevel2() {
    return accesslevel2;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o)
//      return true;
//    if (o == null || getClass() != o.getClass())
//      return false;
//    UserDetailsImpl user = (UserDetailsImpl) o;
//    return Objects.equals(id, user.id);
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserDetailsImpl)) return false;
    UserDetailsImpl that = (UserDetailsImpl) o;
    return username.equals(that.username) &&

            password.equals(that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  public String getLoggedin() {
    return loggedin;
  }

  public void setLoggedin(String loggedin) {
    this.loggedin = loggedin;
  }

public String getEmail() {
	return email;
}

}