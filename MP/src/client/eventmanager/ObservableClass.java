/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.eventmanager;

import client.eventmanager.Escaner;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 *
 * @author k.lisowski
 */
public class ObservableClass extends Observable implements KeyListener{

    private int id;
    public ObservableClass() {
        id =0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String msg = "nothing";
        int i= e.getKeyCode();
        if(i == KeyEvent.VK_UP){
            msg= "DIR;UP";
            System.out.println("ARRIBA");
        }else if(i == KeyEvent.VK_DOWN){
            msg= "DIR;DWN";
            System.out.println("ABAJO");
        }else if(i == KeyEvent.VK_LEFT){
            msg= "DIR;LFT";
            System.out.println("IZQUIERDA");
        }else if(i == KeyEvent.VK_RIGHT){
            msg= "DIR;RGT";
            System.out.println("DERECHA");
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
        System.out.println("pausa tocada");
        setChanged();
        notifyObservers("STOP");
    }

    public void botonInicioMouseClicked(java.awt.event.MouseEvent evt) {
        Escaner scan = new Escaner();
        System.out.println("Introduzca su ID: ");
        String name = scan.readPlayerName();
        setChanged();
        notifyObservers("STARTINFO;"+ name);

    }
}