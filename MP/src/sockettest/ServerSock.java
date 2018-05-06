package sockettest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSock {

    public void run() {
        ServerSocket socket;
        try {
            socket = new ServerSocket(8000);
            while(true) {
                Socket s = socket.accept();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

