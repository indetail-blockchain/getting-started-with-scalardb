package com.example.qa.controller;

import com.example.qa.service.ServiceException;
import com.example.qa.service.question.QuestionNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultExceptionHandler {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @ResponseBody
  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<String> serviceExceptionHandler(HttpServletRequest req, Exception e) {
    log.error("Request: " + req.getRequestURL() + " raised " + e, e);
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    if (e instanceof QuestionNotFoundException) {
      httpStatus = HttpStatus.NOT_FOUND;
    }
    return new ResponseEntity<>("Server error", httpStatus);
  }
}
