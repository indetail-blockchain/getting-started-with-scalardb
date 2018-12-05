package com.example.qa.service.question;

import com.example.qa.service.ServiceException;

public class QuestionNotFoundException extends ServiceException {
  public QuestionNotFoundException(String date, long createdAt) {
    super("The question with date : " + date + " and createdAt : " + createdAt + " does not exist");
  }
}
