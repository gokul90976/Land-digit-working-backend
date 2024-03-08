package com.bocxy.landDigit.core.landDigitV2.ChangePassword;

import java.util.List;
import com.bocxy.landDigit.core.landDigitV2.entity.User;

public interface GetChangePasswordDao {
  List<User> updatealldetails(String username, String password);
}