package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Questions.Question;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientConnection implements Runnable{

    private Socket socket;
    private Server server;
    private String name;
    private PrintWriter output;
    private Prompt prompt;

    public ClientConnection(Socket socket, Server server, String name) throws IOException {
        this.socket = socket;
        this.server = server;
        this.name = name;
    }


    @Override
    public void run() {
        try {
//            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));
            prompt = new Prompt(socket.getInputStream(), System.out);

            //BufferedReader in = openStreams();
            Question question = selection();

            prompt.getUserInput(question.getQuestionsMenu());

            if(!server.addClient(this)) {
                System.out.println("You can't play! Try again later!");;
            }


            /*while (!socket.isClosed()) {
                listen(question);
            }*/

        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    /*
    private void listen(Question question) throws IOException {
        String message = question;
    }*/

    private Question selection() {
        return server.getQuestionsBucket().getHashMap().get(server.selectionQuestion());
    }

    private BufferedReader openStreams() throws IOException {
        output = new PrintWriter(socket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void close() throws IOException {
        socket.close();
    }

    // adaptar ao prompt???
    public void send(String message) {
        output.println(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
