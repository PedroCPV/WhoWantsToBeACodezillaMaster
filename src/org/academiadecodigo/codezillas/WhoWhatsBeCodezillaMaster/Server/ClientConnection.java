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

            if(!server.addClient(this)) {
                System.out.println("You can't play! Try again later!");;
            }

            //TODO: test Sout (Delete)
            System.out.println("1");
            openStreams();
            prompt = new Prompt(socket.getInputStream(), new PrintStream(socket.getOutputStream()));

            //TODO: test Sout (Delete)
            System.out.println("2");
            server.getQuestionsBucket().questionsInit();
            Question question = selection();

            //TODO: test Sout (Delete)
            System.out.println("3");
            int numChoose = prompt.getUserInput(question.getQuestionsMenu());

            //TODO: test Sout (Delete)
            System.out.println(numChoose);
            System.out.println("4");
            int score = server.checkAnswer(numChoose, question.getValidIndex());

            //TODO: test Sout (Delete)
            System.out.println(score);
            if (score == 1) {
                send("Nice shot ma Friend");
            } else {
                send("Your answer is incorrect!\nThe correct answer is : " + question.getOptions()[numChoose-1]);
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
