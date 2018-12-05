package com.example.qa.controller.question;

import com.example.qa.dao.answer.AnswerRecord;
import com.example.qa.dao.question.QuestionRecord;
import java.util.ArrayList;
import java.util.List;

public class HttpGetQuestionResponse {

  private final String date;
  private final long createdAt;
  private final String title;
  private final String user;
  private final String context;
  private final long updatedAt;
  private final int numberOfAnswers;

  private final List<AnswerRecord> answers;

  public HttpGetQuestionResponse(QuestionRecord record, List<AnswerRecord> answers) {
    this.date = record.getDate();
    this.createdAt = record.getCreatedAt();
    this.title = record.getTitle();
    this.user = record.getUser();
    this.context = record.getContext();
    this.updatedAt = record.getUpdatedAt();
    this.numberOfAnswers = record.getNumberOfAnswers();
    this.answers = (answers == null) ? new ArrayList<>() : answers;
  }

  public List<AnswerRecord> getAnswers() {
    return answers;
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
    return "HttpGetQuestionResponse : createdAt "
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
        + numberOfAnswers
        + ", answers "
        + answers;
  }
}
