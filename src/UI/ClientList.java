package UI;

import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class ClientList extends JFrame {
    public static String[] title = {"IP", "Port"};
    private JButton btSearch;
    private JScrollPane jScrollPane1;
    private JLabel lbIP;
    private JTable tbClient;
    private JTextField tfIP;
    private List<Client> clients;
    private javax.swing.JButton btReset;
    public ClientList(List<Client> data){
        lbIP = new JLabel();
        tfIP = new JTextField();
        btSearch = new JButton();
        jScrollPane1 = new JScrollPane();
        tbClient = new JTable();
        btReset = new JButton();
        this.clients = data;
        init();
        setDataTable(clients);
    }

    public void setDataTable(List<Client> data) {
        DefaultTableModel model = new DefaultTableModel(title,0);
        data.forEach(m -> {
            model.addRow(new String[] {m.getIp(), String.valueOf(m.getPort())});
        });
        this.tbClient.setModel(model);
    }

    @Override
    public synchronized void addWindowListener(WindowListener l) {
        this.setVisible(false);
    }

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String ip = tfIP.getText();
        List<Client> result = new ArrayList<>();
        for (Client client : clients) {
            if(client.getIp().equals(ip))
                result.add(client);
        }
        setDataTable(result);
    }
    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {
        setDataTable(clients);
    }

    public void init(){
        lbIP.setText("IP");

        btSearch.setText("Tìm Kiếm");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tbClient);

        btReset.setText("Khôi phục");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(lbIP)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btSearch)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btReset))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbIP)
                                        .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btSearch)
                                        .addComponent(btReset))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }
}
