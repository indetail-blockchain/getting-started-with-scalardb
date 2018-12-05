package com.example.qa.service;

public class UnknownTransactionStatusException extends ServiceException {
  public UnknownTransactionStatusException(String message, Throwable cause) {
    super(message, cause);
  }
}
