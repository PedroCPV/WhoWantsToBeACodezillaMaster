package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server.Server;
import java.io.IOException;

public class MainTests {

    public static void main(String[] args) {

        QuestionsBucket questionsBucket = new QuestionsBucket();

        try {

            Server server = new Server(questionsBucket, 9898);
            server.start();

        } catch (IOException io) {
            io.getStackTrace();
        }
    }
}
