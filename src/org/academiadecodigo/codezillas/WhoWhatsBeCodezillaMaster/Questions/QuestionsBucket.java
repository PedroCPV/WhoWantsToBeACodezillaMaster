package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions;

import java.util.HashMap;

public class QuestionsBucket {

    private int numOfQuestions;
    private HashMap<Integer, Question> hashMap;

    public QuestionsBucket() {
        hashMap = new HashMap<>();
        numOfQuestions = 0;
    }

    public void addNewQuestion(String question, String optionA, String optionB, String optionC, String optionD, int validIndex) {
        Question newQuestion = new Question(question, optionA, optionB, optionC, optionD, validIndex);
        numOfQuestions++;
        hashMap.put(numOfQuestions, newQuestion);
    }

    public HashMap<Integer, Question> getHashMap() {
        return hashMap;
    }

    //TODO: Make Questions PEEPS!
    public void questionsInit() {
        addNewQuestion("pergunta", "A1", "B1", "C1", "D1", 1);
        addNewQuestion("pergunta2", "A2", "B2", "C2", "D2", 2);
        addNewQuestion("pergunta3", "A3", "B3", "C3", "D3", 3);
    }
}
