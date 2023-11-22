package com.example.demo.security;

import com.example.demo.enums.RoleEnum;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

  // Role của quản lý, super admin
  public static final Set<String> ADMINS = Set.of(RoleEnum.ADMIN.getCode());
  // Role của khách hàng
  public static final Set<String> CUSTOMERS =
      Set.of(RoleEnum.CUS.getCode(), RoleEnum.ADMIN.getCode());

  private static final long EXPIRE_DURATION_1_HOUR = 30L * 24 * 60 * 60 * 1000;
  private static final String JWT_SECRET = "abcdefghijklmnpqrstuvwyz1234567890";
  private static final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
  private static UserService userService;

  private SecurityUtils(UserService userService) {
    SecurityUtils.userService = userService;
  }

  public static String generateToken(User user) {
    Date now = new Date();
    Date expiredDate = new Date(now.getTime() + EXPIRE_DURATION_1_HOUR);

    return Jwts.builder()
        .setSubject(Integer.toString(user.getId()))
        .setIssuedAt(now)
        .setExpiration(expiredDate)
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  // method để authen, author
  public static void validateToken(String jwtToken, Set<String> allowRoles) {
    if (StringUtils.isBlank(jwtToken)) {
      throw new NullPointerException("Action requires providing of the authentication token");
    }
    DefaultClaims claims =
        (DefaultClaims)
            Jwts.parserBuilder().setSigningKey(key).build().parse(jwtToken.split(" ")[1]).getBody();
    if (Objects.isNull(claims)) {
      throw new NullPointerException();
    }
    if (claims.getExpiration().getTime() < System.currentTimeMillis()) {
      throw new IllegalStateException("Expired jwt token");
    }
    String[] tokens = claims.getSubject().split("~");
    int userId = Integer.parseInt(tokens[0]);
    User user = userService.getUser(userId);
    // role của user hiện tại
    Set<String> currentRoles =
        user.getRoles().stream().map(Role::getCode).collect(Collectors.toSet());

    if (!allowRoles.containsAll(currentRoles)) {
      throw new GPSException(ExceptionUtils.E_NOT_AUTHORIZE);
    }
  }
}
