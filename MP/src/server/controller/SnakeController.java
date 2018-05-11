package server.controller;

import server.model.Node;
import server.model.ScoreCounter;
import server.model.Snake;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class SnakeController extends Thread implements Observer {

    private final int MAX_SIZE = 90;

    private Socket s;
    private InputStream iST;
    private OutputStream oST;
    BufferedReader read;
    PrintWriter writer;

    private Snake snk;
    private Node treasure;

    private boolean connected;

    private boolean stopped;


    public SnakeController(Socket s, InputStream inputStream, OutputStream outputStream,int id, ScoreCounter scores) {

        this.s = s;
        this.iST = inputStream;
        this.oST = outputStream;
        this.snk = new Snake(scores,id);
        this.read = new BufferedReader(new InputStreamReader(iST));
        this.connected = true;
        this.stopped = true;
        this.snk.addObserver(this);
        scores.addObserver(this);
        this.treasure = new Node(0,0);

        this.writer = new PrintWriter(this.oST,true);

    }


    public void randomTreasure(){
        Random rng = new Random();
        int x = rng.nextInt(MAX_SIZE-0);
        int y = rng.nextInt(MAX_SIZE-0);
        this.treasure.setPos(x,y);

            writer.println(("TRS;" + this.treasure.getX() + ";" + this.treasure.getY()));


    }

    public void run() {

        while (stopped) {
            try {
                String[] start = read.readLine().split(";");
                if (start[0].equals("STARTINFO")) {
                    this.snk.setName(start[1]);

                }if (start[0].equals("DIR")) {
                    this.snk.setMov(start[1]);
                    this.stopped = false;
                }
                this.randomTreasure();

            } catch (IOException e) {
                this.connected= false;
                this.stopped= false;
                e.printStackTrace();
            }
        }


        while (this.connected) {


            try {
                if (this.iST.available()>0) {
                    String[] msg = read.readLine().split(";");
                    if (msg[0].equals("FIN")) {
                        this.s.close();
                        this.connected = false;

                    } else {
                        switch (msg[0]) {
                            case "DIR":
                                this.snk.setMov(msg[1]);

                                break;

                            case "STP":

                                this.stopped = true;

                                while (this.stopped) {
                                    msg = read.readLine().split(";");
                                    if (msg[0].equals("START")) {
                                        this.stopped = false;
                                    }
                                }
                                break;

                            case "START":

                                this.stopped = false;
                                break;


                            default:
                                System.out.println("No message received");

                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (this.checkTreasure()) {
                snk.addScore();
                snk.move("keep");
                this.randomTreasure();

            } else {
                snk.move("poll");
            }

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkTreasure() {
        return snk.getTail().equals(this.treasure);
    }


    @Override
    public void update(Observable o, Object arg) {
        String msg = o.toString();

            writer.println(msg);


    }
}
