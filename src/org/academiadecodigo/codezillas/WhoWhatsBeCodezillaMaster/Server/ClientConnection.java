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


            send(" __    __ _                                 _         _          _                   \n" +
                    "/ / /\\ \\ \\ |__   ___   __      ____ _ _ __ | |_ ___  | |_ ___   | |__   ___    __ _  \n" +
                    "\\ \\/  \\/ / '_ \\ / _ \\  \\ \\ /\\ / / _` | '_ \\| __/ __| | __/ _ \\  | '_ \\ / _ \\  / _` | \n" +
                    " \\  /\\  /| | | | (_) |  \\ V  V / (_| | | | | |_\\__ \\ | || (_) | | |_) |  __/ | (_| | \n" +
                    "  \\/  \\/ |_| |_|\\___/    \\_/\\_/ \\__,_|_| |_|\\__|___/  \\__\\___/  |_.__/ \\___|  \\__,_| \n" +
                    "                                                                                     \n" +
                    "          ___   ___  ___  __    _ _ _                          _                     \n" +
                    "         / __\\ /___\\/   \\/__\\__(_) | | __ _    /\\/\\   __ _ ___| |_ ___ _ __          \n" +
                    "        / /   //  // /\\ /_\\|_  / | | |/ _` |  /    \\ / _` / __| __/ _ \\ '__|         \n" +
                    "       / /___/ \\_// /_///__ / /| | | | (_| | / /\\/\\ \\ (_| \\__ \\ ||  __/ |            \n" +
                    "       \\____/\\___/___,'\\__//___|_|_|_|\\__,_| \\/    \\/\\__,_|___/\\__\\___|_|            \n" +
                    "                                                                                     ");

            send(
                    "                                                    _/ -|  \\_     \n" +
                    "                                                   (  \\   /  )   \n" +
                    "                                                     \\ *   * /   \n" +
                    "                                                    \\     /    \n" +
                    "                                                      |( \" )|  \n" +
                    "                                                      | VWV |  \n" +
                    "                         |\\                         _/      |\n" +
                    "                         | |                   _-~         \\/\n" +
                    "                        /  /                _-~  /          |\\\n" +
                    "                       |  |              _-~    (     /   |/ \n" +
                    "                      /   /           _-~  __    |   |____|/\n" +
                    "                     |   |__         / _-~  ~-_  (_______  `\\\n" +
                    "                     |      ~~--__--~ /  _     \\        __\\)))\n" +
                    "                      \\               _-~       |     ./  \\\n" +
                    "                       ~~--__        /         /    _/     |\n" +
                    "                             ~~--___/       _-_____/      /\n" +
                    "                              _____/     _-_____/      _-~\n" +
                    "                           /^<  ___       -____         -____\n" +
                    "                              ~~   ~~--__      ``\\--__       ``\\\n" +
                    "                                         ~~--\\)\\)\\)   ~~--\\)\\)\\)");


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
