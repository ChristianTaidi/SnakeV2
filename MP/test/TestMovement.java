import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class TestMovement {

    public static void main(String[]args){


        try {
            String up="UP";
            String dwn="DWN";
            String rgt="RGT";
            String lft="LFT";

            HashMap<Integer,String> dirs= new HashMap<>();

            dirs.put(0,rgt);
            dirs.put(1,dwn);
            dirs.put(2,rgt);
            dirs.put(3,lft);
            Socket socket = new Socket("25.20.17.142",9000);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println("STARTINFO;TEST");
            writer.println("DIR;UP");
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
            String msg = reader.readLine();
            String dir = "DIR;UP";
            int i=0;
            while(msg!="FIN"){

                int index = i%4;
                dir= dirs.get(index);
                writer.println("DIR;"+dir);


                System.out.println(msg);
                msg = reader.readLine();

                i++;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
