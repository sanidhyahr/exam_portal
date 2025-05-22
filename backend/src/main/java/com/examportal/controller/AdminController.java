package com.examportal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.Exam;
import com.examportal.model.Question;
import com.examportal.model.User;
import com.examportal.repository.ExamRepository;
import com.examportal.repository.QuestionRepository;
import com.examportal.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Exam
    @PostMapping("/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam examRequest) {
        Exam savedExam = examRepository.save(examRequest);
        return ResponseEntity.status(201).body(savedExam);
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        return ResponseEntity.ok(exams);
    }

    // Update Exam
    @PutMapping("/exams/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam examDetails) {
        Optional<Exam> examOpt = examRepository.findById(id);
        if (examOpt.isEmpty()) return ResponseEntity.notFound().build();
        Exam exam = examOpt.get();
        exam.setTitle(examDetails.getTitle());
        exam.setDescription(examDetails.getDescription());
        exam.setDuration(examDetails.getDuration());
        exam.setTotalMarks(examDetails.getTotalMarks());
        return ResponseEntity.ok(examRepository.save(exam));
    }

    // Delete Exam
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        if (!examRepository.existsById(id)) return ResponseEntity.notFound().build();
        examRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Create Question
    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionRepository.save(question));
    }

    // Update Question
    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        Optional<Question> questionOpt = questionRepository.findById(id);
        if (questionOpt.isEmpty()) return ResponseEntity.notFound().build();
        Question question = questionOpt.get();
        
        question.setText(questionDetails.getText());
        question.setCategory(questionDetails.getCategory());
        question.setDifficulty(questionDetails.getDifficulty());
        question.setCorrectAnswer(questionDetails.getCorrectAnswer());
        // Add other fields as needed
        return ResponseEntity.ok(questionRepository.save(question));
    }

    // Delete Question
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        if (!questionRepository.existsById(id)) return ResponseEntity.notFound().build();
        questionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Assign Role
    @PutMapping("/users/{id}/role")
    public ResponseEntity<User> assignRole(@PathVariable Long id, @RequestParam String role) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();
        User user = userOpt.get();
        user.setRole(role);
        return ResponseEntity.ok(userRepository.save(user));
    }
}