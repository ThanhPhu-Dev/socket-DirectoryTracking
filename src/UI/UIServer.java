package UI;

import Util.Server.SocketServer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
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

    private JButton btDanhSachIP;
    private JButton btLichSu;
    private JButton btGiamSat;
    private JLabel lbPort;
    private JLabel lbIp;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextField jTextIP;
    private JTextField jTextPort;
    private JTextPane tpKetNoi;
    private JTree treeFolder;


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
        jPanel2 = new JPanel();
        lbIp = new JLabel();
        jTextIP = new JTextField();
        lbPort = new JLabel();
        jTextPort = new JTextField();
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tpKetNoi = new JTextPane();
        jScrollPane2 = new JScrollPane();
        treeFolder = new JTree();
        btDanhSachIP = new JButton();
        btLichSu = new JButton();
        btGiamSat = new JButton();
        SocketServer socketServer = new SocketServer(tpKetNoi);
        init();
    }

    public void init() {
//        DefaultMutableTreeNode root = initJTree();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(552, 430));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 22767));
        jPanel2.setPreferredSize(new java.awt.Dimension(752, 50));

        lbIp.setText("IP");

        jTextIP.setText("127.0.0.1");
        jTextIP.setEnabled(false);

        lbPort.setText("Port");

        jTextPort.setText("3000");
        jTextPort.setEnabled(false);
        tpKetNoi.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(lbIp)
                                .addGap(31, 31, 31)
                                .addComponent(jTextIP, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(lbPort)
                                .addGap(18, 18, 18)
                                .addComponent(jTextPort, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbIp)
                                        .addComponent(jTextIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPort)
                                        .addComponent(jTextPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);

        jScrollPane1.setViewportView(tpKetNoi);

        jScrollPane2.setViewportView(treeFolder);

        btDanhSachIP.setText("Danh sách IP Client");

        btLichSu.setText("Lịch sử giám sát");
        btLichSu.setToolTipText("");

        btGiamSat.setText("Giám sát");

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
                                                        .addComponent(btDanhSachIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btLichSu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btGiamSat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                .addComponent(btDanhSachIP)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btLichSu)
                                                .addGap(18, 18, 18)
                                                .addComponent(btGiamSat))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
    }


}
