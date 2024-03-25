package controller.model;


public class Question {
    private int id;
    private int level;
    private String questionText;

    private String a;
    private String b;
    private String c;
    private String d;
    private String correctAnswer;

    public Question(int id, int level, String questionText, String a, String b, String c, String d, String correctAnswer) {
        this.id = id;
        this.level = level;
        this.questionText = questionText;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correctAnswer = correctAnswer;
    }



    public int getLevel() {
        return level;
    }



    public String getQuestionText() {
        return questionText;
    }



    public String getA() {
        return a;
    }



    public String getB() {
        return b;
    }


    public String getC() {
        return c;
    }



    public String getD() {
        return d;
    }



    public String getCorrectAnswer() {
        return correctAnswer;
    }


    public String toString() {
        return "model.controller.controller.Question{" +
                "id=" + id +
                ", level=" + level +
                ", questionText='" + questionText + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}

