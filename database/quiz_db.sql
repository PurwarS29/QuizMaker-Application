START TRANSACTION;

DROP TABLE IF EXISTS quiz, question, quiz_question, answer CASCADE;

CREATE TABLE quiz (
    quiz_id serial,
    quiz_name varchar(20) NOT NULL,
    CONSTRAINT PK_quiz PRIMARY KEY(quiz_id)
);

CREATE TABLE question (
    question_id serial,
    question_text varchar(100) NOT NULL,
    correct_answer int NOT NULL,
    CONSTRAINT PK_question PRIMARY KEY(question_id)
);

CREATE TABLE quiz_question (
    quiz_id int,
    question_id int,
    question_number int NOT NULL,
    CONSTRAINT PK_quiz_question PRIMARY KEY(quiz_id, question_id),
	CONSTRAINT FK_quiz_id FOREIGN KEY(quiz_id) REFERENCES quiz(quiz_id),
	CONSTRAINT FK_question_id FOREIGN KEY(question_id) REFERENCES question(question_id)
);

CREATE TABLE answer (
    question_id int,
    answer_number int,
    answer_text varchar(50) NOT NULL,
    CONSTRAINT PK_answer PRIMARY KEY(question_id, answer_number),
	CONSTRAINT FK_answer_question FOREIGN KEY (question_id) REFERENCES question(question_id)
);

INSERT INTO quiz
(quiz_name)
VALUES
('test_quiz');

--Question 1
INSERT INTO question
(question_text, correct_answer)
VALUES
('What color is the sky?', 2);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, 'yellow'),
(lastval(), 2, 'blue'),
(lastval(), 3, 'green'),
(lastval(), 4, 'red');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 1);

--Question 2
INSERT INTO question
(question_text, correct_answer)
VALUES
('A skeleton walks into a bar, and says to the bartender, "Give me a beer and a ..."', 3);

INSERT INTO answer
(question_id, answer_number, answer_text)
VALUES
(lastval(), 1, 'hair brush'),
(lastval(), 2, 'dry erase marker'),
(lastval(), 3, 'mop'),
(lastval(), 4, 'yo-yo');

INSERT INTO quiz_question
(quiz_id, question_id, question_number)
VALUES
((SELECT quiz_id FROM quiz WHERE quiz_name = 'test_quiz'), lastval(), 2);

COMMIT;
