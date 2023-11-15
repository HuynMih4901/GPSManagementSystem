package com.example.demo.utils;

import jakarta.persistence.Query;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;

public class GPSUtils {

  private GPSUtils() {
    throw new IllegalStateException("Utils class");
  }

  // tạo code
  public static String generateCode(String type, int index) {
    return type + String.format("%05d", index);
  }

  // mã hoá password
  public static String encodePassword(String password, String secretKey) {
    try {
      // Create a SecretKeySpec from the secretKey and choose the HMAC-SHA-256 algorithm
      SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");

      // Create a Mac instance and initialize it with the key
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(keySpec);

      // Calculate the HMAC of the password
      byte[] macBytes = mac.doFinal(password.getBytes());

      // Convert the result to a Base64 encoded string
      return Base64.getEncoder().encodeToString(macBytes);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      return null;
    }
  }

  public static String appendLikeExpression(String value) {
    if (StringUtils.isBlank(value)) {
      return null;
    }
    return String.format("%%%s%%", StringUtils.trim(value.toLowerCase()));
  }

  public static void setQueryParameters(Map<String, Object> queryParams, Query query) {
    if (!queryParams.isEmpty()) {
      queryParams.forEach(query::setParameter);
    }
  }
}
