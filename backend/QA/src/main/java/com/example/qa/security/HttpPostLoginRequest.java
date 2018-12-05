package com.example.qa.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpPostLoginRequest {
  private final String email;
  private final String password;

  @JsonCreator
  public HttpPostLoginRequest(
      @JsonProperty("email") String email, @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "HttpPostLoginRequest : email " + email + ", password " + password;
  }
}
