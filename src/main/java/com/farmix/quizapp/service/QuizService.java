package com.farmix.quizapp.service;

import com.farmix.quizapp.dao.QuestionDao;
import com.farmix.quizapp.dao.QuizDAO;
import com.farmix.quizapp.entity.Question;
import com.farmix.quizapp.entity.QuestionWrapper;
import com.farmix.quizapp.entity.Quiz;
import com.farmix.quizapp.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDAO quizDAO;

    @Autowired
    private QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        Pageable pageable = PageRequest.of(0, numQ);
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, pageable);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

       return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestion(int id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> calculateRes(Integer id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int res = 0;
        int i = 0;
        for(Response q : responses){
            if(q.getResponse().equals(questions.get(i).getRight_answer())) res++;

            i++;
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
