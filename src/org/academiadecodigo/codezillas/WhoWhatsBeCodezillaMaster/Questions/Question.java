package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class Question {

    private static MenuInputScanner questionsMenu;
    private int validIndex;


    public Question(String question, String optionA, String optionB, String optionC, String optionD, int validIndex) {

        String[] options = {optionA, optionB, optionC, optionD};
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
}
