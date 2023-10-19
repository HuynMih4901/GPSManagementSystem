package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {

  public static final String E_INTERNAL_SERVER = "E_INTERNAL_SERVER";
  public static final String E_RECORD_NOT_EXIST = "E_RECORD_NOT_EXIST";
  public static final String E_NOT_FOUND_PATH = "E_NOT_FOUND_PATH";
  public static final String E_USERNAME_PASSWORD_NOT_VALID = "E_USERNAME_PASSWORD_NOT_VALID";
  public static final String E_USERNAME_EXISTED = "E_USERNAME_EXISTED";
  public static final String E_EMAIL_EXISTED = "E_EMAIL_EXISTED";
  public static final String E_ROLE_NOT_EXISTED = "E_ROLE_NOT_EXISTED";
  public static final String E_SIGN_UP_ERROR = "E_SIGN_UP_ERROR";
  public static final String E_PHONE_EXISTED = "E_PHONE_EXISTED";
  public static final String E_ADDRESS_ERROR = "E_ADDRESS_ERROR";
  public static final String E_NOT_AUTHORIZE = "E_NOT_AUTHORIZE";
  public static final String E_RECORD_EXIST = "E_RECORD_EXIST";

  public static final Map<String, String> MESSAGES = new HashMap<>();

  static {
    MESSAGES.put(E_INTERNAL_SERVER, "Server không phản hồi");
    MESSAGES.put(E_RECORD_NOT_EXIST, "Bản ghi không tồn tại");
    MESSAGES.put(E_NOT_FOUND_PATH, "Đường dẫn không tồn tại");
    MESSAGES.put(E_USERNAME_PASSWORD_NOT_VALID, "Tài khoản hoặc mật khẩu không chính xác hoặc không tồn tại");
    MESSAGES.put(E_USERNAME_EXISTED, "Tên đăng nhập đã tồn tại");
    MESSAGES.put(E_EMAIL_EXISTED, "Email đã tồn tại");
    MESSAGES.put(E_ROLE_NOT_EXISTED, "Role không tồn tại");
    MESSAGES.put(E_SIGN_UP_ERROR, "lỗi đăng ký");
    MESSAGES.put(E_PHONE_EXISTED, "Số điện thoại đã tồn tại");
    MESSAGES.put(E_ADDRESS_ERROR, "Lỗi địa chỉ");
    MESSAGES.put(E_NOT_AUTHORIZE, "Không có quyền truy cập");
    MESSAGES.put(E_RECORD_EXIST, "Bản ghi bị trùng");
  }


  private ExceptionUtils() {
    throw new IllegalStateException("ExceptionUtils class");
  }
}
