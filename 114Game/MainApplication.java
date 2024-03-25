package controller;

import controller.controller.GameController;
import controller.controller.QuizController;
import model.QuestionLoaderImp;
import controller.view.GameView;
import controller.view.Quiz;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication extends JFrame {
    private Quiz quizPart; // Part I: Quiz
    private GameView gamePart; // Part II: Game
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private QuizController quizController;
    private GameController gameController;
    private JButton switchToGameButton;
    private Timer gameTimer;

    public MainApplication() {
        setTitle("Quiz and Game Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setVisible(true);
    }
    private void startGame() {
        // Calculate game duration: Quiz score * 3 seconds
        int gameDurationInSeconds = Score.getTotalPoints() * 3;

        // Set the game part visible
        cardLayout.show(mainPanel, "Game");

        // Initialize and start the game timer
        gameTimer = new Timer(gameDurationInSeconds * 1000, e -> endGame());
        gameTimer.setRepeats(false); // Ensure the timer only triggers once
        gameTimer.start();
    }

    private void endGame() {
        // Stop the timer
        gameTimer.stop();

        // Show a message or handle the end of the game
        JOptionPane.showMessageDialog(this, "Time's up! Your game has ended.");

        // Here you can add logic to switch back to the quiz, reset the game, etc.
    }

    private void initComponents() {
        // Initialize quiz and game parts
        QuestionLoaderImp questionLoader = new QuestionLoaderImp("C:\\Users\\USER\\OneDrive\\Belgeler\\questions.csv");
        Score score = new Score();
        quizPart = new Quiz();
        quizController = new QuizController(quizPart, questionLoader, score);

        GameModel gameModel = new GameModel();
        gamePart = new GameView();
        gameController = new GameController(gameModel, gamePart);

        // Layout for switching between parts
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(quizPart, "Quiz");
        mainPanel.add(gamePart, "Game");

        // Button to switch to the game part
        switchToGameButton = new JButton("Switch to Game");
        switchToGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        add(mainPanel, BorderLayout.CENTER);
        add(switchToGameButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication mainApp = new MainApplication();
            mainApp.setVisible(true);
        });
    }
}
