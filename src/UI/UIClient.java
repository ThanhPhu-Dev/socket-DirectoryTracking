package UI;

import Util.Client.SocketClient;
import Util.Util;
import Util.WatchFile;
import model.FolderTracking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private JComboBox cbFilter;
    private JTextField tfFilter;
    private JButton btFilter;
    private String path;
    public UIClient(String path){
        lbIP = new JLabel();
        tfIP = new JTextField();
        lbPort = new JLabel();
        tfPort = new JTextField();
        btnKetNoi = new JButton();
        message = new JLabel();
        jScrollPane1 = new JScrollPane();
        tbHistory = new JTable();
        socketClient = new SocketClient();
        btFilter = new JButton();
        cbFilter = new JComboBox();
        tfFilter = new JTextField();
        this.path = path;
        initUI();
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

        jScrollPane1.setViewportView(tbHistory);

        jScrollPane1.setViewportView(tbHistory);

        btFilter.setText("Tìm Kiếm");
        btFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFilterActionPerformed(evt);
            }
        });

        cbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Thời Điểm", "Action" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(135, 135, 135)
                                                .addComponent(btnKetNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(cbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(tfFilter)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btFilter)
                                                                .addGap(13, 13, 13))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lbIP)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)
                                                                .addComponent(lbPort)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(186, 186, 186))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(message)
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbIP)
                                        .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPort)
                                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKetNoi)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btFilter)
                                        .addComponent(cbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }

    private void btnKetNoiActionPerformed(java.awt.event.ActionEvent evt) {
        if(btnKetNoi.getText().equals("Kết nối")){
            if(!socketClient.connect(tfIP.getText(), tfPort.getText())){
                message.setText("kết nối thất bại.");
                return;
            }
            btnKetNoi.setText("Ngắt kết nối");
            tfIP.setEnabled(false);
            tfPort.setEnabled(false);
            watchFile = new WatchFile(path, socketClient, tbHistory);
        }else{
            btnKetNoi.setText("Kết nối");
            tfPort.setEnabled(true);
            tfIP.setEnabled(true);
            socketClient.sendMessage("quit");
            watchFile.terminate();
        }
    }


    private void btFilterActionPerformed(java.awt.event.ActionEvent evt) {
        String value = cbFilter.getSelectedItem().toString();
        watchFile.filter(value, tfFilter.getText());
    }
}
