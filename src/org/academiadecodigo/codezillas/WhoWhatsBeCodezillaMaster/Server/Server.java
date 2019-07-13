package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.Question;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String DEFAULT_NAME = "player";
    private QuestionsBucket questionsBucket;
    private ServerSocket bindSocket;
    private final ExecutorService pool;
    private final List<ClientConnection> clients;


    public Server(QuestionsBucket questionsBucket, int port) throws IOException {
        bindSocket = new ServerSocket(port);
        pool = Executors.newFixedThreadPool(2);
        clients = Collections.synchronizedList(new LinkedList<>());
        this.questionsBucket = questionsBucket;
    }

    public void start() {
        int connections = 0;

        while(bindSocket.isBound() && connections < 2) {
            connections++;

            waitConnection(connections);
            System.out.println("oi" + connections);
        }

    }

    private void waitConnection(int connections) {
        try {
            Socket clientSocket = bindSocket.accept();
            ClientConnection connection = new ClientConnection(clientSocket,this, DEFAULT_NAME + connections);
            pool.submit(connection);

        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    public boolean addClient(ClientConnection client) {
        synchronized (clients) {
            if (clients.size() > 2) {
                return false;
            }
            clients.add(client);
            return true;
        }
    }


    public int selectionQuestion() {

        int sortQuestion = (int) Math.floor(Math.random() * (questionsBucket.getHashMap().size()) + 1);
//Main tests
        questionsBucket.getHashMap().get(sortQuestion);
        return sortQuestion;
    }

    //TODO: remove client from list


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
