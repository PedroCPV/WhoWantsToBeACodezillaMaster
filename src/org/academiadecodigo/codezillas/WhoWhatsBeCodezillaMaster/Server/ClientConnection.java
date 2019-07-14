package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
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
            Prompt prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));

            StringInputScanner playerName = new StringInputScanner();
            playerName.setMessage("Hey CODEzilla, what should I call you? ");
            name = prompt.getUserInput(playerName);

            send("Welcome " + name + "!");

            server.getQuestionsBucket().questionsInit();
            HashMap<Integer, Question> allQuestions = server.getQuestionsBucket().getHashMap();

            numOfAnswers = 0;
            while (numOfAnswers < TOTAL_QUESTIONS) {

                int questionChased = server.selectQuestion();
                Question question = allQuestions.get(questionChased);
                MenuInputScanner questionsMenu = new MenuInputScanner(question.getOptions());
                questionsMenu.setMessage(question.getQuestion());
                int answer = prompt.getUserInput(questionsMenu);


                if (server.checkAnswer(answer, questionChased) == 1) {

                    send("GOOD JOB SON");
                    score++;
                } else {

                    send("WTF?! I'm gonna slap you right in your face! \nThe correct answer is : " + question.getOptions()[question.getValidIndex() - 1]);
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

    public int getScore() {
        return score;
    }

    public int getNumOfAnswers() {
        return numOfAnswers;
    }

    public String getName() {
        return name;
    }
}
