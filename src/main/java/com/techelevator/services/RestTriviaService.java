package com.techelevator.services;

import com.techelevator.model.TriviaApi;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

/*
 * Category #s here: https://opentdb.com/api_config.php
 */
public class RestTriviaService {
    private static final int NUMBER_OF_QUESTIONS = 10;
    private static final String API_URL = "https://opentdb.com/api.php";
    private static final String CATEGORY_URL = "https://opentdb.com/api_category.php";
    private final int GENERAL_KNOWLEDGE_QUIZ = 9;

    private Integer numberOfQuestions, category;
    private String difficulty, type;
    private RestTemplate restTemplate;

    public RestTriviaService(String category){
        this.difficulty = "medium";
        this.type = "multiple";
        this.numberOfQuestions = NUMBER_OF_QUESTIONS;
        this.category = GENERAL_KNOWLEDGE_QUIZ;
    }

    public TriviaApi getTrivia() throws RestClientResponseException {
        /*
         * Example:
         * "https://opentdb.com/api.php?amount=10&category=9&difficulty=medium&type=multiple"
         */

        // TODO: Finish

        return null;
    }
}
