package UI;

import model.Client;
import model.FolderTracking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ActionHistory extends JFrame {
    private JButton btFilter;
    private JComboBox cbFilter;
    private JScrollPane jScrollPane1;
    private JTable tbHistory;
    private JTextField tfFilter;
    private List<FolderTracking> data;
    public ActionHistory(List<FolderTracking> data){
        jScrollPane1 = new JScrollPane();
        tbHistory = new JTable();
        cbFilter = new JComboBox();
        tfFilter = new JTextField();
        btFilter = new JButton();
        this.data = data;
        this.init();
        setDataTable(data);
    }
    public static String[] title = {"STT", "IP Client", "Thoi diem", "Action", "Dien giai"};
    public void setDataTable(List<FolderTracking> data) {
        DefaultTableModel model = new DefaultTableModel(title, 0);
        if (Objects.nonNull(data)) {
            for(int i=0;i<data.size();i++){
                data.get(i).setStt(i+1);
                model.addRow(new String[]{String.valueOf(data.get(i).getStt()),data.get(i).getIpClient(), data.get(i).getTime().toString(), data.get(i).getAction(), data.get(i).getDescription()});
            }
        }
        this.tbHistory.setModel(model);
    }

    private void init() {
        jScrollPane1.setViewportView(tbHistory);

        cbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Thời điểm", "Action", "Ip Client"}));

        btFilter.setText("Tìm Kiếm");
        btFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(53, 53, 53)
                                                .addComponent(cbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btFilter)))
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btFilter))
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }

    private void btFilterActionPerformed(java.awt.event.ActionEvent evt) {
        String value = cbFilter.getSelectedItem().toString();
        if(tfFilter.getText().trim().isEmpty()){
            setDataTable(this.data);
            return;
        }
        switch (value){
            case "Thời điểm":
                setDataTable(this.data.stream().filter(s -> s.getTime().equals(tfFilter.getText()))
                        .collect(Collectors.toList()));
                break;
            case "Action":
                setDataTable(this.data.stream().filter(s -> s.getAction().equals(tfFilter.getText()))
                        .collect(Collectors.toList()));
                break;
            default:
                setDataTable(this.data.stream().filter(s -> s.getIpClient().equals(tfFilter.getText()))
                        .collect(Collectors.toList()));
                break;
        }
    }
}
