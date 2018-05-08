import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTest {


    public static void main(String[]args){

        try {
            Socket socket = new Socket("25.20.17.142",9000);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("STARTINFO;TEST");
            writer.println("DIR;UP");
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
