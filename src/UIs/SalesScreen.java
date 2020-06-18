/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Products;
import Beans.Customer;
import Beans.FormatDate;
import Beans.Sales;
import Beans.SalesBreakdown;
import Beans.Staff;
import Model.DBConnection;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class SalesScreen extends javax.swing.JPanel {

    private DBConnection dbConnection = null;
    private ButtonGroup paymentModeButtonGroup;
    private ActionEvent radioButtonActionEvent;

    protected static DefaultTableModel customerDefaultTableModel, productDefaultTableModel, salesListDefaultTableModel;
    private ArrayList<Customer> customers;
    private ArrayList<Products> products;
    protected static Staff staff;

    private double totalPurchaseCost = 00.00;
    private int productTableProdID;
    private String name;
    private int availableQty;
    protected static int salesQty;

    /**
     * Creates new form ProductsScreen
     */
    public SalesScreen() {
        initComponents();

        paymentModeButtonGroup = new ButtonGroup();
        paymentModeButtonGroup.add(cashPayment);
        paymentModeButtonGroup.add(posPayment);
        paymentModeButtonGroup.add(cashTransfer);

        dbConnection = new DBConnection();
        customerDefaultTableModel = (DefaultTableModel) customerDetailsTable.getModel();
        productDefaultTableModel = (DefaultTableModel) productTable.getModel();
        salesListDefaultTableModel = (DefaultTableModel) salesListTable.getModel();

        updateCustomerTable();
        updateProductTable();
        totalPurchaseCostDisplay.setText(" Total: " + totalPurchaseCost);
        saleesIdField.setText((dbConnection.getlastSalesID() + 1) + "");     //set next sales id

        setCursorOnField();
        refreshTables();
    }

    //set cursor on search field on screen display
    public void setCursorOnField() {
        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                productSearchField.requestFocus();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    /*-------------customer table begins--------------------------------------------*/
    //update details on customers table
    public void updateCustomerTable() {
        customerDefaultTableModel.setRowCount(0);
        customers = dbConnection.getAllCustomers();
        populateCustomerTable(customers);
    }

    //method to populate the customer table
    protected static void populateCustomerTable(ArrayList<Customer> customers) {

        for (int index = 0; index < customers.size(); index++) {
            int customerID = customers.get(index).getCustomerID();
            String firstName = customers.get(index).getCustomerFirstName();
            String lastname = customers.get(index).getCustomerLastName();
            String phone = customers.get(index).getCustomerPhone();
            String email = customers.get(index).getCustomerEmail();

            insertIntoCustomerTable(customerID, firstName, lastname, phone, email);
        }
    }

    //populate customer table
    protected static void insertIntoCustomerTable(int customerID, String customerFirstName, String customerLastName, String customerPhone, String customerEmail) {
        String[] rowData = {customerID + "", customerFirstName, customerLastName, customerPhone, customerEmail};
        customerDefaultTableModel.addRow(rowData);
    }

    /*-------------customer table ends--------------------------------------------*/
 /*-------------products table begins--------------------------------------------*/
    //update details on products table
    public void updateProductTable() {
        productDefaultTableModel.setRowCount(0);
        products = dbConnection.getAllProducts();
        populateProductTable(products);
    }

    //method to populate the product table
    public void populateProductTable(ArrayList<Products> products) {

        for (int index = 0; index < products.size(); index++) {
            int productID = products.get(index).getProductID();
            String barcode = products.get(index).getBarcode();
//            String category = products.get(index).getCategory();
            String name = products.get(index).getName();
            double costPrice = products.get(index).getCostPrice();
            int quantity = products.get(index).getQuantity();
//            String purchaseDate = products.get(index).getDate();
            String description = products.get(index).getDescription();
//            String purchasedFrom = products.get(index).getPurchasedFrom();
            String image = products.get(index).getImage();

            insertIntoAdminProductTable(productID + "", barcode, name, costPrice + "", quantity + "", description);
        }
    }

    //populate product table
    public void insertIntoAdminProductTable(String productID, String barcode, String name, String costPrice, String quantity, String description) {
        String[] rowData = {productID, barcode, name, costPrice, quantity, description};
        productDefaultTableModel.addRow(rowData);
    }

    /*-------------products table ends--------------------------------------------*/
    //populate sales list table
    protected static void insertIntoSalesListTable(int productID, String name, double salesPrice, int quantity, double qtyXprice) {
        String[] rowData = {productID + "", name, salesPrice + "", quantity + "", qtyXprice + ""};
        salesListDefaultTableModel.addRow(rowData);
    }

    //update total sales amount display
    public void updateTotalSalesAmountDisplay() {
        for (int index = 0; index < salesListDefaultTableModel.getRowCount(); index++) {
            totalPurchaseCost += Double.parseDouble(salesListTable.getValueAt(index, 4).toString());
        }
        totalPurchaseCostDisplay.setText(" Total: " + totalPurchaseCost);
    }

    //update total sales amount display
    public void updateTotalSalesAmountDisplayAfterSaleRemoval() {
        totalPurchaseCost -= Double.parseDouble(salesListTable.getValueAt(salesListTable.getSelectedRow(), 4).toString());
        totalPurchaseCostDisplay.setText(" Total: " + totalPurchaseCost);
    }

    //get row position of selected product to alter qty
    public void updateQtyOnProductTable(int productID, int updatedQty) {
        for (int index = 0; index < productDefaultTableModel.getRowCount(); index++) {
            if (productID == Integer.parseInt(productTable.getValueAt(index, 0).toString())) {
                productTable.setValueAt(updatedQty, index, 4);
            }
        }
    }

    //get row position of selected product to alter qty
    public void updateQtyAfterSaleRemoval(int productID, int salesQty) {
        for (int index = 0; index < productDefaultTableModel.getRowCount(); index++) {
            if (productID == Integer.parseInt(productTable.getValueAt(index, 0).toString())) {
                int currentQty = Integer.parseInt(productTable.getValueAt(index, 4).toString());
                productTable.setValueAt(currentQty + salesQty, index, 4);
            }
        }
    }

    //refresh tables
    public void refreshTables() {
        productDefaultTableModel.setRowCount(0);
        products = dbConnection.getAllProducts();
        populateProductTable(products);

        salesListDefaultTableModel.setRowCount(0);
        totalPurchaseCostDisplay.setText(" Total: " + totalPurchaseCost);
    }

    //get all productIDs and QTYs from sales table
    public Map<Integer, Integer> getProductIDAndQty() {
        Map<Integer, Integer> productDetails = new HashMap<>();

        for (int index = 0; index < salesListDefaultTableModel.getRowCount(); index++) {
            int salesTableProdID = Integer.parseInt(salesListTable.getValueAt(index, 0).toString());
            int salesQty = Integer.parseInt(salesListTable.getValueAt(index, 3).toString());

            productDetails.put(salesTableProdID, salesQty);
        }
        return productDetails;
    }

    //get the entire sales details
    public Sales getSalesDetails() {

//        int salesId = Integer.parseInt(orderIdField.getText());
        int custId = Integer.parseInt(customerIdField.getText());
        int staffId = staff.getId();
        Date saleDate = (Date.valueOf(FormatDate.format(orderDateField.getDate())));
        double totalSaleAmount = totalPurchaseCost;
        String soldFrom = soldFromField.getText();
        String salesMode = salesModeField.getText();
        String deliveryStatus = "";

        String paymentMode = ((JRadioButton) radioButtonActionEvent.getSource()).getActionCommand();
        int paymentModeID = 1;
        switch (paymentMode) {
            case "Cash Payment":
                paymentModeID = 1;
                break;
            case "POS":
                paymentModeID = 2;
                break;
            case "Cash Transfer":
                paymentModeID = 3;
                break;
            case "Credit":
                paymentModeID = 4;
                break;
            default:
                break;
        }

        Sales sales = new Sales(custId, staffId, saleDate, totalSaleAmount, soldFrom, salesMode, paymentModeID, deliveryStatus);

        return sales;
    }

    //get the sales breakdown details
    public ArrayList<SalesBreakdown> getSalesBreakdownDetails() {
        ArrayList<SalesBreakdown> salesBreakdowns = new ArrayList<>();

        for (int index = 0; index < salesListDefaultTableModel.getRowCount(); index++) {

            int salesID = Integer.parseInt(saleesIdField.getText());
            int productID = Integer.parseInt(salesListTable.getValueAt(index, 0).toString());
            String productName = salesListTable.getValueAt(index, 1).toString();
            double salesPrice = Double.parseDouble(salesListTable.getValueAt(index, 2).toString());
            int quantity = Integer.parseInt(salesListTable.getValueAt(index, 3).toString());
            double quantityXprice = Double.parseDouble(salesListTable.getValueAt(index, 4).toString());

            salesBreakdowns.add(new SalesBreakdown(salesID, productName, productID, salesPrice, quantity, quantityXprice));
        }

        return salesBreakdowns;
    }

    //update quantity in database after sales
    public void updateQtyOnDbAfterSales() {

        Map<Integer, Integer> productDetails = getProductIDAndQty();
        Set<Integer> pIds = productDetails.keySet();

        for (int salesTableProdID : pIds) {
            dbConnection.updateProductQty(salesTableProdID, productDetails.get(salesTableProdID));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        customerDetailsTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        salesListTable = new javax.swing.JTable();
        totalPurchaseCostDisplay = new javax.swing.JLabel();
        selectProductButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        customerIdField = new javax.swing.JTextField();
        saleesIdField = new javax.swing.JTextField();
        payButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        insertNewCategoryButton = new javax.swing.JButton();
        orderDateField = new com.toedter.calendar.JDateChooser();
        searchByComboBox = new javax.swing.JComboBox<>();
        productSearchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        removeProductButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        salesModeField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        paymentModePanel = new javax.swing.JPanel();
        cashPayment = new javax.swing.JRadioButton();
        posPayment = new javax.swing.JRadioButton();
        cashTransfer = new javax.swing.JRadioButton();
        credit = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        soldFromField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 51, 51));
        setPreferredSize(new java.awt.Dimension(1500, 1000));

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        customerDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Tel", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerDetailsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(customerDetailsTable);

        productTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barcode", "Name", "Cost Price", "Quantity", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(productTable);

        salesListTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        salesListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Sales Price", "Quantity", "Qty X Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(salesListTable);

        totalPurchaseCostDisplay.setBackground(new java.awt.Color(255, 255, 255));
        totalPurchaseCostDisplay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalPurchaseCostDisplay.setText(" Total: 00.00");
        totalPurchaseCostDisplay.setOpaque(true);

        selectProductButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        selectProductButton.setText(">>>");
        selectProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectProductButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Customer ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Sales ID:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Sales Date:");

        customerIdField.setEditable(false);
        customerIdField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        saleesIdField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saleesIdField.setText("6");
        saleesIdField.setEnabled(false);

        payButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        payButton.setText("Pay");
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Choose Customer From List");

        insertNewCategoryButton.setBackground(new java.awt.Color(255, 117, 52));
        insertNewCategoryButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        insertNewCategoryButton.setForeground(new java.awt.Color(255, 255, 255));
        insertNewCategoryButton.setText("New Customer?");
        insertNewCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertNewCategoryButtonActionPerformed(evt);
            }
        });

        searchByComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Barcode", "Product Name" }));
        searchByComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByComboBoxActionPerformed(evt);
            }
        });

        productSearchField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        productSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productSearchFieldKeyTyped(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(51, 51, 0));
        searchButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search-32.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        removeProductButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        removeProductButton1.setText("Remove Product");
        removeProductButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeProductButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Sales Mode:");

        salesModeField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        salesModeField.setText("Offline");
        salesModeField.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Search By:");

        refreshButton.setBackground(new java.awt.Color(0, 102, 102));
        refreshButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/up.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        paymentModePanel.setBackground(new java.awt.Color(0, 51, 51));
        paymentModePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Select Payment Mode", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        cashPayment.setBackground(new java.awt.Color(0, 51, 51));
        cashPayment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cashPayment.setForeground(new java.awt.Color(255, 255, 255));
        cashPayment.setText("Cash Payment");
        cashPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashPaymentActionPerformed(evt);
            }
        });

        posPayment.setBackground(new java.awt.Color(0, 51, 51));
        posPayment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        posPayment.setForeground(new java.awt.Color(255, 255, 255));
        posPayment.setText("POS");
        posPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posPaymentActionPerformed(evt);
            }
        });

        cashTransfer.setBackground(new java.awt.Color(0, 51, 51));
        cashTransfer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cashTransfer.setForeground(new java.awt.Color(255, 255, 255));
        cashTransfer.setText("Cash Transfer");
        cashTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashTransferActionPerformed(evt);
            }
        });

        credit.setBackground(new java.awt.Color(0, 51, 51));
        credit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        credit.setForeground(new java.awt.Color(255, 255, 255));
        credit.setText("Credit");
        credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentModePanelLayout = new javax.swing.GroupLayout(paymentModePanel);
        paymentModePanel.setLayout(paymentModePanelLayout);
        paymentModePanelLayout.setHorizontalGroup(
            paymentModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentModePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paymentModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashPayment)
                    .addComponent(posPayment)
                    .addComponent(cashTransfer)
                    .addComponent(credit))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        paymentModePanelLayout.setVerticalGroup(
            paymentModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentModePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cashPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(posPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cashTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(credit, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Sold From:");

        soldFromField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        soldFromField.setText("Store");
        soldFromField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(selectProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalPurchaseCostDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(productSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(searchButton)
                                .addGap(6, 6, 6)
                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(insertNewCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paymentModePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(removeProductButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(customerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(saleesIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(salesModeField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(soldFromField, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(insertNewCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(6, 6, 6)
                                .addComponent(searchByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(productSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(selectProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(saleesIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(salesModeField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(orderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(soldFromField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(paymentModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeProductButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPurchaseCostDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(345, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectProductButtonActionPerformed
        //check to see that a product was selected on product table
        if (productTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "No product selected.", "message", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            productTableProdID = Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), 0).toString());
            name = productTable.getValueAt(productTable.getSelectedRow(), 2).toString();
            availableQty = Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), 4).toString());

            //pass values across to QtyAndPriceDiaolg
            QtyAndPriceDiaolg.prodID = productTableProdID;
            QtyAndPriceDiaolg.name = name;
            QtyAndPriceDiaolg.availableQty = availableQty;
            new QtyAndPriceDiaolg(null, true).setVisible(true);

            totalPurchaseCost = 00.00;
            updateTotalSalesAmountDisplay();    //update total purchase amount

//            int updatedQty = dbConnection.getSelectedProductQty(productTableProdID);     //get updated product's qty from database
            int updatedQty = availableQty - salesQty;     //get updated product's qty from database
            updateQtyOnProductTable(productTableProdID, updatedQty);      //update product's qty on product table after selecting qty for sales

        }

    }//GEN-LAST:event_selectProductButtonActionPerformed

    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed
        int generateReceipt = 0;

        if (salesListDefaultTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No Sales Yet.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (customerIdField.getText().equals("") || orderDateField.getDate() == null || saleesIdField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Some Fields Are Empty.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (paymentModeButtonGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Select Payment Mode.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        }

//      JOptionPane.showMessageDialog(this, "New Order Added.", "Message",JOptionPane.INFORMATION_MESSAGE);
        generateReceipt = JOptionPane.showConfirmDialog(this, "Payment Successful?\n\nClick YES to generate receipt.\nClick NO to decline.", "Sales successful", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (generateReceipt == JOptionPane.YES_OPTION) {

            Sales sale = getSalesDetails();
            ArrayList<SalesBreakdown> salesBreakdowns = getSalesBreakdownDetails();

            dbConnection.addNewSale(sale);
            for (SalesBreakdown salesBreakdown : salesBreakdowns) {
                dbConnection.addNewSaleBreakdown(salesBreakdown);
            }

            updateQtyOnDbAfterSales();
            salesListDefaultTableModel.setRowCount(0);
            totalPurchaseCost = 00.00;
            totalPurchaseCostDisplay.setText(" Total: " + totalPurchaseCost);
            

            CustomerPurchaseReceipt.customerName = customerDetailsTable.getValueAt(customerDetailsTable.getSelectedRow(), 1).toString() + " " + customerDetailsTable.getValueAt(customerDetailsTable.getSelectedRow(), 2).toString();
            CustomerPurchaseReceipt.soldBy = staff.getFirstName() + " " + staff.getLastName();
            CustomerPurchaseReceipt.sale = sale;
            CustomerPurchaseReceipt.salesBreakdown = salesBreakdowns;
            new CustomerPurchaseReceipt(null, true).setVisible(true);
        }

    }//GEN-LAST:event_payButtonActionPerformed

    private void insertNewCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertNewCategoryButtonActionPerformed
        new AddNewCustomer(null, true).setVisible(true);
    }//GEN-LAST:event_insertNewCategoryButtonActionPerformed

    private void searchByComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchByComboBoxActionPerformed

    private void productSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productSearchFieldKeyTyped
        if (searchByComboBox.getSelectedIndex() == 0) {
            if (evt.getKeyChar() == 10) {
                String barcode = productSearchField.getText();

                products = dbConnection.getProductUsingBarcode(barcode);
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No product with such BARCODE.", "message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    productDefaultTableModel.setRowCount(0);
                    populateProductTable(products);
                    productSearchField.setText("");
                }

            }
        }
    }//GEN-LAST:event_productSearchFieldKeyTyped

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (productSearchField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Search cannot be empty!", "message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (searchByComboBox.getSelectedIndex() == 0) {
                String barcode = productSearchField.getText().trim();

                products = dbConnection.getProductUsingBarcode(barcode);
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No product with such BARCODE.", "message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    productDefaultTableModel.setRowCount(0);
                    populateProductTable(products);
                }

            } else if (searchByComboBox.getSelectedIndex() == 1) {
                String productName = productSearchField.getText().trim();

                products = dbConnection.getProductUsingProductName(productName);
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No product with such NAME.", "message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    productDefaultTableModel.setRowCount(0);
                    populateProductTable(products);
                }
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshTables();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked

        if (evt.getClickCount() == 2) {
            int productID = Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), 0).toString());
            String barcode = productTable.getValueAt(productTable.getSelectedRow(), 1).toString();
            String name = productTable.getValueAt(productTable.getSelectedRow(), 2).toString();
            double costPrice = Double.parseDouble(productTable.getValueAt(productTable.getSelectedRow(), 3).toString());
            int quantity = Integer.parseInt(productTable.getValueAt(productTable.getSelectedRow(), 4).toString());
            String description = productTable.getValueAt(productTable.getSelectedRow(), 5).toString();
            String image = dbConnection.getProductImagePath(productID);

            Products products = new Products(productID, barcode, name, costPrice, quantity, description, image);
            DisplaySelectedAtPos.products = products;

            new DisplaySelectedAtPos(null, true).setVisible(true);
        }

    }//GEN-LAST:event_productTableMouseClicked

    private void customerDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerDetailsTableMouseClicked
        //get the selected category details
        int customerID = Integer.parseInt(customerDetailsTable.getValueAt(customerDetailsTable.getSelectedRow(), 0).toString());

        //display customer id on display fields
        customerIdField.setText(customerID + "");
    }//GEN-LAST:event_customerDetailsTableMouseClicked

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

        Map<Integer, Integer> productDetails = getProductIDAndQty();
        Set<Integer> pIds = productDetails.keySet();

        for (int salesTableProdID : pIds) {
            updateQtyAfterSaleRemoval(salesTableProdID, productDetails.get(salesTableProdID));
        }

        salesListDefaultTableModel.setRowCount(0);
        totalPurchaseCost = 00.00;
        totalPurchaseCostDisplay.setText(" Total: " + totalPurchaseCost);
    }//GEN-LAST:event_clearButtonActionPerformed

    private void removeProductButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeProductButton1ActionPerformed

        if (salesListTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Sales Table is EMPTY.", "message", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (salesListTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "No sale selected.", "message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int deleteOption = JOptionPane.showConfirmDialog(this, "Do You Really Want To Remove This Sale?", "Remove Sale", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (deleteOption == JOptionPane.YES_OPTION) {
                int salesTableProdID = Integer.parseInt(salesListTable.getValueAt(salesListTable.getSelectedRow(), 0).toString());
                int salesQty = Integer.parseInt(salesListTable.getValueAt(salesListTable.getSelectedRow(), 3).toString());

                updateQtyAfterSaleRemoval(salesTableProdID, salesQty); //update product quantity
                updateTotalSalesAmountDisplayAfterSaleRemoval();    //update sales amount
                salesListDefaultTableModel.removeRow(salesListTable.getSelectedRow());

                JOptionPane.showMessageDialog(this, "Sale Successfully Removed.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_removeProductButton1ActionPerformed

    private void cashPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashPaymentActionPerformed
        radioButtonActionEvent = evt;
    }//GEN-LAST:event_cashPaymentActionPerformed

    private void posPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posPaymentActionPerformed
        radioButtonActionEvent = evt;
    }//GEN-LAST:event_posPaymentActionPerformed

    private void cashTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashTransferActionPerformed
        radioButtonActionEvent = evt;
    }//GEN-LAST:event_cashTransferActionPerformed

    private void creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditActionPerformed
        radioButtonActionEvent = evt;
    }//GEN-LAST:event_creditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton cashPayment;
    private javax.swing.JRadioButton cashTransfer;
    private javax.swing.JButton clearButton;
    private javax.swing.JRadioButton credit;
    private javax.swing.JTable customerDetailsTable;
    private javax.swing.JTextField customerIdField;
    private javax.swing.JButton insertNewCategoryButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser orderDateField;
    private javax.swing.JButton payButton;
    private javax.swing.JPanel paymentModePanel;
    private javax.swing.JRadioButton posPayment;
    private javax.swing.JTextField productSearchField;
    private javax.swing.JTable productTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeProductButton1;
    private javax.swing.JTextField saleesIdField;
    private javax.swing.JTable salesListTable;
    private javax.swing.JTextField salesModeField;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchByComboBox;
    private javax.swing.JButton selectProductButton;
    private javax.swing.JTextField soldFromField;
    private javax.swing.JLabel totalPurchaseCostDisplay;
    // End of variables declaration//GEN-END:variables
}
