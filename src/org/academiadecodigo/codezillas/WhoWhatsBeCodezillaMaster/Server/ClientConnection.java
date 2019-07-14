package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.Question;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ClientConnection implements Runnable {

    private Socket socket;
    private Server server;
    private String name;
    private PrintWriter output;
    private int score;
    private int numOfAnswers;
    public static final int TOTAL_QUESTIONS = 3;


    public ClientConnection(Socket socket, Server server, String name) {
        this.socket = socket;
        this.server = server;
        this.name = name;
        score = 0;
    }


    @Override
    public void run() {
        try {
            openStreams();

            send("Welcome!\n You are a Player" + server.getPlayers().size());

            Prompt prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            server.getQuestionsBucket().questionsInit();
            HashMap<Integer, Question> allQuestions = server.getQuestionsBucket().getHashMap();

            numOfAnswers = 0;
            while (numOfAnswers < TOTAL_QUESTIONS) {

                int questionChased = server.selectionQuestion();
                Question question = allQuestions.get(questionChased);
                MenuInputScanner questionsMenu = new MenuInputScanner(question.getOptions());
                questionsMenu.setMessage(question.getQuestion());
                int answer = prompt.getUserInput(questionsMenu);


                if (server.checkAnswer(answer, questionChased) == 1) {

                    send("GOOD JOB SON -- Nice shot ma Friend");
                    score++;
                } else {

                    send("Your answer is incorrect!\nThe correct answer is : " + question.getOptions()[question.getValidIndex() - 1]);
                }
                numOfAnswers++;
            }

            send(server.winner());

            close();

        } catch (IOException io) {
            io.getStackTrace();
        } catch (InterruptedException intexcep) {
            System.out.println(intexcep.getMessage());
        }
    }

    private BufferedReader openStreams() throws IOException {
        output = new PrintWriter(socket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void close() throws IOException {
        socket.close();
    }

    public void send(String message) {
        output.println(message);
    }

    public String sendScore() {
        if (score >= 8 && score < 10) {
            return "That's a really great score, next summarizer's on you!\nYou scored: " + score;
        } else if (score >= 4 && score < 8) {
            return "You did meh, however, use your brain more often and you'll do juuuuust fine... buy everyone a round o' beer to redeem yourself ^^\nYou scored: " + score;
        } else if (score < 4) {
            return "You failed miserably, you didn't pay attention AT ALL!!!\nYOU SUUUUUUUUUUCK\nYou scored: " + score;
        }
        return "You did awesome, you're worthy of the title <Master_Codezilla>\nYou scored: " + score;
    }

    public int getScore() {
        return score;
    }

    public int getNumOfAnswers() {
        return numOfAnswers;
    }
}
