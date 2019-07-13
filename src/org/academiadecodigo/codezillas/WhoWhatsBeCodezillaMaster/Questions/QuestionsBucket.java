package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions;

import java.util.HashMap;

public class QuestionsBucket {

    private int numOfQuestions;
    private HashMap<Integer, Question> hashMap;

    public QuestionsBucket() {
        hashMap = new HashMap<>();
        numOfQuestions = 0;
    }

    public void newQuestion(String question, String optionA, String optionB, String optionC, String optionD, int validIndex) {
        Question newQuestion = new Question(question, optionA, optionB, optionC, optionD, validIndex);
        numOfQuestions++;
        hashMap.put(numOfQuestions, newQuestion);
    }

    public HashMap<Integer, Question> getHashMap() {
        return hashMap;
    }

    //TODO: Make Questions PEEPS!
    public void questionsInit() {
        newQuestion("pergunta", "A", "B", "C", "D", 2);
    }
}
