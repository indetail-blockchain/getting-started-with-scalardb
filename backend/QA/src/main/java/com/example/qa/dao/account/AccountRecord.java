package com.example.qa.dao.account;

public class AccountRecord {

  private final String email;
  private final String password;

  public AccountRecord(String email, String password) {
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
    return "AccountRecord : email " + email + ", password " + password;
  }
}
