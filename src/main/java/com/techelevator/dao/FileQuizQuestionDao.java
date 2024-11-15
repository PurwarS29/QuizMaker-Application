package com.techelevator.dao;

import com.techelevator.model.QuizQuestion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileQuizQuestionDao implements QuizQuestionDao {

    @Override
    public List<String> getQuizzes() {
        return Arrays.asList(new String[]{"test_quiz.txt","another_quiz.txt"});
    }

    @Override
    public List<QuizQuestion> getQuestionsForQuiz(String quizName) {
        List<QuizQuestion> questions = new ArrayList<>();

        File quizFile = new File(quizName);

        try (Scanner fileScanner = new Scanner(quizFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if ((line != null) && (!line.isEmpty())) {    // Skip null or empty lines
                    QuizQuestion q = parseFileLine(line);
                    questions.add(q);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to read the quiz file.");
        }

        return questions;
    }

    private QuizQuestion parseFileLine(String line) {
        QuizQuestion quizQuestion = new QuizQuestion();

        String[] parts = line.split("\\|");
        quizQuestion.setQuestion(parts[0]);
        String[] answers = new String[parts.length-1];

        for(int i = 1; i < parts.length; i++) {
            String answer = parts[i].trim();
            if (answer.endsWith("*")) {
                answer = answer.substring(0, answer.length()-1);	// Pull-off trailing "*" character
                quizQuestion.setCorrectAnswer(i);
            }
            answers[i-1] = answer;									// Add the answer to the array of answers
        }
        quizQuestion.setAnswers(answers);
        return quizQuestion;
    }
}
