package com.example.qa.service.question;

import com.example.qa.controller.question.HttpGetQuestionResponse;
import com.example.qa.controller.question.HttpPutQuestionRequest;
import com.example.qa.controller.question.HttpPutQuestionResponse;
import com.example.qa.service.ServiceException;
import java.util.List;

public interface QuestionService {

  /** Insert the question in the storage */
  HttpPutQuestionResponse put(HttpPutQuestionRequest question) throws ServiceException;

  /** Retrieve the question by its id* */
  HttpGetQuestionResponse get(long id) throws ServiceException;

  /**
   * Retrieve the questions by targeting a starting day. If the number of questions for that day is
   * not enough to to satisfy the minimal numbers of results desired. It will also retrieve the
   * questions of the previous day until the minimal number of results is satisfied.
   *
   * @param start a day
   * @param minimal a minimal number of questions desired
   * @return a list of questions sorted in ascending order by its creation date
   * @throws ServiceException
   */
  List<HttpGetQuestionResponse> get(String start, int minimal) throws ServiceException;
}
