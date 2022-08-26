import UI.UIClient;
import UI.UIServer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        UIServer uiServer = new UIServer();
        Thread thread1 = new Thread(() -> {
            UIClient a = new UIClient(System.getProperty("user.dir") + File.separator + "Data1");
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            UIClient b = new UIClient(System.getProperty("user.dir") + File.separator + "Data2");
        });
        thread2.start();
    }
}
