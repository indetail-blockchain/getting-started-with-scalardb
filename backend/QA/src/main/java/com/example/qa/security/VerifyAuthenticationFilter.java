package com.example.qa.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/** Verify the JWT validity */
@Component
public class VerifyAuthenticationFilter extends GenericFilterBean {
  private final JWTAuthenticationService authenticationService;

  @Autowired
  VerifyAuthenticationFilter(JWTAuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    Authentication authentication = null;
    try {
      authentication = authenticationService.verifyAuthentication((HttpServletRequest) request);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (AuthenticationException e) {
      logger.error("Error verifying JWT", e);
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      httpResponse.getWriter().write("Error verifying JWT");
    }
  }
}
