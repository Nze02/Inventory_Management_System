/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Products;
import Model.DBConnection;
import java.awt.Color;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class StaffProductsScreen extends javax.swing.JPanel {

    private DBConnection dbConnection = null;
    private DefaultTableModel dtm;
    private ArrayList<Products> products;

    /**
     * Creates new form ProductsScreen
     */
    public StaffProductsScreen() {
        initComponents();
        dbConnection = new DBConnection();
        dtm = (DefaultTableModel) staffProductTable.getModel();

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

        updateProductTable();
    }

    //update details 
    public void updateProductTable() {
        dtm.setRowCount(0);
        products = dbConnection.getAllProducts();
        populateProductTable(products);
    }

    //method to populate the product table
    public void populateProductTable(ArrayList<Products> products) {
        for (int index = 0; index < products.size(); index++) {

            int productID = products.get(index).getProductID();
            String barcode = products.get(index).getBarcode();
            String category = products.get(index).getCategory();
            String name = products.get(index).getName();
            double costPrice = products.get(index).getCostPrice();
            int quantity = products.get(index).getQuantity();
            Date purchaseDate = products.get(index).getDate();
            String description = products.get(index).getDescription();
            String purchasedFrom = products.get(index).getPurchasedFrom();
            String image = dbConnection.getProductImagePath(productID);

            insertIntoAdminProductTable(productID + "", barcode, category, name, costPrice + "", quantity + "", purchaseDate.toString(), description, purchasedFrom);
        }
    }

//    public void searchForProductOnKeyTyped() {
//        String typedBarcode = productSearchField.getText();
//        ArrayList<Integer> prodId = new ArrayList<>();
//
//        for (int index = 0; index < dtm.getRowCount(); index++) {
//            String barcode = staffProductTable.getValueAt(index, 1).toString();
//            if (barcode.contains(typedBarcode)) {
//                prodId.add(Integer.parseInt(staffProductTable.getValueAt(index, 0).toString()));
//            }
//        }
//
//        products = new ArrayList<>();
//        for (int index = 0; index < prodId.size(); index++) {
//            products.add(dbConnection.getProductUsingID(prodId.get(index)));
//        }
//        dtm.setRowCount(0);
//        populateProductTable(products);
//    }

    //populate product table
    public void insertIntoAdminProductTable(String productID, String barcode, String category, String name, String costPrice, String quantity, String purchaseDate, String description, String purchasedFrom) {
        String[] rowData = {productID, barcode, category, name, costPrice, quantity, purchaseDate, description, purchasedFrom};
        dtm.addRow(rowData);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        productSearchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        staffProductTable = new javax.swing.JTable();
        productSortBy = new javax.swing.JComboBox<>();
        searchByComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 29));
        setPreferredSize(new java.awt.Dimension(1300, 615));

        productSearchField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        productSearchField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
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

        refreshButton.setBackground(new java.awt.Color(0, 102, 102));
        refreshButton.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/up.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        staffProductTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        staffProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Barcode", "Category", "Name", "Cost Price", "Quantity", "Purchase Date", "Description", "Purchased From"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffProductTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(staffProductTable);
        if (staffProductTable.getColumnModel().getColumnCount() > 0) {
            staffProductTable.getColumnModel().getColumn(7).setPreferredWidth(40);
        }

        productSortBy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        productSortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product ID", "Product Name" }));
        productSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productSortByActionPerformed(evt);
            }
        });

        searchByComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product Barcode", "Product Name" }));
        searchByComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Search By:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sort By:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(618, 618, 618)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(465, 465, 465)
                .addComponent(searchByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(productSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(390, 390, 390)
                .addComponent(productSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1212, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(searchByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(productSortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(productSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchByComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByComboBoxActionPerformed
        if (searchByComboBox.getSelectedIndex() == 0) {
            productSearchField.setText("");
        }
    }//GEN-LAST:event_searchByComboBoxActionPerformed

    private void staffProductTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffProductTableMouseClicked

        if (evt.getClickCount() == 2) {
            int productID = Integer.parseInt(staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 0).toString());
            String barcode = staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 1).toString();
            String category = staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 2).toString();
            String name = staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 3).toString();
            double costPrice = Double.parseDouble(staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 4).toString());
            int quantity = Integer.parseInt(staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 5).toString());
            Date purchaseDate = Date.valueOf(staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 6).toString());
            String description = staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 7).toString();
            String purchasedFrom = staffProductTable.getValueAt(staffProductTable.getSelectedRow(), 8).toString();
            String image = dbConnection.getProductImagePath(productID);

            Products products = new Products(productID, barcode, category, name, costPrice, quantity, purchaseDate, description, purchasedFrom, image);
            DisplaySelectedProduct.products = products;

            new DisplaySelectedProduct(null, true).setVisible(true);
        }
    }//GEN-LAST:event_staffProductTableMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        dtm.setRowCount(0);
        products = dbConnection.getAllProducts();
        populateProductTable(products);
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void productSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productSearchFieldKeyTyped
        if (searchByComboBox.getSelectedIndex() == 0) {
            if (evt.getKeyChar() == 10) {
                String barcode = productSearchField.getText();

                products = dbConnection.getProductUsingBarcode(barcode);
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No product with such BARCODE.", "message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    dtm.setRowCount(0);
                    populateProductTable(products);
                    productSearchField.setText("");
                }
                return;
            }

//            searchForProductOnKeyTyped();

        }
//        else if(searchByComboBox.getSelectedIndex() == 1){
//            
//        }
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
                    dtm.setRowCount(0);
                    populateProductTable(products);
                }

            } else if (searchByComboBox.getSelectedIndex() == 1) {
                String productName = productSearchField.getText().trim();

                products = dbConnection.getProductUsingProductName(productName);
                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No product with such NAME.", "message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    dtm.setRowCount(0);
                    populateProductTable(products);
                }
            }
        }

    }//GEN-LAST:event_searchButtonActionPerformed

    private void productSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productSortByActionPerformed
        if (productSortBy.getSelectedIndex() == 0) {
            products = dbConnection.getAllProducts();
            dtm.setRowCount(0);
            populateProductTable(products);
        } else if (productSortBy.getSelectedIndex() == 1) {
            products = dbConnection.getAllProductsByName();
            dtm.setRowCount(0);
            populateProductTable(products);
        }
    }//GEN-LAST:event_productSortByActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField productSearchField;
    private javax.swing.JComboBox<String> productSortBy;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchByComboBox;
    private javax.swing.JTable staffProductTable;
    // End of variables declaration//GEN-END:variables
}
