package controller.controller;

import controller.model.Question;
import controller.QuestionLoaderImp;

import controller.Score;
import controller.view.Quiz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizController {
    private Quiz quizView;
    private QuestionLoaderImp questionLoader;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private Set<String> answeredQuestions;
    private Score score;

    public QuizController(Quiz quizView, QuestionLoaderImp questionLoader, Score score) {
        this.quizView = quizView;
        this.questionLoader = questionLoader;
        this.score = score;
        this.answeredQuestions = new HashSet<>();
        initialize();
    }

    private void initialize() {
        quizView.setDifficultySelectionListener(this::loadQuestionsForDifficulty);
        quizView.setSubmitActionListener(this::onSubmit);
        quizView.setNextQuestionActionListener(this::nextQuestion);
        quizView.setVisible(true);
    }

    private void loadQuestionsForDifficulty(ActionEvent event) {
        JComboBox comboBox = (JComboBox) event.getSource();
        String selectedDifficulty = (String) comboBox.getSelectedItem();

        if (selectedDifficulty != null) {
            switch (selectedDifficulty) {
                case "Easy":
                    questions = questionLoader.getEasyQuestions();
                    break;
                case "Intermediate":
                    questions = questionLoader.getIntermediateQuestions();
                    break;
                case "Difficult":
                    questions = questionLoader.getDifficultQuestions();
                    break;
            }

            currentQuestionIndex = 0;
            if (!questions.isEmpty()) {
                quizView.showOptionsPanel(true);
                updateQuestionDisplay();
            } else {
                quizView.showMessage("No questions available for this difficulty.");
            }
        }
    }

    private void updateQuestionDisplay() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String[] options = {currentQuestion.getA(), currentQuestion.getB(),
                currentQuestion.getC(), currentQuestion.getD()};
        quizView.updateQuestionDisplay(currentQuestion.getQuestionText(), options);
    }

    private void onSubmit(ActionEvent event) {
        String selectedAnswer = quizView.getSelectedAnswer();
        if (selectedAnswer != null) {
            checkAnswer(selectedAnswer);
        } else {
            quizView.showMessage("Please select an answer.");
        }
    }

    private void checkAnswer(String selectedAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String questionKey = getQuestionKey(currentQuestion.getLevel(), currentQuestionIndex);

        if (answeredQuestions.contains(questionKey)) {
            quizView.showMessage("This question has already been answered.");
            return;
        }

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            int points = calculatePoints(currentQuestion.getLevel());
            score.addPoints(points);
            quizView.updateScoreDisplay(score.getTotalPoints());
            answeredQuestions.add(questionKey);
        }
    }

    private int calculatePoints(int level) {
        switch (level) {
            case 1:
                return 10;
            case 2:
                return 30;
            case 3:
                return 50;
            default:
                return 0;
        }
    }

    private String getQuestionKey(int level, int index) {
        return level + "-" + index;
    }

    private void nextQuestion(ActionEvent event) {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            updateQuestionDisplay();
        }
    }
}
