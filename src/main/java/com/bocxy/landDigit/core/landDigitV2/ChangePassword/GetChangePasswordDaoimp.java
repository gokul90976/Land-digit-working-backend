package com.bocxy.landDigit.core.landDigitV2.ChangePassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.bocxy.landDigit.core.landDigitV2.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GetChangePasswordDaoimp implements GetChangePasswordDao {
  @Autowired
  NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  public static void main(String[] args) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://tnhb-landdigit.cvk3pspk8bzn.ap-south-1.rds.amazonaws.com/landdigit_db");
    dataSource.setUsername("root");
    dataSource.setPassword("yazhini1998");
  }
  public List<User> updatealldetails(String username, String password) {

    String sqlQuery = " Update landdigit_db.users SET password = '" + password + "'"
            + " WHERE username = '" + username + "' ";
    String DB_URL = "jdbc:mysql://tnhb-landdigit.cvk3pspk8bzn.ap-south-1.rds.amazonaws.com/landdigit_db";
    String USER = "root";
    String PASS = "yazhini1998";
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()
    ) {
      stmt.executeUpdate(sqlQuery);
    } catch (SQLException e) {
      System.out.println(e);
    }
    List<User> domain = new ArrayList<>();
    String sqlQuery1 = " Select * from landdigit_db.users ";
    domain = namedParameterJdbcTemplate.query(sqlQuery1, new BeanPropertyRowMapper<>(User.class));
    return domain;
  }
}