package Util;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Objects;

public class Util {
    public void listChildFolder(){

        File dir = new File("D:\\cv");
        File[] children = dir.listFiles();
        for (File file : children) {
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getName());
        }

    }

    public static void main(String[] args) {
        Util a = new Util();
        a.listChildFolder();
    }

}
