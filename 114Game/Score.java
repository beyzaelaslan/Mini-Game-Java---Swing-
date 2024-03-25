package controller;

public class Score {
    private static int totalPoints = 0;

    public static int getTotalPoints() {
        return totalPoints;
    }

    public static void addPoints(int point) {
        totalPoints += point;
    }
}
