package com.example.qa.security;

import com.example.qa.dao.ScalarDbManager;
import com.example.qa.dao.account.AccountDao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/** Javascript Web Token (JWT) based authentication */
@Component("com.example.qa.security.JWTAuthenticationService")
abstract class JWTAuthenticationService {
  protected static final long EXPIRATION_TIME = 864_000_000; // 10 days
  protected static final String SECRET = "Q&A_secret_token";
  protected static final String TOKEN_PREFIX = "Bearer";
  protected static final String HEADER_STRING = "Authorization";

  protected final AccountDao accountDao;

  @Autowired
  public JWTAuthenticationService(AccountDao accountDao, ScalarDbManager scalarDbManager) {
    this.accountDao = accountDao;
  }

  protected void addAuthenticationHeader(HttpServletResponse res, String username) {
    String JWT =
        Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
  }

  /**
   * Return an authentication object
   *
   * @param request the HTTP request
   * @return if the authentication is successful, return an Authentication object
   */
  abstract Authentication verifyAuthentication(HttpServletRequest request)
      throws AuthenticationException;
}
