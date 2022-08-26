package UI;

import Util.Server.ServerListens;
import Util.Server.SocketServer;
import Util.Util;
import model.FolderTracking;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UIServer extends JFrame{

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
    private SocketServer socketServer;
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
        btDanhSachIP = new JButton();
        btLichSu = new JButton();
        btGiamSat = new JButton();
        socketServer = new SocketServer(tpKetNoi);
        init();
    }

    public void init() {
//        DefaultMutableTreeNode root = initJTree();

        lbIp.setText("IP");

        jTextIP.setText("127.0.0.1");
        jTextIP.setEnabled(false);

        lbPort.setText("Port");

        jTextPort.setText("3000");
        jTextPort.setEnabled(false);
        tpKetNoi.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(552, 240));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setMaximumSize(new java.awt.Dimension(32767, 22767));
        jPanel2.setPreferredSize(new java.awt.Dimension(752, 50));

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
                                .addContainerGap(33, Short.MAX_VALUE))
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

        getContentPane().add(jPanel2);

        jScrollPane1.setViewportView(tpKetNoi);


        btDanhSachIP.setText("Danh sách IP Client");
        btDanhSachIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDanhSachIPActionPerformed(evt);
            }
        });

        btLichSu.setText("Lịch sử giám sát");
        btLichSu.setToolTipText("");
        btLichSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btLichSuActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(btDanhSachIP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btLichSu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btDanhSachIP)
                                        .addComponent(btLichSu))
                                .addContainerGap(33, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        pack();
        setVisible(true);
    }

    private void btDanhSachIPActionPerformed(java.awt.event.ActionEvent evt) {
        ClientList clientListFrame = new ClientList(socketServer.getClients());
    }

    private void btLichSuActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        List<FolderTracking> folderTrackings = Util.readFile();
        ActionHistory actionHistory = new ActionHistory(folderTrackings);
    }


}
