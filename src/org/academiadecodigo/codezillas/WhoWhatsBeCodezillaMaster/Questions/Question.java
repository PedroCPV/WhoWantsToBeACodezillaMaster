package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Question {

    private static MenuInputScanner questionsMenu;
    private int validIndex;
    private String[] options;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, int validIndex) {

        options = new String[4];
        options[0] = optionA;
        options[1] = optionC;
        options[2] = optionC;
        options[3] = optionD;
        questionsMenu = new MenuInputScanner(options);
        questionsMenu.setMessage(question);
        this.validIndex = validIndex;
    }

    public int getValidIndex() {
        return validIndex;
    }

    public MenuInputScanner getQuestionsMenu(){
        return questionsMenu;
    }

    public String[] getOptions() {
        return options;
    }

}
