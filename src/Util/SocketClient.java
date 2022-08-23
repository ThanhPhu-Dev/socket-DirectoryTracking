package Util;

import java.io.*;
import java.net.Socket;

public class SocketClient extends Thread{
    private Socket s = null;
    private InputStream is = null;
    private BufferedReader br;
    private boolean status = false;
    private String receivedMessage = null;
    public void SocketClient(){
        try {
            is=s.getInputStream();
            br=new BufferedReader(new InputStreamReader(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        do {
            try {
                receivedMessage = br.readLine();
                System.out.println("Received : " + receivedMessage);
                if (receivedMessage.equalsIgnoreCase("quit")) {
                    this.status = false;
                    break;
                }
                listenMessage(receivedMessage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true);
    }

    public void listenMessage(String message){
        String[] meStrings = message.split(";");
        String type = meStrings[1];
        String des = meStrings[2];
        if(Constant.trackingFile.equals(type)){
            WatchFile.setPath(des);
        }

    }

    public boolean connect(String host, int port){

        try {
            s = new Socket(host,port);
            this.status = true;
            return true;
        } catch (IOException e) {
            System.out.println("Connect fail");
            return false;
        }
    }
}
