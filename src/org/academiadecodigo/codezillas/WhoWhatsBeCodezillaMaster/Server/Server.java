package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.Question;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.QuestionsBucket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private QuestionsBucket questionsBucket;
    private ServerSocket bindSocket;
    private DataInputStream message;
    private final ExecutorService pool;

    public static void main(String[] args) {
        Server server = new Server();
        server.listen(9999);
    }


    public Server(/*QuestionsBucket questionsBucket*/) {
        pool = Executors.newFixedThreadPool(2);
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

    private void listen(int port) {
        try {
            bindSocket = new ServerSocket(port);
            System.out.println("Binding to port " + port);
            serve(bindSocket);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void serve(ServerSocket bindSocket) {
        while (bindSocket.isBound()) {
            try {
                Socket clientSocket = bindSocket.accept();
                System.out.println("New connection from; " + clientSocket.getLocalAddress());
                pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        dispatch(clientSocket);
                        System.out.println(Thread.currentThread().getName());
                    }
                });
            } catch (IOException io) {
                io.getStackTrace();
            }
        }
        shutDown();
    }

    private void dispatch(Socket clientSocket) {
        try {
            message = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            String line = "";
            while(!line.equals("Quit") && line != null) {
                try {
                    line = message.read();
                    System.out.println(line);
                } catch (IOException io) {
                    io.getStackTrace();
                }
            }
            System.out.println("Connection lost");
        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    public void shutDown() {
        try {
            System.out.println("Connection lost");
            bindSocket.close();
            message.close();
        }catch (IOException io) {
            io.getStackTrace();
        }
    }
}
