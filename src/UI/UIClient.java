package UI;

import Util.Client.SocketClient;
import Util.WatchFile;
import model.FolderTracking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Objects;

public class UIClient extends JFrame {
    private JButton btnKetNoi;
    private JLabel lbIP;
    private JLabel lbPort;
    private JTextField tfIP;
    private JTextField tfPort;
    private WatchFile watchFile = null;
    SocketClient socketClient = null;
    private javax.swing.JTable tbHistory;
    private JLabel message;
    private javax.swing.JScrollPane jScrollPane1;
    public UIClient(){
        lbIP = new JLabel();
        tfIP = new JTextField();
        lbPort = new JLabel();
        tfPort = new JTextField();
        btnKetNoi = new JButton();
        message = new JLabel();
        jScrollPane1 = new JScrollPane();
        tbHistory = new JTable();
        initUI();
        socketClient = new SocketClient();
    }




    public void initUI(){


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbIP.setText("IP");

        lbPort.setText("Port");

        btnKetNoi.setText("Kết nối");
        btnKetNoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetNoiActionPerformed(evt);
            }
        });

        tbHistory.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(tbHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(message)
                                .addGap(296, 296, 296))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(lbIP)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(lbPort)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(btnKetNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbIP)
                                        .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPort)
                                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnKetNoi)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        message.getAccessibleContext().setAccessibleName("message");

        pack();
        setVisible(true);
    }

    private void btnKetNoiActionPerformed(java.awt.event.ActionEvent evt) {
        if(!socketClient.connect(tfIP.getText(), tfPort.getText())){
            message.setText("kết nối thất bại.");
            return;
        }
        if(btnKetNoi.getText().equals("Kết nối")){
            btnKetNoi.setText("Ngắt kết nối");
            tfIP.setEnabled(false);
            tfPort.setEnabled(false);
            watchFile = new WatchFile("C:\\ClientMonitoringSystem\\Data", socketClient, tbHistory);


        }else{
            btnKetNoi.setText("Kết nối");
            tfPort.setEnabled(true);
            tfIP.setEnabled(true);
            watchFile.terminate();
        }
    }

}
