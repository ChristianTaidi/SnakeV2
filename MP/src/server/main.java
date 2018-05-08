package server;
import GUI.SnakeGUI;

import java.io.Serializable;

public class main {
    public static void main(String[]args){
        ServerConection connection = new ServerConection();
        connection.run();
    }
}
