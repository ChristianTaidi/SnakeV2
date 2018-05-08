import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestSocket {
    public static void main(String []args){


        try {

            Socket socket = new Socket("192.168.1.33" ,9000);
            InputStream ist = socket.getInputStream();
            System.out.println(new BufferedReader(new InputStreamReader(ist)).readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
