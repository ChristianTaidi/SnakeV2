
package client;
import client.controller.ClientController;
import client.eventmanager.ObservableClass;
import client.view.Pixels;

public class Main {
    public static void main(String[]args){
        ObservableClass observable = new ObservableClass();
        Pixels pix= new Pixels(observable);
        ClientController client =  new ClientController(pix);
        observable.addObserver(client);
    }
}
