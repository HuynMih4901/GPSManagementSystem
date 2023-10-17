package com.example.demo.exceptions;

import java.util.Map;

public class ExceptionUtils {

  public static final String E_INTERNAL_SERVER = "E_INTERNAL_SERVER";
  public static final String E_RECORD_NOT_EXIST = "E_RECORD_NOT_EXIST";
  public static final String E_NOT_FOUND_PATH = "E_NOT_FOUND_PATH";
  public static final String E_USERNAME_PASSWORD_NOT_VALID = "E_USERNAME_PASSWORD_NOT_VALID";
  public static final String E_USERNAME_EXISTED = "E_USERNAME_EXISTED";
  public static final String E_EMAIL_EXISTED = "E_EMAIL_EXISTED";

  public static final Map<String, String> MESSAGES = Map.of(
          E_INTERNAL_SERVER, "Server không phản hồi",
          E_RECORD_NOT_EXIST, "Bản ghi không tồn tại",
          E_NOT_FOUND_PATH, "Đường dẫn không tồn tại",
          E_USERNAME_PASSWORD_NOT_VALID, "Tài khoản hoặc mật khẩu không chính xác hoặc không tồn tại",
          E_USERNAME_EXISTED, "Tên đăng nhập đã tồn tại",
          E_EMAIL_EXISTED, "Email đã tồn tại"
  );

  private ExceptionUtils() {
    throw new IllegalStateException("ExceptionUtils class");
  }
}
