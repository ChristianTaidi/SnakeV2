/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.eventmanager;

import java.util.Scanner;

/**
 *
 * Class used to read the name of the player from the console log
 */
public class Escaner {
    Scanner entradaTeclado;
    public Escaner(){
        this.entradaTeclado= new Scanner(System.in);
    }
    public String readPlayerName(){
        String cadena = this.entradaTeclado.nextLine();
        return cadena;
    }
}
