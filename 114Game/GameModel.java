package controller;

import java.awt.Point;
import java.util.Random;

public class GameModel {

    private static final int GRID_SIZE = 6;
    private boolean[][] apples; // Grid to track apple positions
    private Point handPosition; // Position of the hand
    private int score;
    private Random random;

    public GameModel() {
        apples = new boolean[GRID_SIZE][GRID_SIZE];
        handPosition = new Point(0, 0); // Starting position of the hand
        score = 0;
        random = new Random();
        placeApplesRandomly();
    }


    private void placeApplesRandomly() {
        // Assuming a random number of apples, you can adjust this logic
        int numberOfApples = 5 + random.nextInt(10); // Randomly place between 5 and 15 apples
        for (int i = 0; i < numberOfApples; i++) {
            int x = random.nextInt(GRID_SIZE);
            int y = random.nextInt(GRID_SIZE);
            apples[x][y] = true;
        }
    }

    public boolean moveHand(int newX, int newY) {
        if (newX >= 0 && newX < GRID_SIZE && newY >= 0 && newY < GRID_SIZE) {
            handPosition.setLocation(newX, newY);
            if (apples[newX][newY]) { // Check if there's an apple
                collectApple(newX, newY);
            }
            return true;
        }
        return false;
    }


    public void collectApple(int x, int y) {
        if (apples[x][y]) {
            apples[x][y] = false; // Remove the apple
            score += 100; // Increase the score
        }
    }


    // Getters and Setters
    public Point getHandPosition() {
        return handPosition;
    }

    public int getScore() {
        return score;
    }
    public static int getGridSize() {
        return GRID_SIZE;
    }

    public boolean isAppleAt(int x, int y) {
        return apples[x][y];
    }
}
