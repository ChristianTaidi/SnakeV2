/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;
import client.connection.ClientSocket;
import client.view.Pixels;

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
    
    public ClientController(Pixels pixelController){
        this.pixelController= pixelController;
        try {
            this.socket = new ClientSocket();
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pixelController.setVisible(true);
    }
    
    public String parseadorMov(String cadena){

        String[] partes= cadena.split(";");
        if(partes[0].equals("MOV")){
                return partes[2]+";"+partes[3];
        }else{
            return "0";
        }
    }
    
    
    public void parseadorMensajesServer(String msg){
        String[] partes= msg.split(";");
        if(partes[0].equals("POS")){
            this.pixelController.drawSnake(Integer.parseInt(partes[2]),Integer.parseInt( partes[3]));
            this.pixelController.unDraw(Integer.parseInt(partes[4]), Integer.parseInt(partes[5]));
        }else if(partes[0].equals("SCR")){
            
        }else if(partes[0].equals("TRS")){
            this.pixelController.drawTreasure(Integer.parseInt(partes[1]),Integer.parseInt( partes[2]));
        }
    }


    

    @Override
    public void update(Observable o, Object arg) {
        String msg = (String)arg;
        try {
            socket.socketToServer(msg);
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
