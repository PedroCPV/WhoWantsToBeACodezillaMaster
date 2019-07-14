package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String DEFAULT_NAME = "player";
    private QuestionsBucket questionsBucket;
    private ServerSocket bindSocket;
    private final ExecutorService pool;
    private final HashMap<Integer, ClientConnection> players;
    private int numOfConnections;


    public Server(QuestionsBucket questionsBucket, int port) throws IOException {
        bindSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(2);
        players = new HashMap<>();
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
            addClient(connection);
            pool.submit(connection);

        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    public boolean addClient(ClientConnection client) {
        synchronized (players) {
            if (players.size() >= 2) {
                return false;
            }
            numOfConnections++;
            players.put(numOfConnections, client);
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
        return (players.get(1).getNumOfAnswers() == ClientConnection.TOTAL_QUESTIONS && players.get(2).getNumOfAnswers() == ClientConnection.TOTAL_QUESTIONS);
    }

    public synchronized String winner() throws InterruptedException {
        if (!endOfGame()) {
            wait();
        }

        notifyAll();

        if (players.get(1).getScore() == players.get(2).getScore()) {
            return "It's a draw, what a bummer.\n Player 1 and Player 2 scored: " + players.get(2).getScore();
        }

        if (players.get(1).getScore() > players.get(2).getScore()) {
            return "Player 1 won!\nPlayer 1 scored: " + players.get(1).getScore() + "\nPlayer 2 scored: " + players.get(2).getScore();
        }
        return "Player 2 won!\nPlayer 1 scored: " + players.get(1).getScore() + "\nPlayer 2 scored: " + players.get(2).getScore();
    }

    public HashMap<Integer, ClientConnection> getPlayers() {
        return players;
    }
}