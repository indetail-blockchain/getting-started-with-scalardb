package com.example.qa.security;

import com.example.qa.dao.account.AccountRecord;
import com.example.qa.service.ServiceException;
import com.example.qa.service.account.AccountService;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configure security parameters for HTTP requests and define the authentication process based on
 * JWT
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final AccountService accountService;
  private final LoginFilter loginFilter;
  private final VerifyAuthenticationFilter verifyAuthenticationFilter;

  @Autowired
  public WebSecurityConfig(
      AccountService accountService,
      LoginFilter loginFilter,
      VerifyAuthenticationFilter verifyAuthenticationFilter) {
    super();
    this.accountService = accountService;
    this.loginFilter = loginFilter;
    this.verifyAuthenticationFilter = verifyAuthenticationFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    initializeAccounts();
    http.cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        // Handle the user authentication with email and password for the /login route
        // Return JWT Token upon success
        .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
        // Other routes except /login needs to provide a valid JWT Token
        .addFilterBefore(verifyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  /**
   * @see <a
   *     href="https://spring.io/understanding/CORS">https://spring.io/understanding/CORS</a></a>
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
    configuration.setExposedHeaders(Arrays.asList("Authorization"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  private void initializeAccounts() {
    for (AccountRecord acc : getAccounts()) {
      try {
        if (accountService.get(acc.getEmail()) == null) {
          accountService.put(acc.getEmail(), acc.getPassword());
          log.info("Account " + acc.getEmail() + " has been registered");
        } else {
          log.info("Account " + acc.getEmail() + " is already registered");
        }
      } catch (ServiceException e) {
        log.error("Error initializing user " + acc.getEmail());
      }
    }
  }

  private List<AccountRecord> getAccounts() {
    return Arrays.asList(
        new AccountRecord("user1@example.com", "user1"),
        new AccountRecord("user2@example.com", "user2"),
        new AccountRecord("user3@example.com", "user3"));
  }
}
