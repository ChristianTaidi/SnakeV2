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
public class ClientSocket {
    private Socket socket;
    
    
    public ClientSocket()throws Exception{
        try{
          this.socket = new Socket("10.10.97.209", 9305);
        }catch(Exception e){
            e.getMessage();
        }
    }
    
    public void socketToServer(String msg)throws Exception{
        try{
        DataOutputStream streamToServer = new DataOutputStream(socket.getOutputStream());
        streamToServer.writeBytes(msg);
        }catch(Exception e)
        {e.getMessage();
        }
    }
    public String socketFromServer()throws IOException{
        String code ="";
        while(true){
          InputStreamReader in = new InputStreamReader(socket.getInputStream());
          BufferedReader br = new BufferedReader(in);
          if(socket.getInputStream().available()>0){
                code= br.readLine();
          }
          return code;  
        }
    }
    
    public static void main(String[] args) throws Exception{
        

       // InputStream streamFromServer = socket.getInputStream();

      /*  int i = streamFromServer.read();
        FileOutputStream fos= new FileOutputStream("FicheroRedes.pdf");
        while(i != -1){
            fos.write(i);
            i = streamFromServer.read();
        } 


    socket.close();
    fos.close();
    */
    }
    
    
}
