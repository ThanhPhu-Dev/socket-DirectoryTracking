package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIClient extends JFrame {
    public static void main(String[] args) {
        UIClient a = new UIClient();
    }
    private JButton btnKetNoi;
    private JLabel lbIP;
    private JLabel lbPort;
    private JTextField tfIP;
    private JTextField tfPort;

    public UIClient(){
        initUI();

    }


    public void initUI(){
        lbIP = new javax.swing.JLabel();
        tfIP = new javax.swing.JTextField();
        lbPort = new javax.swing.JLabel();
        tfPort = new javax.swing.JTextField();
        btnKetNoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbIP.setText("IP");
        lbPort.setText("Port");

        tfIP.setText("");
        tfPort.setText("");

        btnKetNoi.setText("Kết nối");
        btnKetNoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnKetNoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(43, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnKetNoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbIP)
                                                        .addComponent(lbPort))
                                                .addGap(33, 33, 33)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfIP)
                                                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbIP)
                                        .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbPort)
                                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(btnKetNoi)
                                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }

    private void btnKetNoiActionPerformed(java.awt.event.ActionEvent evt) {
        //check socket
        if(btnKetNoi.getText().equals("Kết nối")){
            btnKetNoi.setText("Ngắt kết nối");
            tfIP.setEnabled(false);
            tfPort.setEnabled(false);

        }else{
            btnKetNoi.setText("Kết nối");
            tfPort.setEnabled(true);
            tfIP.setEnabled(true);
        }
    }

}
