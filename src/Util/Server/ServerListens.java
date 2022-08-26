package Util.Server;

import Util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.FolderTracking;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerListens extends Thread{
    private Socket ss;
    private String receivedMessage;
    private ObjectMapper objectMapper = null;
    public ServerListens(Socket s){
        ss = s;
        objectMapper = new ObjectMapper();
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
                    InetSocketAddress sockaddr = (InetSocketAddress) ss.getRemoteSocketAddress();
                    InetAddress inaddr = sockaddr.getAddress();
                    try {
                        List<FolderTracking> folderTrackings = Util.readFile();
                        folderTrackings.add(new FolderTracking(inaddr.toString(), "Ngat ket noi", "Ngat ket Noi Server"));
                        Util.writeFile(folderTrackings);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                writeLog(receivedMessage);
            }while(true);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void writeLog(String receivedMessage) throws IOException {
        FolderTracking folderTracking = objectMapper.readValue(receivedMessage, FolderTracking.class);
        InetSocketAddress sockaddr = (InetSocketAddress) ss.getRemoteSocketAddress();
        InetAddress inaddr = sockaddr.getAddress();
        folderTracking.setIpClient(inaddr.toString());

        List<FolderTracking> folderTrackings = Util.readFile();
        folderTrackings.add(folderTracking);
        Util.writeFile(folderTrackings);
    }


}
