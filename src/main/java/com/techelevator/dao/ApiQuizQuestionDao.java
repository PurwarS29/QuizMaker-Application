package com.techelevator.dao;

import com.techelevator.model.QuizQuestion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

/*
 * Quiz API:
 * https://opentdb.com/api_config.php
 */
public class ApiQuizQuestionDao implements QuizQuestionDao {
    private static final String API_BASE_URL = "https://opentdb.com/api.php";

    @Override
    public List<String> getQuizzes() {

        List<String> quizzes = new ArrayList<>();
        quizzes.add("General Knowledge");
        quizzes.add("Science & Nature");
        quizzes.add("Sports");

        return quizzes;
    }

    @Override
    public List<QuizQuestion> getQuestionsForQuiz(String quizName) {



       // return null;
        List<QuizQuestion> questions = new ArrayList<>();
        String apiUrl = API_BASE_URL + "?amount=5&category=" + getCategoryID(quizName);

        try {
            // Open connection to API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();

            // Extract questions and answers from the response
            String response = jsonResponse.toString();
            String[] results = response.split("\"results\":\\[")[1].split("]")[0].split("\\},\\{");

            for (String result : results) {
                // Extract question
                String question = extractValue(result, "question");
                // Extract correct answer
                String correctAnswer = extractValue(result, "correct_answer");

                // Extract incorrect answers
                String incorrectAnswers = extractValue(result, "incorrect_answers");
                String[] incorrectArray = incorrectAnswers.replaceAll("[\\[\\]\"]", "").split(",");

                // Combine answers into one list
                List<String> answers = new ArrayList<>();
                answers.add(correctAnswer); // Add correct answer first
                for (String incorrect : incorrectArray) {
                    answers.add(incorrect.trim());
                }

                // Create QuizQuestion and add it to the list
               // QuizQuestion quizQuestion = new QuizQuestion(question, answers, 0);
               // questions.add(quizQuestion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    private String getCategoryID(String quizName) {
        // Map quiz names to category IDs
        switch (quizName) {
            case "General Knowledge":
                return "9";
            case "Science & Nature":
                return "17";
            case "Sports":
                return "21";
            default:
                return "9"; // Default to General Knowledge
        }
    }

    private String extractValue(String json, String key) {
        // Basic string manipulation to extract values
        String[] parts = json.split("\"" + key + "\":\"");
        if (parts.length > 1) {
            return parts[1].split("\",")[0]; // Split and return the value
        }
        return "";
    }
}


