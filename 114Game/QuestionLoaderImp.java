package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionLoaderImp  {
    private final List<Question> easyQuestions;
    private final List<Question> intermediateQuestions;
    private final List<Question> difficultQuestions;

    public QuestionLoaderImp(String filePath) {
        this.easyQuestions = new ArrayList<>();
        this.intermediateQuestions = new ArrayList<>();
        this.difficultQuestions = new ArrayList<>();
        readFile(filePath);
    }

    private void readFile(String filePath) {
        String line;
        String splitBy = ";";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                line = removeUTF8BOM(line).trim();
                String[] questions = line.split(splitBy);

                int id = Integer.parseInt(questions[0].trim());
                int level = Integer.parseInt(questions[1].trim());
                String questionText = questions[2].trim();
                String a = questions[3].trim();
                String b = questions[4].trim();
                String c = questions[5].trim();
                String d = questions[6].trim();
                String correctAnswer = questions[7].trim();

                Question question = new Question(id, level, questionText, a, b, c, d, correctAnswer);

                if (level == 1) {
                    easyQuestions.add(question);
                } else if (level == 2) {
                    intermediateQuestions.add(question);
                } else if (level == 3) {
                    difficultQuestions.add(question);
                } else {
                    System.out.println("Invalid question level: " + level);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public List<Question> getEasyQuestions() {
        return new ArrayList<>(easyQuestions);
    }


    public List<Question> getIntermediateQuestions() {
        return new ArrayList<>(intermediateQuestions);
    }


    public List<Question> getDifficultQuestions() {
        return new ArrayList<>(difficultQuestions);
    }

    private String removeUTF8BOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
    }
}
