package client.view;

import client.controller.ClientController;
import client.eventmanager.ObservableClass;

public class BorrarEsteMain {
    public static void main(String [] args){
        String msg = "SCR;MARIA;200";
        ObservableClass observable = new ObservableClass();
        Pixels pix= new Pixels(observable);
        Puntuacion scoreTable =  new Puntuacion();
        ClientController client =  new ClientController(pix,scoreTable, observable);
       // observable.addObserver(client);
        //client.parseadorMensajesServer(msg);

    }
}
