package UI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

public class UIServer extends JFrame{
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new UIServer().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JButton jButtonDanhSachIP;
    private JButton jbuttonLichSu;
    private JButton jbuttonGianSat;
    private JLabel jLablePort;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextField jTextIP;
    private JTextField jTextPort;
    private JTextPane jTextPane1;
    private JTree jTreeFolder;


    private DefaultMutableTreeNode listChildFolder(File file, int level, DefaultMutableTreeNode root){

        if (file.isDirectory()) { // Dừng nếu là tập tin
            if(Objects.nonNull(root)){
                System.out.println(getPadding(level) + " - " + file.getName());
                root.add(new DefaultMutableTreeNode(file.getName()));
            }else{
                root = new DefaultMutableTreeNode(file.getName());
            }
            File[] children = file.listFiles();
            for (File child : children) {
                root = this.listChildFolder(child, 0 + 1, root); // Gọi đệ quy
            }
        }
        return root;
    }

    private String getPadding(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= level; i++) {
            sb.append("    "); // Thêm dấu tab.
        }
        return sb.toString();
    }

    private DefaultMutableTreeNode initJTree(){
        File file = new File("D:\\cv");
        DefaultMutableTreeNode root = this.listChildFolder(file,0, null);
        return root;
    }

    public UIServer() throws IOException {
        DefaultMutableTreeNode root = initJTree();
        ServerSocket socket = new ServerSocket(3200);
        jPanel2 = new JPanel();
        jLabel2 = new JLabel();
        jTextIP = new JTextField();
        jLablePort = new JLabel();
        jTextPort = new JTextField();
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTextPane1 = new JTextPane();
        jScrollPane2 = new JScrollPane();
        jTreeFolder = new JTree(root);
        jButtonDanhSachIP = new JButton();
        jbuttonLichSu = new JButton();
        jbuttonGianSat = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(552, 430));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 22767));
        jPanel2.setPreferredSize(new java.awt.Dimension(752, 50));

        jLabel2.setText("jLabel2");

        jTextIP.setText("127.0.0.1");
        jTextIP.setEnabled(false);

        jLablePort.setText("Port");

        jTextPort.setText(String.valueOf(socket.getLocalPort()));
        jTextPort.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel2)
                                .addGap(31, 31, 31)
                                .addComponent(jTextIP, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLablePort)
                                .addGap(18, 18, 18)
                                .addComponent(jTextPort, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLablePort)
                                        .addComponent(jTextPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane2.setViewportView(jTreeFolder);

        jButtonDanhSachIP.setText("Danh sách IP Client");

        jbuttonLichSu.setText("Lịch sử giám sát");
        jbuttonLichSu.setToolTipText("");

        jbuttonGianSat.setText("Giám sát");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonDanhSachIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jbuttonLichSu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jbuttonGianSat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 28, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jButtonDanhSachIP)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jbuttonLichSu)
                                                .addGap(18, 18, 18)
                                                .addComponent(jbuttonGianSat))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }


}
