package Util.Server;

import model.Client;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer extends Thread{
    private ServerSocket s = null;
    private volatile JTextPane tpKetNoi;
    public List<Client> clients = new ArrayList<>();
    public SocketServer(JTextPane tpKetNoi){
        try {
            this.tpKetNoi = tpKetNoi;
            s = new ServerSocket(3000);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getS() {
        return s;
    }

    public void setS(ServerSocket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try
        {
            Socket ss=s.accept(); //synchronous
//            ServerListens serverListens = new ServerListens(ss);
            InetSocketAddress sockaddr = (InetSocketAddress)ss.getRemoteSocketAddress();
            InetAddress inaddr = sockaddr.getAddress();
            clients.add(new Client(inaddr.getHostAddress(), ss.getPort()));
            SwingUtilities.invokeAndWait(new Runnable(){
                public void run()
                {
                    tpKetNoi.setText(tpKetNoi.getText() + "IP: "+ inaddr.getHostAddress() + " PORT: " + ss.getPort() + " Kết Nối\n");
                }
            });
        }
        catch(InterruptedException | InvocationTargetException | IOException e)
        {
            System.out.println("There're some error");
        }
    }

    public void showConnection(){

     }
}
