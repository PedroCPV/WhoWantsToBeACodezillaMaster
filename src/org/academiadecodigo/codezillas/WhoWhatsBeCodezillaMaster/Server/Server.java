package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.Question;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;

public class Server {

    private QuestionsBucket questionsBucket;

    public Server(QuestionsBucket questionsBucket) {
        this.questionsBucket = questionsBucket;
    }

    //TODO: Player input Answer verify (this logic make sense stay in the "game judge")
    public int checkAnswer(int numChooseOption, int questionHasMapID) {

        if (numChooseOption == questionsBucket.getHashMap().get(questionHasMapID).getValidIndex()) {
            System.out.println("rigth");
            return 1;
        } else {
            System.out.println("wrong");
            return 0;
        }
    }
}
