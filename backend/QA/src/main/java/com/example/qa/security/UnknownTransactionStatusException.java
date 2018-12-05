package com.example.qa.security;

public class UnknownTransactionStatusException extends AuthenticationException {
  public UnknownTransactionStatusException(String message, Throwable cause) {
    super(message, cause);
  }
}
