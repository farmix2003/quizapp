package com.farmix.quizapp.controller;

import com.farmix.quizapp.entity.Question;
import com.farmix.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:300")
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getData(){
      return questionService.getAllQuestions();
    }

    @GetMapping("/category/{topic}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String topic){
     return questionService.getQuestionsByCategory(topic);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @DeleteMapping("/delete/{id}")
        public void deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);

    }
}
