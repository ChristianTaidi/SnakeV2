
package client;
import client.controller.ClientController;
import client.eventmanager.ObservableClass;
import client.view.Pixels;
import client.view.Puntuacion;

public class Main {
    public static void main(String[]args){
        ObservableClass observable = new ObservableClass();
        Pixels pix= new Pixels(observable);
        Puntuacion scoreTable =  new Puntuacion();
        ClientController client =  new ClientController(pix,scoreTable,observable);
        client.run();
        observable.addObserver(client);
    }
}
