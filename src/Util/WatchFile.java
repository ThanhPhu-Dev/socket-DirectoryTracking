package Util;

import Util.Client.SocketClient;
import model.FolderTracking;

import java.io.IOException;
import java.nio.file.*;

public class WatchFile extends Thread{
    public static Path path;
    private volatile boolean running = true;
    private SocketClient socketClient;
    public WatchFile(String pathString, SocketClient socketClient) {
        this.path = Paths.get(pathString);
        this.socketClient = socketClient;
        start();
    }


    public void terminate() {
        running = false;
    }

    public static void setPath(String stringPath) {
        WatchFile.path = Paths.get(stringPath);
    }


    @Override
    public void run() {
        int i=0;
        try{
            WatchService watchService = FileSystems.getDefault().newWatchService();
            WatchKey key = path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while(true){
                for(WatchEvent<?> event : key.pollEvents()){
                    socketClient.sendAction(new FolderTracking(event.kind().toString(), event.context().toString()));
                    System.out.println("Event type " + event.kind() +" File affected " + event.context());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
