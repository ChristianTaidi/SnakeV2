/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;
import client.connection.ClientSocket;
import client.eventmanager.ObservableClass;
import client.view.Pixels;
import client.view.Puntuacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author k.lisowski
 */
public class ClientController implements Observer{
    private Pixels pixelController;
    private ClientSocket socket;
    private Puntuacion scoreTable;
    private ObservableClass observable;
    private boolean connected;


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

    
    
    public void parseadorMensajesServer(String msg){
        String[] partes= msg.split(";");
        if(partes[0].equals("MOV")){
            this.pixelController.drawSnake(Integer.parseInt(partes[2]),Integer.parseInt( partes[3]));
            this.pixelController.unDraw(Integer.parseInt(partes[4]), Integer.parseInt(partes[5]));
        }else if(partes[0].equals("SCR")){
                //añade jugador si no existe
                if(!scoreTable.getPlayers().containsKey(partes[1])){
                    scoreTable.setPlayersInGame(partes[1]);
                }
                //muestra el nuevo score
                scoreTable.getPlayers().get(partes[1]).setText(partes[2]);



        }else if(partes[0].equals("TRS")){
            this.pixelController.drawTreasure(Integer.parseInt(partes[1]),Integer.parseInt( partes[2]));
        }
    }


    

    @Override
    public void update(Observable o, Object arg) {
        String msg = (String)arg;
        String[] partes= msg.split(";");
        try {
            socket.socketToServer(msg);
        } catch (Exception ex) {
            System.out.println("Fallo al enviar mensaje");
        }
        if(partes[0].equals("STARTINFO")){
            this.scoreTable.setPlayersInGame(partes[1]);
        }
    }

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
