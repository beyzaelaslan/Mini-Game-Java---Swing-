package controller.controller;

import controller.GameModel;
import controller.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

public class GameController implements ActionListener {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.view.setButtonListener(this); // For grid buttons, if still needed
        this.view.setControlButtonListener(this); // For navigation buttons
        initializeGameView();
    }


    private void initializeGameView() {
        updateViewAfterMove();
        updateApplePositionsInView(); // Initialize the grid with apples
        view.updateHandPosition(model.getHandPosition().x, model.getHandPosition().y); // Set initial hand position
    }
    private void moveHand(String direction) {
        // Logic to move hand based on direction
        Point current = model.getHandPosition();
        int x = current.x;
        int y = current.y;

        switch (direction) {
            case "UP":    y = Math.max(y - 1, 0); break;
            case "DOWN":  y = Math.min(y + 1, GameModel.getGridSize() - 1); break;
            case "LEFT":  x = Math.max(x - 1, 0); break;
            case "RIGHT": x = Math.min(x + 1, GameModel.getGridSize() - 1); break;
        }

        handleButtonPress(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "MOVE_UP":
                moveHand("UP");
                break;
            case "MOVE_DOWN":
                moveHand("DOWN");

                break;
            case "MOVE_LEFT":
                moveHand("LEFT");

                break;
            case "MOVE_RIGHT":
                moveHand("RIGHT");

                break;
            // ... other cases ...
        }
    }



    private void handleButtonPress(int x, int y) {
        if (model.moveHand(x, y)) {
            updateViewAfterMove();
            if (model.isAppleAt(x, y)) { // Check if there is an apple at the new position
                model.collectApple(x, y);
                view.clearCell(x, y); // Clearing the cell where the apple was collected
               // Update model to reflect apple collection
            }
            updateViewAfterMove();
        }
    }



    private void updateViewAfterMove() {
        Point handPosition = model.getHandPosition();
        view.updateHandPosition(handPosition.x, handPosition.y);
        view.updateScore(model.getScore());
    }

    private void updateApplePositionsInView() {
        for (int i = 0; i < GameModel.getGridSize(); i++) {
            for (int j = 0; j < GameModel.getGridSize(); j++) {
                if (model.isAppleAt(i, j)) {
                    view.placeApple(i, j);
                } else {
                    view.clearCell(i, j);
                }
            }
        }
    }

}
