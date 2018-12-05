package com.example.qa.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/** Handle the user authentication with email and password for the /login route */
@Component
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
  private final JWTAuthenticationService JWTAuthenticationService;

  @Autowired
  public LoginFilter(
      AuthenticationManager authManager, JWTAuthenticationService JWTAuthenticationService) {
    super(new AntPathRequestMatcher("/login"));
    setAuthenticationManager(authManager);
    this.JWTAuthenticationService = JWTAuthenticationService;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException, IOException {
    HttpPostLoginRequest creds =
        new ObjectMapper().readValue(req.getInputStream(), HttpPostLoginRequest.class);
    return getAuthenticationManager()
        .authenticate(
            new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword()));
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
    JWTAuthenticationService.addAuthenticationHeader(res, auth.getName());
  }
}
