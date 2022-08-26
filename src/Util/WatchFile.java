package Util;

import Util.Client.SocketClient;
import model.FolderTracking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WatchFile extends Thread{
    public static Path path;
    private volatile boolean running = true;
    private SocketClient socketClient;
    public static String[] title = {"STT", "thời điểm", "Action", "Diễn giải"};
    private JTable tbHistory;
    private DefaultTableModel model =  null;
    public WatchFile(String pathString, SocketClient socketClient, JTable tbHistory) {
        this.path = Paths.get(pathString);
        this.socketClient = socketClient;
        this.tbHistory = tbHistory;
        this.model = new DefaultTableModel(title, 0);
        this.tbHistory.setModel(model);
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

            while(running){
                for(WatchEvent<?> event : key.pollEvents()){
                    FolderTracking folderTracking = new FolderTracking(event.kind().toString(), event.context().toString());
                    socketClient.sendAction(folderTracking);
                    setDataTable(folderTracking);
                    System.out.println("Event type " + event.kind() +" File affected " + event.context());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setDataTable(FolderTracking data) {

        try {
            List<FolderTracking> folderTrackings = Util.readFile();
            if (Objects.nonNull(folderTrackings)) {
                folderTrackings = new ArrayList<>();
            }
                folderTrackings.add(data);
            for(int i=0;i<folderTrackings.size();i++){
                folderTrackings.get(i).setStt(i+1);
                model.addRow(new String[]{String.valueOf(folderTrackings.get(i).getStt()), folderTrackings.get(i).getTime().toString(), folderTrackings.get(i).getAction(), folderTrackings.get(i).getDescription()});
            }

            this.tbHistory.setModel(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
