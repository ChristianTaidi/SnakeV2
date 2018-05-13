/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.eventmanager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 *Class used to manage keyevents, gets arrowkeyEvents and notifies the controller when any event happens
 * gameState: used to determine if the start button is used as the first start, or as after pause start
 */
public class ObservableClass extends Observable implements KeyListener{

    private int gameState;
    public ObservableClass() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String msg = "nothing";
        int i= e.getKeyCode();
        if(i == KeyEvent.VK_UP){
            msg= "DIR;DWN";

        }else if(i == KeyEvent.VK_DOWN){
            msg= "DIR;UP";

        }else if(i == KeyEvent.VK_LEFT){
            msg= "DIR;LFT";

        }else if(i == KeyEvent.VK_RIGHT){
            msg= "DIR;RGT";

        }else if(i == KeyEvent.VK_V){
            msg= "speed";
        }
        setChanged();
        notifyObservers(msg);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void botonPausaMouseClicked(java.awt.event.MouseEvent evt){
        System.out.println("pausa");
        setChanged();
        notifyObservers("STP");
    }

    public void botonInicioMouseClicked(java.awt.event.MouseEvent evt) {
        if (gameState == 0) {
            Escaner scan = new Escaner();
            System.out.println("Introduzca su ID: ");
            String name = scan.readPlayerName();
            setChanged();
            notifyObservers("STARTINFO;" +"gfds ;"+ name +";"+0);
            gameState += 1;
        } else {
            setChanged();
            notifyObservers("START;");
        }
    }

    public void endGame() {
        setChanged();
        notifyObservers("FIN;");
    }
}