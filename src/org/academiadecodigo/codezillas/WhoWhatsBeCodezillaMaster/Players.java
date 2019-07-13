package org.academiadecodigo.codezillas.WhoWhatsBeCodezillaMaster;

import org.academiadecodigo.bootcamp.Prompt;

import java.net.Socket;

public class Players {

    private Socket socket;
    private Prompt playerPrompt;
    //TODO: variavel opcional
    private int numOfWins;


    public Players (Socket socket) {

        this.socket = socket;
        playerPrompt = new Prompt(System.in, System.out);
    }

    public void intit() {
        //instance Thread argument = Socket
        //Prompt receive
        //Prompt send

    }

    public void gameResult () {
        //metodo opcional
        //incrementar wins
    }
}
