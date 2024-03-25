package controller.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Quiz extends JPanel {
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JLabel scoreLabel;
    private JPanel optionsPanel;
    private JPanel mainPanel;
    private JComboBox<String> difficultyComboBox;
    private JButton submitButton, nextButton;

    public Quiz() {
//        setTitle("Quiz Application");
//        setSize(500, 350);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
//        setLocationRelativeTo(null); // Center on screen
    }

    private void initUI() {
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Top Panel for difficulty selection
        JPanel topPanel = new JPanel();
        difficultyComboBox = new JComboBox<>(new String[]{"Easy", "Intermediate", "Difficult"});
        topPanel.add(new JLabel("Select Difficulty:"));
        topPanel.add(difficultyComboBox);
        add(topPanel, BorderLayout.NORTH);

        // controller.controller.Question label
        questionLabel = new JLabel("Please select a difficulty level.", JLabel.CENTER);
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        // Options panel
        optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        optionsPanel.setVisible(false);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel();
        submitButton = new JButton("Submit Answer");
        nextButton = new JButton("Next");
        bottomPanel.add(submitButton);
        bottomPanel.add(nextButton);

        // Score label
        scoreLabel = new JLabel("Score: 0");
        bottomPanel.add(scoreLabel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setDifficultySelectionListener(ActionListener listener) {
        difficultyComboBox.addActionListener(listener);
    }

    public void setSubmitActionListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void setNextQuestionActionListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void updateQuestionDisplay(String questionText, String[] options) {
        questionLabel.setText("<html><div style='text-align: center;'>" + questionText + "</div></html>");
        for (int i = 0; i < options.length; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setVisible(true);
        }
        // Hide unused option buttons
        for (int i = options.length; i < optionButtons.length; i++) {
            optionButtons[i].setVisible(false);
        }
        clearOptionSelection();
    }

    public void updateScoreDisplay(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void clearOptionSelection() {
        ButtonGroup group = new ButtonGroup();
        for (JRadioButton button : optionButtons) {
            group.add(button);
            button.setSelected(false);
        }
    }
    public String getSelectedAnswer() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                return String.valueOf((char) ('a' + i));
            }
        }
        return null;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void showOptionsPanel(boolean isVisible) {
        optionsPanel.setVisible(isVisible);
    }
}
