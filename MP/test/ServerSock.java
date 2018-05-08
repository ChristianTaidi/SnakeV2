import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


    public class ServerSock {

        public synchronized void run() {
            String str = "OK";
            ServerSocket socket;
            try {
                socket = new ServerSocket(9000);
                while(true) {
                    Socket s = socket.accept();
                    BufferedOutputStream ost = new BufferedOutputStream(s.getOutputStream());
                    System.out.println("Conectado");
                    ost.write(str.getBytes());
                    System.out.println("Escrito");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

