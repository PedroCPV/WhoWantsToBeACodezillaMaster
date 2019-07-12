package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server.Server;

public class MainTests {

    public static void main(String[] args) {
        QuestionsBucket questionsBucket = new QuestionsBucket();
        Server server = new Server(questionsBucket);
        questionsBucket.questionsInit();
        server.checkAnswer(3,1);
    }
}
