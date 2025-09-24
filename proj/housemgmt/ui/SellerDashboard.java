/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proj.housemgmt.ui;

import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proj.housemgmt.dao.HouseDao;
//import proj.housemgmt.dao.TransactionDao;
import proj.housemgmt.dao.impl.HouseDaoimpl;
//import proj.housemgmt.dao.impl.TransactionDaoimpl;
import proj.housemgmt.model.House;
//import proj.housemgmt.model.Transaction;
import proj.housemgmt.model.User;
/**
 *
 * @author didhitimaharjan
 */
public class SellerDashboard extends javax.swing.JFrame {
    
    private final  HouseDao houseDao;
    //private final TransactionDao transactionDao;
    

    private final String[] columns = new String[]{"House no.", "Type", "Area(sq.ft)", "Address", "Price(lakh)","Status"};


    private final DefaultTableModel houseModel = new DefaultTableModel();
        //private final DefaultTableModel transactionModel = new DefaultTableModel();
   
    
    public SellerDashboard(User seller) {
        this.houseDao = new HouseDaoimpl();
        //this.transactionDao = new TransactionDaoimpl();

        initComponents();
        houseTable.getTableHeader().setFont(new Font("Kannada Sangam MN",Font.PLAIN, 16));
        houseTable.setFont(new Font("Kannada Sangam MN", Font.PLAIN, 14));
        houseTable.setRowHeight(30); // sets each row to 30px tall

        statusButton.add(availableButton);
        statusButton.add(soldButton);

        setUpTableModel();
        findAllHouses();
        //findAllTransactions();
        
       
    }
    private void saveHouse() throws NumberFormatException, HeadlessException {
        try {
            House house = getValueFromTextField();
            int rowCount = houseDao.save(house);
            if (rowCount >= 1) {
                showMessageDialog("House sucessfully added to the site. ");
                resetForm();
                findAllHouses();
            } else {
                showMessageDialog("Something went wrong");
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
    private void updateHouse() throws NumberFormatException {
        try {
            int selectedRow = houseTable.getSelectedRow();
            int houseno = (int) houseTable.getValueAt(selectedRow, 0);
            House house = houseDao.findOne(houseno);
            if (editOrUpdateButton.getText().equals("EDIT")) {
                editOrUpdateButton.setText("UPDATE");
                
                typeDropdown.setSelectedItem(house.getType());
                areaTextField.setText(String.valueOf(house.getArea()));
                addressTextField.setText(house.getAddress());
                priceTextField.setText(String.valueOf(house.getPrice()));
                if (house.getStatus().equalsIgnoreCase("AVAILABLE")) {
                availableButton.setSelected(true);
                } else if (house.getStatus().equalsIgnoreCase("SOLD")) {
                soldButton.setSelected(true);
                } else {
                statusButton.clearSelection(); // no selection if status unknown
            }

                
            } else if (editOrUpdateButton.getText().equals("UPDATE")) {
                house.setType((String) typeDropdown.getSelectedItem());
                house.setArea(Double.parseDouble(areaTextField.getText()));
                house.setAddress(addressTextField.getText());
                house.setPrice(Double.parseDouble(priceTextField.getText()));
                house.setStatus(availableButton.isSelected() ? "AVAILABLE" :
                soldButton.isSelected() ? "SOLD" : "Unknown");
                int rowCount = houseDao.update(house, houseno);

                if (rowCount >= 1) {
                    showMessageDialog("Property sucessfully updated");
                    resetForm();
                    findAllHouses();
                    editOrUpdateButton.setText("EDIT");
                } else {
                    showMessageDialog("Something went wrong");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
    private void removeHouse() {
        int selectedRow = houseTable.getSelectedRow();
        int houseno = (int) houseTable.getValueAt(selectedRow, 0);
        try {
            House house = houseDao.findOne(houseno);
            if (house != null) {
                int rowCount = houseDao.remove(houseno);
                if (rowCount >= 1) {
                    showMessageDialog("Property sucessfully deleted from the list.");
                    findAllHouses();
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

    private void findAllHouses() {
        houseModel.setRowCount(0);
        try {
            List<House> houses = houseDao.findAll();
            for (House house : houses) {
                Object[] row = {house.getHouseno(), house.getType(), 
                    house.getArea(), house.getAddress(), house.getPrice(),house.getStatus()};
                houseModel.addRow(row);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }
//    private void findAllTransactions() {
//        transactionModel.setRowCount(0);
//        transactionModel.setColumnIdentifiers(new String[]{"Transaction ID", "Buyer", "House Type", "Address", "Amount", "Date"});
//        
//
//        try {
//            List<Transaction> transactions = transactionDao.findAll();
//            for (Transaction t : transactions) {
//                transactionModel.addRow(new Object[]{
//                        t.getTransactionid(),
//                        t.getBuyerUsername(),
//                        t.getHouseType(),
//                        t.getHouseAddress(),
//                        t.getAmount(),
//                        t.getDate()
//                });
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            showMessageDialog(ex.getMessage());
//        }
//    }


    private void searchHouse() {
        String query = searchHouse.getText();
        if (query.length() == 0) {
            findAllHouses();
            
        } else {
            houseModel.setRowCount(0);
            try {
                List<House> houses = houseDao.search(query);
                for (House house : houses) {
                    Object[] row = {house.getHouseno(), house.getType(), 
                    house.getArea(), house.getAddress(), house.getPrice(),house.getStatus()};
                    houseModel.addRow(row);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                showMessageDialog(ex.getMessage());
            }
        }
    }

    private void setUpTableModel() {
        houseTable.setModel(houseModel);
        houseModel.setColumnIdentifiers(columns);
    }

    
    
    

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private House getValueFromTextField() throws NumberFormatException {
    String type = (String) typeDropdown.getSelectedItem();
    double area = Double.parseDouble(areaTextField.getText());
    String address = addressTextField.getText();
    double price = Double.parseDouble(priceTextField.getText());
    String status = availableButton.isSelected() ? "Available" : soldButton.isSelected() ? "Sold" : "Unknown";    
    
    return new House(type, area, address, price, status);
}

    private void resetForm() {
        areaTextField.setText("");
        addressTextField.setText("");
        priceTextField.setText("");
         if (typeDropdown.getItemCount() > 0) {
        typeDropdown.setSelectedIndex(0);
    }
    availableButton.setSelected(true);
    editOrUpdateButton.setText("EDIT");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        statusButton = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        areaTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        priceTextField = new javax.swing.JTextField();
        availableButton = new javax.swing.JRadioButton();
        soldButton = new javax.swing.JRadioButton();
        typeDropdown = new javax.swing.JComboBox<>();
        insertButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editOrUpdateButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        tableSearch = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        SearchLabel = new javax.swing.JLabel();
        searchHouse = new javax.swing.JTextField();
        ScrollPanel = new javax.swing.JScrollPane();
        houseTable = new javax.swing.JTable();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(236, 236, 232));

        jPanel1.setBackground(new java.awt.Color(245, 234, 224));

        jLabel1.setText("Type");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("SELLER CONTROL");

        jLabel3.setText("Area");

        jLabel4.setText("Address");

        jLabel5.setText("Price");

        jLabel6.setText("Status");

        areaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaTextFieldActionPerformed(evt);
            }
        });

        statusButton.add(availableButton);
        availableButton.setText("AVAILABLE");

        statusButton.add(soldButton);
        soldButton.setText("SOLD");

        typeDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mansion", "Apartment", "Single Family Home", "Cabin", "Duplex", "Farmhouse" }));
        typeDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeDropdownActionPerformed(evt);
            }
        });

        insertButton.setText("INSERT");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editOrUpdateButton.setText("EDIT");
        editOrUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOrUpdateButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("LOGOUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(47, 47, 47)
                                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1))
                                    .addGap(67, 67, 67)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(typeDropdown, 0, 310, Short.MAX_VALUE)
                                        .addComponent(areaTextField))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(priceTextField)
                                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(availableButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(soldButton)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editOrUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(typeDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(availableButton)
                        .addComponent(soldButton)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertButton)
                    .addComponent(deleteButton)
                    .addComponent(editOrUpdateButton))
                .addGap(18, 18, 18)
                .addComponent(logoutButton)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        tableSearch.setBackground(new java.awt.Color(226, 212, 201));

        searchPanel.setBackground(new java.awt.Color(212, 188, 174));

        SearchLabel.setText("Search");

        searchHouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchHouseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SearchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchHouse)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchLabel)
                    .addComponent(searchHouse, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ScrollPanel.setBackground(new java.awt.Color(212, 188, 174));

        houseTable.setFont(new java.awt.Font("Kannada Sangam MN", 0, 15)); // NOI18N
        houseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        ScrollPanel.setViewportView(houseTable);

        javax.swing.GroupLayout tableSearchLayout = new javax.swing.GroupLayout(tableSearch);
        tableSearch.setLayout(tableSearchLayout);
        tableSearchLayout.setHorizontalGroup(
            tableSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tableSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE))
                .addContainerGap())
        );
        tableSearchLayout.setVerticalGroup(
            tableSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPanel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void areaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaTextFieldActionPerformed

    private void typeDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeDropdownActionPerformed

    }//GEN-LAST:event_typeDropdownActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        saveHouse();
            }//GEN-LAST:event_insertButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        removeHouse();   
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editOrUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOrUpdateButtonActionPerformed
        updateHouse(); 
        // TODO add your handling code here:
    }//GEN-LAST:event_editOrUpdateButtonActionPerformed

    private void searchHouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchHouseActionPerformed
        searchHouse();        // TODO add your handling code here:
    }//GEN-LAST:event_searchHouseActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
    dispose(); // close dashboard
    new Login().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPanel;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JTextField areaTextField;
    private javax.swing.JRadioButton availableButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editOrUpdateButton;
    private javax.swing.JTable houseTable;
    private javax.swing.JButton insertButton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JTextField searchHouse;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JRadioButton soldButton;
    private javax.swing.ButtonGroup statusButton;
    private javax.swing.JPanel tableSearch;
    private javax.swing.JComboBox<String> typeDropdown;
    // End of variables declaration//GEN-END:variables

    
}
