package com.example.qa.controller.answer;

import com.example.qa.service.ServiceException;
import com.example.qa.service.answer.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("/answer")
public class AnswerController {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final AnswerService service;

  @Autowired
  AnswerController(AnswerService answerService) {
    this.service = answerService;
  }

  /**
   * PUT/answer
   *
   * @param request
   * @return
   */
  @PutMapping
  public void putAnswer(@RequestBody HttpPutAnswerRequest request) throws ServiceException {
    service.put(request);
  }
}
