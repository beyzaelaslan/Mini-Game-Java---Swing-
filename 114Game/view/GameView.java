package controller.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView extends JPanel {
    private static final int GRID_SIZE = 6;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton[][] gridButtons = new JButton[GRID_SIZE][GRID_SIZE];
    private JLabel scoreLabel;
    private ImageIcon handIcon; // Icon to represent the hand
    private ImageIcon appleIcon; // Icon to represent the apples

    public GameView() {
        setLayout(new BorderLayout());
        initializeGrid();
        initializeControlButtons();
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel, BorderLayout.SOUTH);
        // Load your hand and apple icons here
        handIcon = new ImageIcon("C:\\Users\\USER\\OneDrive\\Belgeler\\Applecollect\\hand.png");
        appleIcon = new ImageIcon("C:\\Users\\USER\\OneDrive\\Belgeler\\Applecollect\\apple.png");
    }
    public void setControlButtonListener(ActionListener listener) {
        upButton.addActionListener(listener);
        downButton.addActionListener(listener);
        leftButton.addActionListener(listener);
        rightButton.addActionListener(listener);
    }
    private void initializeControlButtons() {
        JPanel controlPanel = new JPanel(new GridLayout(3, 3)); // Using GridLayout for alignment
        controlPanel.setPreferredSize(new Dimension(300, 100)); // Set a preferred size

        // Initialize buttons with action commands
        upButton = createControlButton("Up", "MOVE_UP");
        downButton = createControlButton("Down", "MOVE_DOWN");
        leftButton = createControlButton("Left", "MOVE_LEFT");
        rightButton = createControlButton("Right", "MOVE_RIGHT");

        // Add buttons and placeholders to controlPanel
        controlPanel.add(new JLabel()); // Placeholder
        controlPanel.add(upButton);
        controlPanel.add(new JLabel()); // Placeholder
        controlPanel.add(leftButton);
        controlPanel.add(new JLabel()); // Placeholder
        controlPanel.add(rightButton);
        controlPanel.add(new JLabel()); // Placeholder
        controlPanel.add(downButton);

        // Add the control panel to the main GameView panel
        this.add(controlPanel, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
    }
    private JButton createControlButton(String text, String actionCommand) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        return button;
    }



    private void initializeGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton button = new JButton();
                button.setActionCommand(i + "," + j); // Set action command to identify the button
                 // Add the action listener
                button.setPreferredSize(new Dimension(50, 50)); // Set button size
                gridPanel.add(button);
                gridButtons[i][j] = button; // Store the button in the array for later use
            }
        }
        add(gridPanel, BorderLayout.CENTER);
    }

    public void setButtonListener(ActionListener listener) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridButtons[i][j].addActionListener(listener);
            }
        }
    }

    public void updateHandPosition(int x, int y) {
        // Clear the current hand position
        for (JButton[] row : gridButtons) {
            for (JButton button : row) {
                button.setIcon(null);
            }
        }
        // Place the hand icon at the new position
        gridButtons[x][y].setIcon(handIcon);
    }

    public void placeApple(int x, int y ) {
        gridButtons[x][y].setIcon(appleIcon);
    }
    public void clearCell(int x, int y) {
        gridButtons[x][y].setIcon(null);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }


}
