package com.farmix.quizapp.controller;

import com.farmix.quizapp.entity.QuestionWrapper;
import com.farmix.quizapp.entity.Response;
import com.farmix.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

  @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable int id){
        return quizService.getQuestion(id);
  }
  @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateRes(@PathVariable Integer id, @RequestBody List<Response> responses){
       return quizService.calculateRes(id, responses);
  }
}
