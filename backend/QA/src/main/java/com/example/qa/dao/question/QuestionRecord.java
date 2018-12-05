package com.example.qa.dao.question;

public class QuestionRecord {

  private final String date;
  private final long createdAt;
  private final String title;
  private final String user;
  private final String context;
  private final long updatedAt;
  private final int numberOfAnswers;

  public QuestionRecord(
      String date,
      long createdAt,
      String title,
      String user,
      String context,
      long updatedAt,
      int numberOfAnswers) {
    this.date = date;
    this.createdAt = createdAt;
    this.title = title;
    this.user = user;
    this.context = context;
    this.updatedAt = updatedAt;
    this.numberOfAnswers = numberOfAnswers;
  }

  public String getDate() {
    return date;
  }

  public long getCreatedAt() {
    return createdAt;
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

  public long getUpdatedAt() {
    return updatedAt;
  }

  public int getNumberOfAnswers() {
    return numberOfAnswers;
  }

  @Override
  public String toString() {
    return "QuestionRecord : id "
        + createdAt
        + ", title "
        + title
        + ", user "
        + user
        + ", context "
        + context
        + ", updatedAt "
        + updatedAt
        + ", numberOfAnswers "
        + numberOfAnswers;
  }
}
