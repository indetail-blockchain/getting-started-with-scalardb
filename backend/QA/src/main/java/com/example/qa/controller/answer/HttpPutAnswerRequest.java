package com.example.qa.controller.answer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpPutAnswerRequest {

  private final long questionCreatedAt;
  private final String context;
  private final String user;

  @JsonCreator
  public HttpPutAnswerRequest(
      @JsonProperty("questionCreatedAt") long questionCreatedAt,
      @JsonProperty("context") String context,
      @JsonProperty("user") String user) {
    this.questionCreatedAt = questionCreatedAt;
    this.context = context;
    this.user = user;
  }

  public long getQuestionCreatedAt() {
    return questionCreatedAt;
  }

  public String getContext() {
    return context;
  }

  public String getUser() {
    return user;
  }

  @Override
  public String toString() {
    return "HttpPutAnswerRequest : questionCreatedAt "
        + questionCreatedAt
        + ", context "
        + context
        + ", user "
        + user;
  }
}
