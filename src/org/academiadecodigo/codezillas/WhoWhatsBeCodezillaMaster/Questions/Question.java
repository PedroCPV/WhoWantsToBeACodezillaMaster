package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Question {

    private int validIndex;
    private String[] options;
    private String question;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, int validIndex) {

        this.question = question;
        options = new String[4];
        options[0] = optionA;
        options[1] = optionB;
        options[2] = optionC;
        options[3] = optionD;
        this.validIndex = validIndex;
    }

    public int getValidIndex() {
        return validIndex;
    }

    public String[] getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }
}
