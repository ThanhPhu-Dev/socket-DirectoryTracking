package Util.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListens extends Thread{
    private Socket ss;
    private String receivedMessage;
    public ServerListens(Socket s){
        ss = s;
        start();
    }

    @Override
    public void run() {
        InputStream is;
        BufferedReader br;
        try {
            is = ss.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            do {
                receivedMessage=br.readLine();
                if (receivedMessage.equalsIgnoreCase("quit"))
                {
                    System.out.println("Client has left !");
                    break;
                }
            }while(true);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
