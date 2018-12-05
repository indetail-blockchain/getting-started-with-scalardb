package com.example.qa.controller.question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpPutQuestionRequest {

  private final String title;
  private final String user;
  private final String context;

  @JsonCreator
  public HttpPutQuestionRequest(
      @JsonProperty("title") String title,
      @JsonProperty("user") String user,
      @JsonProperty("context") String context) {
    this.title = title;
    this.user = user;
    this.context = context;
  }

  public String getTitle() {
    return title;
  }

  public String getUser() {
    return user;
  }

  public String getContext() {
    return context;
  }

  @Override
  public String toString() {
    return "HttpPutQuestionRequest : title " + title + ", user " + user + ", context " + context;
  }
}
