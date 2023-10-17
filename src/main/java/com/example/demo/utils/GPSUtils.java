package com.example.demo.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GPSUtils {

  private GPSUtils() {
    throw new IllegalStateException("Utils class");
  }

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
}
