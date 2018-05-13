/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;
import client.connection.ClientSocket;
import client.eventmanager.ObservableClass;
import client.view.Pixels;
import client.view.PopUpWindow;
import client.view.PopUpWindowSnake;
import client.view.Puntuacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used as the controller and observer for the client
 * pixelController: view of the API draws and undraws the information in order to notify the user
 * socket: instance of a ClientSocket used to read and send info
 * scoreTable: view that shows the score and name of every user connected to the server
 * observable: instance of ObservableClass, used as keyEvent Manager, for the arrow keys
 * connected: boolean field that shows the state of the controller
 */
public class ClientController implements Observer{
    private Pixels pixelController;
    private ClientSocket socket;
    private Puntuacion scoreTable;
    private ObservableClass observable;
    private boolean connected;

    /**
     * Initializes the controller and connects with the server
     * @param pixelController view of the game
     * @param score view of the scores
     * @param observable keyEvent Manager
     */
    public ClientController(Pixels pixelController, Puntuacion score, ObservableClass observable){
        this.pixelController= pixelController;
        this.scoreTable= score;
        this.observable = observable;
        this.observable.addObserver(this);

        try {
            this.socket = new ClientSocket();
            this.connected=true;
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pixelController.setVisible(true);
    }


    /**
     *  parses the message and updates the info of the game or the scores view, depending on the parsing info,
     * @param msg
     */
    public void parseadorMensajesServer(String msg){
        String[] partes= msg.split(";");
        if(partes[0].equals("MOV")){
            this.pixelController.drawSnake(Integer.parseInt(partes[2]),Integer.parseInt( partes[3]));
            this.pixelController.unDraw(Integer.parseInt(partes[4]), Integer.parseInt(partes[5]));
        }else if(partes[0].equals("SCR")){

                scoreTable.setPlayersInGame(partes);
                //muestra el nuevo score




        }else if(partes[0].equals("TRS")){
            this.pixelController.drawTreasure(Integer.parseInt(partes[1]),Integer.parseInt( partes[2]));
        }else if(partes[0].equals("FIN")){
            PopUpWindowSnake message = new PopUpWindowSnake();
            message.setVisible(true);
            this.connected=false;
            this.socket.close();
        }
    }


    /**
     * called when any observable linked to this observer is modified
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        String msg = (String)arg;
        String[] partes= msg.split(";");
        try {
            socket.socketToServer(msg);
        } catch (Exception ex) {
            System.out.println("Fallo al enviar mensaje");
        }
       // if(partes[0].equals("STARTINFO")){

         //       this.scoreTable.setPlayersInGame(partes);


        //}else
        if(partes[0].equals("FIN")){
            this.connected=false;

        }
    }

    /**
     * method that runs the controller, when the state changes to disconnected, it ends
     */
    public void run() {
        
        while(connected){
            try {
                String msg = this.socket.socketFromServer();
                if(msg!=null){
                    parseadorMensajesServer(msg);
                }

            } catch (IOException e) {
                this.connected=false;
                System.out.println("Connection lost");
            }
        }
    }
}
