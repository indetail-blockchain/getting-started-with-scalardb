package com.example.qa.controller.question;

import com.example.qa.service.ServiceException;
import com.example.qa.service.question.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

  private final QuestionService service;

  @Autowired
  QuestionController(QuestionService questionService) {
    this.service = questionService;
  }
  /**
   * PUT/question
   *
   * @param request
   * @return
   */
  @PutMapping
  public HttpPutQuestionResponse putQuestion(@RequestBody HttpPutQuestionRequest request)
      throws ServiceException {
    HttpPutQuestionResponse result = service.put(request);
    return result;
  }

  /**
   * GET/question/{id}
   *
   * @param id
   * @return
   */
  @GetMapping(value = "/{id}")
  public HttpGetQuestionResponse getQuestionDetail(@PathVariable("id") long id)
      throws ServiceException {
    HttpGetQuestionResponse result = service.get(id);
    return result;
  }

  /**
   * GET/question
   *
   * @return
   */
  @GetMapping
  public List<HttpGetQuestionResponse> getQuestions(
      @RequestParam("start") String start, @RequestParam("minimal") int minimal)
      throws ServiceException {
    List<HttpGetQuestionResponse> result = service.get(start, minimal);
    return result;
  }
}
