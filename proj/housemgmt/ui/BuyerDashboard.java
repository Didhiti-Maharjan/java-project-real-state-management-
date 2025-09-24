/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proj.housemgmt.ui;

//import java.security.Timestamp;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import static proj.housemgmt.connection.ConnectionFactory.getConnection;
import proj.housemgmt.dao.HouseDao;
import proj.housemgmt.dao.impl.HouseDaoimpl;
import proj.housemgmt.model.House;
import java.sql.PreparedStatement;
import proj.housemgmt.connection.ConnectionFactory;
import proj.housemgmt.model.User;


    
/**
 *
 * @author didhitimaharjan
 */
//public class BuyerDashboard extends javax.swing.JFrame {
//    
//    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BuyerDashboard.class.getName());
//    private final HouseDao houseDao = new HouseDaoimpl();
//    private final TransactionDao transactionDao = new TransactionDaoimpl();
//    private final int currentBuyerId = 1;
//    private final String[] houseColumns = new String[]{"House no.", "Type", "Area", "Address", "Price","Status"};
//    private final String[] transactionColumns = new String[]{"Transaction ID", "House No.", "Type", "Area", "Address", "Price", "Status", "Date"};
//
//
//    private final DefaultTableModel houseModel = new DefaultTableModel();
//    private final DefaultTableModel transactionModel = new DefaultTableModel();
//
//    
//    public BuyerDashboard() {
//        initComponents();
//        setUpTableModel();
//        setUpTransactionTableModel();
//        findAllHouses();
//        statusButton.add(availableButton);
//        statusButton.add(soldButton);
//        typeDropdown.setEditable(false);
//        refreshTransactionHistory(currentBuyerId);
//
//    
//    }
//    
//    private void setUpTableModel() {
//        houseTable.setModel(houseModel);
//        houseModel.setColumnIdentifiers(houseColumns);
//    }
//    private void setUpTransactionTableModel() {
//        transactionTable.setModel(transactionModel);
//        transactionModel.setColumnIdentifiers(transactionColumns);
//    }
//    
//    private void showMessageDialog(String message) {
//        JOptionPane.showMessageDialog(null, message);
//    }
//    
//    private void findAllHouses() {
//        houseModel.setRowCount(0);
//        try {
//            List<House> houses = houseDao.findAll();
//            for (House house : houses) {
//                Object[] row = {house.getHouseno(), house.getType(), 
//                    house.getArea(), house.getAddress(), house.getPrice(),house.getStatus()};
//                houseModel.addRow(row);
//            }
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            showMessageDialog(ex.getMessage());
//        }
//    }
//private void searchHouses() {    
//        String query = searchHouse.getText();
//        if (query.length() == 0) {
//            findAllHouses();
//        } else {
//            houseModel.setRowCount(0);
//            try {
//                List<House> houses = houseDao.search(query);
//                for (House house : houses) {
//                    Object[] row = {house.getHouseno(), house.getType(), 
//                    house.getArea(), house.getAddress(), house.getPrice(),house.getStatus()};
//                    houseModel.addRow(row);
//                }
//            } catch (SQLException | ClassNotFoundException ex) {
//                showMessageDialog(ex.getMessage());
//            }
//        }
//    }
    
    
//    private void selectHouse() throws SQLException, ClassNotFoundException{
//        try{
//        int selectedRow = houseTable.getSelectedRow();
//        int houseno = (int) houseTable.getValueAt(selectedRow, 0);
//        House house = houseDao.findOne(houseno);
//        
//        typeDropdown.setSelectedItem(house.getType());
//                areaTextField.setText(String.valueOf(house.getArea()));
//                addressTextField.setText(house.getAddress());
//                priceTextField.setText(String.valueOf(house.getPrice()));
//                if (house.getStatus().equalsIgnoreCase("Available")) {
//                availableButton.setSelected(true);
//                } else if (house.getStatus().equalsIgnoreCase("Sold")) {
//                showMessageDialog("property is soldout");
//                } 
//          }   catch (SQLException | ClassNotFoundException ex) {
//                showMessageDialog(ex.getMessage());
//            }  
//        soldButton.setVisible(false);
//    }
//    private void selectHouse() throws SQLException, ClassNotFoundException {
//        int selectedRow = houseTable.getSelectedRow();
//        if (selectedRow != -1) {
//            int houseno = (int) houseTable.getValueAt(selectedRow, 0);
//            try {
//                House house = houseDao.findOne(houseno);
//                if (house != null) {
//                    typeDropdown.setSelectedItem(house.getType());
//                    areaTextField.setText(String.valueOf(house.getArea()));
//                    addressTextField.setText(house.getAddress());
//                    priceTextField.setText(String.valueOf(house.getPrice()));
//                    availableButton.setSelected(house.getStatus().equalsIgnoreCase("Available"));
//                    soldButton.setSelected(house.getStatus().equalsIgnoreCase("Sold"));
//                }
//            } catch (SQLException | ClassNotFoundException ex) {
//                showMessageDialog(ex.getMessage());
//            }
//        }
//    }
    
//    private void purchase() throws NumberFormatException, HeadlessException, SQLException, ClassNotFoundException {
//        int rowCount = cartTable.getRowCount();
//        try{
//        for (int i = 0; i < rowCount; i++) {
//            String itemName = cartTable.getValueAt(i, 0).toString();  
//            int quantityBought = Integer.parseInt(cartTable.getValueAt(i, 1).toString());
//            Double price = (Double) cartTable.getValueAt(i, 2);
//            
//            ItemsCheckout sales = new ItemsCheckout(itemName,quantityBought,price);
//            
//            booksDao.save(sales);
//            
//            String selectQuery = "SELECT stock FROM items WHERE name = ?";
//            PreparedStatement selectStmt = getConnection().prepareStatement(selectQuery);
//            selectStmt.setString(1, itemName);
//            ResultSet rs = selectStmt.executeQuery();
//
//            if (rs.next()) {
//                int currentStock = rs.getInt("stock");
//                int newStock = currentStock - quantityBought;
//
//                if (newStock < 0) {
//                    JOptionPane.showMessageDialog(this, "Not enough stock for: " + itemName);
//                } else {
//                    // Step 2: Update stock
//                    String updateQuery = "UPDATE items SET stock = ? WHERE name = ?";
//                    PreparedStatement updateStmt = getConnection().prepareStatement(updateQuery);
//                    updateStmt.setInt(1, newStock);
//                    updateStmt.setString(2, itemName);
//                    updateStmt.executeUpdate();
//                }
//            }
//        }
//        
//
//        JOptionPane.showMessageDialog(this, "Checkout successful! Stock updated.");
//        cartTableModel.setRowCount(0);
//        }catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Error during checkout: " + e.getMessage());
//    }
//        findAll();
//    }
//private void purchase() {
//    buyButton.addActionListener(e -> {
//        int selectedRow = houseTable.getSelectedRow();
//        if (selectedRow != -1) {
//            double area = Double.parseDouble(areaTextField.getText());
//            String updateQuery = "UPDATE houses SET status = ? WHERE area = ?";
//
//            try {
//                PreparedStatement updateStmt = getConnection().prepareStatement(updateQuery);
//                updateStmt.setString(1, "Sold"); // fixed typo (SOlD → Sold)
//                updateStmt.setDouble(2, area);
//
//                int rowsUpdated = updateStmt.executeUpdate(); // ✅ execute the update
//
//                if (rowsUpdated > 0) {
//                    JOptionPane.showMessageDialog(this, "Property purchased successfully!");
//                } else {
//                    JOptionPane.showMessageDialog(this, "No property found to update!");
//                }
//
//                findAll(); // refresh the table
//
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(this, "Error updating property: " + ex.getMessage());
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a property to buy.");
//        }
//    });
//
public final class BuyerDashboard extends javax.swing.JFrame {

    private final HouseDao houseDao = new HouseDaoimpl();
    private final int currentBuyerId = 1; 

    private final String[] houseColumns = new String[]{"House No.", "Type", "Area(sq.ft)", "Address", "Price(lakh)", "Status"};

    private final DefaultTableModel houseModel = new DefaultTableModel();

    public BuyerDashboard(User buyer) {

        initComponents();
        setUpHouseTable();
        findAllHouses();
        refreshHouseList();
        houseTable.getTableHeader().setFont(new Font("Kannada Sangam MN",Font.PLAIN, 16));
        houseTable.setFont(new Font("Kannada Sangam MN", Font.PLAIN, 14));
        houseTable.setRowHeight(30); // sets each row to 30px tall

    }

    private void setUpHouseTable() {
        houseTable.setModel(houseModel);
        houseModel.setColumnIdentifiers(houseColumns);
    }

   

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    // Load all houses
    private void findAllHouses() {
        houseModel.setRowCount(0);
        try {
            List<House> houses = houseDao.findAll();
            for (House house : houses) {
                Object[] row = {house.getHouseno(), house.getType(), house.getArea(),
                        house.getAddress(), house.getPrice(), house.getStatus()};
                houseModel.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            showMessageDialog(ex.getMessage());
        }
    }

    // Search houses
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

    // Select house details
    private void selectHouse() {
        int selectedRow = houseTable.getSelectedRow();
        if (selectedRow != -1) {
            int houseno = (int) houseTable.getValueAt(selectedRow, 0);
            try {
                House house = houseDao.findOne(houseno);
                if (house != null) {
                    typeDropdown.setSelectedItem(house.getType());
                    areaTextField.setText(String.valueOf(house.getArea()));
                    addressTextField.setText(house.getAddress());
                    priceTextField.setText(String.valueOf(house.getPrice()));
                    availableButton.setSelected(house.getStatus().equalsIgnoreCase("AVAILABLE"));
                    soldButton.setSelected(house.getStatus().equalsIgnoreCase("SOLD"));
                }
            } catch (SQLException | ClassNotFoundException ex) {
                showMessageDialog(ex.getMessage());
            }
        }
    }

    // Purchase selected house
    private void purchaseHouse(BuyerDashboard buyerDashboard, int currentBuyerId) {
    int selectedRow = houseTable.getSelectedRow();
    if (selectedRow != -1) {
        int houseno = (int) houseTable.getValueAt(selectedRow, 0);
       // String buyer = houseTable.getValueAt(selectedRow, 1);
        
        try {
            House house = houseDao.findOne(houseno);
            if (house == null || house.getStatus().equalsIgnoreCase("SOLD")) {
                showMessageDialog("This house is already sold!");
                return;
            }

            // Update house status to Sold in database
            String updateQuery = "UPDATE houses SET status = ? WHERE houseno = ?";
            PreparedStatement updateStmt = ConnectionFactory.getConnection().prepareStatement(updateQuery);
            updateStmt.setString(1, "SOLD");
            updateStmt.setInt(2, houseno);
            findAllHouses();
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                showMessageDialog("Property purchased successfully!");

                // Record transaction
                
            }
//            TransactionDao trans = new TransactionDaoimpl();
//            trans.insert(tx);
            findAllHouses();

        } catch (ClassNotFoundException | SQLException ex) {
            showMessageDialog("Error: " + ex.getMessage());
        }
    } else {
        showMessageDialog("Please select a property to buy.");
    }
}
public void refreshHouseList() {
    houseModel.setRowCount(0); // clear current table
    try {
        List<House> houses = houseDao.findAll(); // MUST fetch from DB
        for (House h : houses) {
            Object[] row = {
                h.getHouseno(),
                h.getType(),
                h.getArea(),
                h.getAddress(),
                h.getPrice(),
                h.getStatus() // should be "SOLD" if DB updated
            };
            houseModel.addRow(row);
        }
    } catch (SQLException | ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
}



    // Load buyer transaction history
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusButton = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
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
        buyButton = new javax.swing.JButton();
        selectHouse = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        tableSearch = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        SearchLabel = new javax.swing.JLabel();
        searchHouse = new javax.swing.JTextField();
        ScrollPanel = new javax.swing.JScrollPane();
        houseTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(236, 236, 232));

        jPanel2.setBackground(new java.awt.Color(245, 234, 224));

        jLabel7.setBackground(new java.awt.Color(130, 115, 95));
        jLabel7.setFont(new java.awt.Font("Papyrus", 0, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Buy Yourself Your Own HOME");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(245, 234, 224));

        jLabel1.setText("Type");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("HOUSE INFORMATION");

        jLabel3.setText("Area");

        jLabel4.setText("Address");

        jLabel5.setText("Price");

        jLabel6.setText("Status");

        areaTextField.setEditable(false);
        areaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaTextFieldActionPerformed(evt);
            }
        });

        addressTextField.setEditable(false);

        priceTextField.setEditable(false);

        statusButton.add(availableButton);
        availableButton.setText("AVAILABLE");

        statusButton.add(soldButton);
        soldButton.setText("SOLD");

        typeDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mansion", "Apartment", "Single Family Home", "Cabin", "Duplex", "Farmouse" }));
        typeDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeDropdownActionPerformed(evt);
            }
        });

        buyButton.setText("BUY");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        selectHouse.setText("SELECT");
        selectHouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectHouseActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(47, 47, 47)
                                .addComponent(addressTextField))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeDropdown, 0, 230, Short.MAX_VALUE)
                                    .addComponent(areaTextField)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(priceTextField)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(availableButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(soldButton))
                                            .addComponent(selectHouse, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(typeDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(buyButton)
                    .addComponent(selectHouse))
                .addGap(18, 18, 18)
                .addComponent(logoutButton)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        tableSearch.setBackground(new java.awt.Color(245, 234, 224));

        searchPanel.setBackground(new java.awt.Color(226, 212, 201));

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScrollPanel.setViewportView(houseTable);

        javax.swing.GroupLayout tableSearchLayout = new javax.swing.GroupLayout(tableSearch);
        tableSearch.setLayout(tableSearchLayout);
        tableSearchLayout.setHorizontalGroup(
            tableSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tableSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
                .addContainerGap())
        );
        tableSearchLayout.setVerticalGroup(
            tableSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchHouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchHouseActionPerformed
        searchHouse();       // TODO add your handling code here:
    }//GEN-LAST:event_searchHouseActionPerformed

    private void typeDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeDropdownActionPerformed

    }//GEN-LAST:event_typeDropdownActionPerformed

    private void areaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaTextFieldActionPerformed

    private void selectHouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectHouseActionPerformed
       selectHouse();
        
    }//GEN-LAST:event_selectHouseActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        purchaseHouse(this, currentBuyerId);
        
    }//GEN-LAST:event_buyButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
dispose(); // close dashboard
    new Login().setVisible(true);
     }//GEN-LAST:event_logoutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new BuyerDashboard().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPanel;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JTextField areaTextField;
    private javax.swing.JRadioButton availableButton;
    private javax.swing.JButton buyButton;
    private javax.swing.JTable houseTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JTextField searchHouse;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JButton selectHouse;
    private javax.swing.JRadioButton soldButton;
    private javax.swing.ButtonGroup statusButton;
    private javax.swing.JPanel tableSearch;
    private javax.swing.JComboBox<String> typeDropdown;
    // End of variables declaration//GEN-END:variables
}
