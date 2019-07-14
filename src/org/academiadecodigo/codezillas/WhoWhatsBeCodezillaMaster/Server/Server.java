package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("ALL")
public class Server {
    private static final String DEFAULT_NAME = "player";
    private QuestionsBucket questionsBucket;
    private ServerSocket bindSocket;
    private final ExecutorService pool;
    private final List<ClientConnection> players;


    public Server(QuestionsBucket questionsBucket, int port) throws IOException {
        bindSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(2);
        players = Collections.synchronizedList(new LinkedList<>());
        this.questionsBucket = questionsBucket;
    }

    public void start() {
        int connections = 0;

        while (bindSocket.isBound() && connections < 2) {

            connections++;
            waitConnection(connections);
        }

    }

    private void waitConnection(int connections) {
        try {

            Socket clientSocket = bindSocket.accept();
            ClientConnection connection = new ClientConnection(clientSocket, this, DEFAULT_NAME + connections);
            pool.submit(connection);

        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    public boolean addClient(ClientConnection client) {
        synchronized (players) {
            if (players.size() > 2) {
                return false;
            }
            players.add(client);
            return true;
        }
    }


    public int selectionQuestion() {
        return (int) Math.floor(Math.random() * (questionsBucket.getHashMap().size()) + 1);
    }


    public int checkAnswer(int numChooseOption, int questionHasMapID) {

        if (numChooseOption == questionsBucket.getHashMap().get(questionHasMapID).getValidIndex()) {
            return 1;
        } else {
            return 0;
        }
    }

    public QuestionsBucket getQuestionsBucket() {
        return questionsBucket;
    }

    public boolean endOfGame() {
        return (players.get(0).getNumOfAnswers() == ClientConnection.TOTAL_QUESTIONS && players.get(1).getNumOfAnswers() == ClientConnection.TOTAL_QUESTIONS);
    }

    public synchronized String winner() throws InterruptedException {
        notifyAll();
        wait();

        if (endOfGame()) {
            if (players.get(1).getScore() == players.get(2).getScore()) {
                return "It's a draw, what a bummer.";
            }

            if (players.get(1).getScore() > players.get(2).getScore()) {
                return "Player 1 won with a score of: " + players.get(1).getScore();
            }
            return "Player 2 won with a score of: " + players.get(2).getScore();
        }
        return "Wait for the other player";
    }
}