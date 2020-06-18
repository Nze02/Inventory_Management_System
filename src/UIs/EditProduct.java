/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Category;
import Beans.FormatDate;
import Beans.Products;
import Model.DBConnection;
import Utils.LoadImages;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class EditProduct extends javax.swing.JDialog {

    private DBConnection dbConnection = null;
    protected static Products product;
    private ArrayList<String> categoryList;

    private ImageIcon currentIcon;

    /**
     * Creates new form EditProduct
     */
    public EditProduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dbConnection = new DBConnection();
        categoryList = new ArrayList<>();    //arraylist to hold category list

        populateCategoryComboBox();

        displayProductDetails();
        currentIcon = (ImageIcon) imageLabel.getIcon();
        this.setLocation(500, 20);

    }

    //populate category combo box
    public void populateCategoryComboBox() {
        ArrayList<Category> categories = dbConnection.getProductCategories();
        categoryList.add("Choose Category");

        for (int index = 0; index < categories.size(); index++) {
            categoryList.add(categories.get(index).getCategoryName());
        }
        categoryComboBox.setModel(new DefaultComboBoxModel(categoryList.toArray()));
    }

    //get index of product category
    public int getCategoryIndex(String category) {
        int categoryIndex = 0;

        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).equals(category)) {
                categoryIndex = i;
            }
        }
        return categoryIndex;
    }

    //display product details
    public void displayProductDetails() {
        idField.setText(product.getProductID() + "");
        barcodeField.setText(product.getBarcode());
        categoryComboBox.setSelectedIndex(getCategoryIndex(product.getCategory()));
        productNameField.setText(product.getName());
        costPriceField.setText(product.getCostPrice() + "");
        quantityField.setText(product.getQuantity() + "");
        descriptionField.setText(product.getDescription());
        purchasedFromField.setText(product.getPurchasedFrom());
        purchasedDateField.setDate(product.getDate());
        LoadImages.downloadImage(imageLabel, this, product.getImage());
//         descriptionField.setText(product.getImage());
    }

    //a method to check for empty fields
    public Boolean isFieldEmpty() {
        boolean isEmpty = false;

        if (barcodeField.getText().isEmpty() || categoryComboBox.getSelectedIndex() == 0 || productNameField.getText().isEmpty()
                || costPriceField.getText().isEmpty() || quantityField.getText().isEmpty() || descriptionField.getText().isEmpty()
                || purchasedFromField.getText().isEmpty() || purchasedDateField.getDate() == null) {
            isEmpty = true;
        }

        return isEmpty;
    }

    //update product
    public void updateEditedProduct() {
        String barcode = barcodeField.getText();
        String category = categoryComboBox.getSelectedIndex() + "";
        String productName = productNameField.getText();
        double costPrice = Double.parseDouble(costPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        Date purchasedDate = (Date.valueOf(FormatDate.format(purchasedDateField.getDate())));
        String description = descriptionField.getText();
        String purchasedFrom = purchasedFromField.getText();
//        String image = "";

        Products updatedProducts = new Products(product.getProductID(), barcode, category, productName, costPrice, quantity, purchasedDate, description, purchasedFrom);
        int updateStatus = dbConnection.updateProduct(updatedProducts);

        //set table contents with updated values
        if (updateStatus > 0) {
            AdminProductsScreen.dtm.setRowCount(0);
            ArrayList<Products> products = dbConnection.getAllProducts();
            AdminProductsScreen.populateProductTable(products);
            JOptionPane.showMessageDialog(this, "Product Successfully Updated.", "Message", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    //update product
    public void updateEditedProductWithNewImage(String imagePath) {
        String barcode = barcodeField.getText();
        String category = categoryComboBox.getSelectedIndex() + "";
        String productName = productNameField.getText();
        double costPrice = Double.parseDouble(costPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        Date purchasedDate = (Date.valueOf(FormatDate.format(purchasedDateField.getDate())));
        String description = descriptionField.getText();
        String purchasedFrom = purchasedFromField.getText();
        String image = imagePath;

        Products updatedProducts = new Products(product.getProductID(), barcode, category, productName, costPrice, quantity, purchasedDate, description, purchasedFrom, image);
        int updateStatus = dbConnection.updateProductWithNewImage(updatedProducts);

        //set table contents with updated values
        if (updateStatus > 0) {
            AdminProductsScreen.dtm.setRowCount(0);
            ArrayList<Products> products = dbConnection.getAllProducts();
            AdminProductsScreen.populateProductTable(products);
            JOptionPane.showMessageDialog(this, "Product Successfully Updated.", "Message", JOptionPane.INFORMATION_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        categoryComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        productNameField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        costPriceField = new javax.swing.JTextField();
        descriptionField = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        cancelEditProductButton = new javax.swing.JButton();
        updateProductButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        purchasedFromField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        barcodeField = new javax.swing.JTextField();
        purchasedDateField = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit A Product");

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Name:");

        categoryComboBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Category:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Description:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Price:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Picture:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Quantity:");

        productNameField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        productNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameFieldActionPerformed(evt);
            }
        });

        quantityField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        costPriceField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        descriptionField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        imageLabel.setOpaque(true);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Select a Picture");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ID:");

        idField.setEditable(false);
        idField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        idField.setEnabled(false);

        cancelEditProductButton.setBackground(new java.awt.Color(255, 51, 0));
        cancelEditProductButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cancelEditProductButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelEditProductButton.setText("Cancel");
        cancelEditProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEditProductButtonActionPerformed(evt);
            }
        });

        updateProductButton.setBackground(new java.awt.Color(0, 102, 255));
        updateProductButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateProductButton.setForeground(new java.awt.Color(255, 255, 255));
        updateProductButton.setText("Update");
        updateProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProductButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Purchased From:");

        purchasedFromField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Purchased Date:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Barcode:");

        barcodeField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        barcodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcodeFieldActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("dd-mm-yyyy (e.g: 02-Mar-2020)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(categoryComboBox, 0, 246, Short.MAX_VALUE)
                                    .addComponent(productNameField)
                                    .addComponent(costPriceField)
                                    .addComponent(quantityField)
                                    .addComponent(descriptionField)
                                    .addComponent(purchasedFromField)
                                    .addComponent(barcodeField)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(cancelEditProductButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(updateProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(purchasedDateField, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barcodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchasedFromField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(purchasedDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelEditProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelEditProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEditProductButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelEditProductButtonActionPerformed

    private void updateProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProductButtonActionPerformed
        if (isFieldEmpty().equals(true)) {
            JOptionPane.showMessageDialog(this, "One Or More Fields Are Empty.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (imageLabel.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Select Product Image.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dispose();
        if (!((ImageIcon) imageLabel.getIcon() == currentIcon)) {

            String imagePath = LoadImages.uploadImage();
            if (imagePath.equals("")) {
                JOptionPane.showMessageDialog(this, "Error while uploading Product Image from update.", "Message", JOptionPane.WARNING_MESSAGE);
                return;
            } 
            updateEditedProductWithNewImage(imagePath);
            return;
        }

        updateEditedProduct();

    }//GEN-LAST:event_updateProductButtonActionPerformed

    private void productNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameFieldActionPerformed

    private void barcodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcodeFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoadImages.fetchImage(imageLabel, this);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditProduct dialog = new EditProduct(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcodeField;
    private javax.swing.JButton cancelEditProductButton;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JTextField costPriceField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField productNameField;
    private com.toedter.calendar.JDateChooser purchasedDateField;
    private javax.swing.JTextField purchasedFromField;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton updateProductButton;
    // End of variables declaration//GEN-END:variables
}
