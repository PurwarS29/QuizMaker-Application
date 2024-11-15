package com.techelevator;

import com.techelevator.application.QuizMaker;
import com.techelevator.dao.ApiQuizQuestionDao;
import com.techelevator.dao.FileQuizQuestionDao;
import com.techelevator.dao.JdbcQuizQuestionDao;
import com.techelevator.dao.QuizQuestionDao;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class QuizMakerCLI {
    /*
     * TODO: Make sure to create the QuizMaker database and then
     *  run the quiz_db.sql file to build the correct tables
     */
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/QuizMaker";

    public static void main(String[] args) {

        QuizQuestionDao loader = null;

        String response = askInput();

        if(response.equalsIgnoreCase("d")){
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(DATABASE_URL);
            dataSource.setUsername("postgres");
            dataSource.setPassword("postgres1");
            loader = new JdbcQuizQuestionDao(dataSource);
        } else if(response.equalsIgnoreCase("a")){
            loader = new ApiQuizQuestionDao();
        } else {
            loader = new FileQuizQuestionDao();
        }

        QuizMaker quizMaker = new QuizMaker(loader);
        quizMaker.run();
    }

    public static String askInput(){
        String ask = "Enter A to load quiz questions from API.\n";
        ask += "Enter D to load quiz questions from database.\n";
        ask += "Enter F to load from file: ";
        System.out.println(ask);
        String response = new Scanner(System.in).nextLine();

        return response;
    }
}
