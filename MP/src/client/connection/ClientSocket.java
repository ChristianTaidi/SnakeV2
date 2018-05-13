/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.connection;
import java.io.*;
import java.net.Socket;
/**
 *  Class that establish a connection to the server provided, in this case the localhost server
 *  socket: socket between the server and the client
 *  streamToServer: writes data to the outputStream
 *  br: reader for the inputStream
 */
public class ClientSocket{
    private Socket socket;
    private PrintWriter streamToServer;
    private BufferedReader br;

    /**
     * connects the server, and initializes the writer and reader
     * @throws Exception
     */
    public ClientSocket()throws Exception{
        try{

          this.socket = new Socket("127.0.0.1", 9305);
          streamToServer = new PrintWriter(socket.getOutputStream(),true);
          br= new BufferedReader(new InputStreamReader(socket.getInputStream()));


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *  writes the msg param to the outputstream
     * @param msg
     * @throws Exception
     */
    public void socketToServer(String msg)throws Exception{
        try{

        streamToServer.println(msg);

        }catch(Exception e)
        {System.out.println(e.getMessage());
        }
    }

    /**
     * reads from the inputStream and returns the string
     * @return
     * @throws IOException
     */
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

    /**
     * closes the socket connection
     */
    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
}
