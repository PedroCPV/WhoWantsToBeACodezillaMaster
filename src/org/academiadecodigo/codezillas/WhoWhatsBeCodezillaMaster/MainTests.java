package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server.Server;

import java.io.IOException;
import java.util.Scanner;

public class MainTests {

    public static void main(String[] args) {
             QuestionsBucket questionsBucket = new QuestionsBucket();
        try {
            Server server = new Server(questionsBucket, 8888);
            server.start();

            questionsBucket.questionsInit();
            Prompt prompt = new Prompt(System.in, System.out);
            int hhhh = server.selectionQuestion();
            System.out.println(hhhh);
            server.checkAnswer( prompt.getUserInput(questionsBucket.getHashMap().get(1).getQuestionsMenu()), hhhh);
        } catch (IOException io) {
            io.getStackTrace();
        }

/*
        try {
            Server server = new Server(8888);
            server.start();
        } catch (IOException io) {
            io.getStackTrace();
        }
*/






    }
}
