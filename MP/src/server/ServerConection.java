package server;

import server.controller.SnakeController;
import server.model.ScoreCounter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConection {

    private int id=0;

    public void run(){
        try {

            ServerSocket socket = new ServerSocket(9000);
            ScoreCounter counter = new ScoreCounter();

            while (true){

                Socket s = socket.accept();

                new SnakeController( s , s.getInputStream(), s.getOutputStream(  ), this.id , counter).start();
                this.id += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
