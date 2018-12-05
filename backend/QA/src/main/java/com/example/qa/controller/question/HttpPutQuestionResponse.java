package com.example.qa.controller.question;

public class HttpPutQuestionResponse {

  private final long createdAt;

  public HttpPutQuestionResponse(long createdAt) {
    this.createdAt = createdAt;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "HttpGetQuestionResponse : createdAt " + createdAt;
  }
}
