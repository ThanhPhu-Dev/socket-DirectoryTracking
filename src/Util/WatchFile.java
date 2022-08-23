package Util;

import java.io.IOException;
import java.nio.file.*;

public class WatchFile extends Thread{
    public static Path path;
    private volatile boolean running = true;

    public WatchFile(String pathString) {
        this.path = Paths.get(pathString);
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
        try{
            WatchService watchService = FileSystems.getDefault().newWatchService();
            WatchKey key = path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            while(running){
                for(WatchEvent<?> event : key.pollEvents()){
                    System.out.println("Event type " + event.kind() +" File affected " + event.context());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
