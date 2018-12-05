package com.example.qa.dao.answer;

public class AnswerRecord {

  private final long questionCreatedAt;
  private final long createdAt;
  private final String context;
  private final String user;

  public AnswerRecord(long questionCreatedAt, long createdAt, String context, String user) {
    this.questionCreatedAt = questionCreatedAt;
    this.createdAt = createdAt;
    this.context = context;
    this.user = user;
  }

  public long getQuestionCreatedAt() {
    return questionCreatedAt;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public String getContext() {
    return context;
  }

  public String getUser() {
    return user;
  }

  @Override
  public String toString() {
    return "AnswerRecord : questionCreatedAt "
        + questionCreatedAt
        + ", createdAt "
        + createdAt
        + ", context "
        + context
        + ", user "
        + user;
  }
}
