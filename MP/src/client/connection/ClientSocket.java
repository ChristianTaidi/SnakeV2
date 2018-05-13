/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.connection;
import java.io.*;
import java.net.Socket;
/**
 *
 * @author k.lisowski
 */
public class ClientSocket{
    private Socket socket;
    private PrintWriter streamToServer;
    private BufferedReader br;

    public ClientSocket()throws Exception{
        try{

          this.socket = new Socket("127.0.0.1", 9305);
          streamToServer = new PrintWriter(socket.getOutputStream(),true);
          br= new BufferedReader(new InputStreamReader(socket.getInputStream()));


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void socketToServer(String msg)throws Exception{
        try{

        streamToServer.println(msg);

        }catch(Exception e)
        {System.out.println(e.getMessage());
        }
    }

    public String socketFromServer()throws IOException{
        String code ="";
        while(true){
          InputStreamReader in = new InputStreamReader(socket.getInputStream());
          if(socket.getInputStream().available()>0){
                code= br.readLine();
          }
          return code;  
        }
    }

    
    
}
