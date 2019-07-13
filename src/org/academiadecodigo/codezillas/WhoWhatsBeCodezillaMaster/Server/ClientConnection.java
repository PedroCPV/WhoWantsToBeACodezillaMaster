package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster.Server;

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

    public ClientConnection(Socket socket, Server server, String name) {
        this.socket = socket;
        this.server = server;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            BufferedReader in = openStreams();
            //some message ??

            if(!server.addClient(this)) {
                System.out.println("You can't play! Try again later!");;
            }
            while (!socket.isClosed()) {
                listen(in);
            }

        } catch (IOException io) {
            io.getStackTrace();
        }
    }

    //not sure
    private void listen(BufferedReader in) throws IOException {
        String message = in.readLine();


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
