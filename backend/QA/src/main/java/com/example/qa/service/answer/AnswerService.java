package com.example.qa.service.answer;

import com.example.qa.controller.answer.HttpPutAnswerRequest;
import com.example.qa.service.ServiceException;

public interface AnswerService {

  /** Insert the answer in the storage */
  void put(HttpPutAnswerRequest request) throws ServiceException;
}
