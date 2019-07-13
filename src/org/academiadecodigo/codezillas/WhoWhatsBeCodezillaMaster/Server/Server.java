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


    public Server(/*QuestionsBucket questionsBucket*/int port) throws IOException {
        bindSocket = new ServerSocket(port);
        pool = Executors.newCachedThreadPool();
        clients = Collections.synchronizedList(new LinkedList<>());
        //this.questionsBucket = questionsBucket;
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

    public void start() {
        int connections = 0;

        while(connections < 2) {
            waitConnection(connections);
            connections++;
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
            if (clients.size() < 2) {
                return false;
            }
            broadcast(client.getName() + " " + "joined" );
            clients.add(client);
            return true;
        }
    }

    public void broadcast(String message) {
        synchronized (clients) {
            for (ClientConnection client : clients) {
                client.send(message);
            }
        }
    }








    //TODO: remove client from list























}
