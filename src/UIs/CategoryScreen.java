/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Category;
import Beans.Products;
import Model.DBConnection;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class CategoryScreen extends javax.swing.JPanel {

    private DBConnection dbConnection = null;
    protected static DefaultTableModel dtm;
    private DefaultListModel listModel;
    private ArrayList<Category> category;
    private ArrayList<Products> products;

    /**
     * Creates new form ProductsScreen
     */
    public CategoryScreen() {
        initComponents();
        dbConnection = new DBConnection();
        dtm = (DefaultTableModel) categoryDisplayTable.getModel();
        
        updateProductTable();
    }
    
    public void updateProductTable(){
        dtm.setRowCount(0);
        category = dbConnection.getProductCategories();
        populateCategoryTable(category);
    }

    //method to populate the category sales table
    protected static void populateCategoryTable(ArrayList<Category> category) {
        for (int index = 0; index < category.size(); index++) {

            int categoryID = category.get(index).getCategoryID();
            String categoryName = category.get(index).getCategoryName();

            insertIntoCategoryTable(categoryID + "", categoryName);
        }
    }

    //populate category table
    protected static void insertIntoCategoryTable(String categoryID, String categoryName) {
        String[] rowData = {categoryID, categoryName};
        dtm.addRow(rowData);
    }
    
    //get details of selected category from table
    public Category getSelectedCategoryDetail(){
            int categoryID = Integer.parseInt(categoryDisplayTable.getValueAt(categoryDisplayTable.getSelectedRow(), 0).toString());
            String categoryName = categoryDisplayTable.getValueAt(categoryDisplayTable.getSelectedRow(), 1).toString();
            
            Category category = new Category(categoryID, categoryName);
            
            return  category;
    }
    
    //clear fields
    public void clearFields(){
        categoryIdDisplay.setText("");
        categoryNameDisplay.setText("");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        categoryIdDisplay = new javax.swing.JTextField();
        categoryNameDisplay = new javax.swing.JTextField();
        deleteCategoryButton = new javax.swing.JButton();
        updateCategoryButton = new javax.swing.JButton();
        insertNewCategoryButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryDisplayTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        productsInThisCategoryList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        showFullProductButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 51));
        setPreferredSize(new java.awt.Dimension(1080, 615));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");
        add(jLabel1);
        jLabel1.setBounds(10, 59, 116, 38);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name:");
        add(jLabel2);
        jLabel2.setBounds(10, 115, 116, 38);

        categoryIdDisplay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        categoryIdDisplay.setEnabled(false);
        add(categoryIdDisplay);
        categoryIdDisplay.setBounds(144, 59, 171, 38);

        categoryNameDisplay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        categoryNameDisplay.setEnabled(false);
        add(categoryNameDisplay);
        categoryNameDisplay.setBounds(144, 115, 171, 38);

        deleteCategoryButton.setBackground(new java.awt.Color(153, 0, 0));
        deleteCategoryButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteCategoryButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteCategoryButton.setText("Delete Selected Category");
        deleteCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCategoryButtonActionPerformed(evt);
            }
        });
        add(deleteCategoryButton);
        deleteCategoryButton.setBounds(10, 506, 305, 43);

        updateCategoryButton.setBackground(new java.awt.Color(0, 102, 102));
        updateCategoryButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateCategoryButton.setForeground(new java.awt.Color(255, 255, 255));
        updateCategoryButton.setText("Update Selected Category");
        updateCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCategoryButtonActionPerformed(evt);
            }
        });
        add(updateCategoryButton);
        updateCategoryButton.setBounds(10, 457, 305, 43);

        insertNewCategoryButton.setBackground(new java.awt.Color(0, 51, 0));
        insertNewCategoryButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        insertNewCategoryButton.setForeground(new java.awt.Color(255, 255, 255));
        insertNewCategoryButton.setText("Insert New Category");
        insertNewCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertNewCategoryButtonActionPerformed(evt);
            }
        });
        add(insertNewCategoryButton);
        insertNewCategoryButton.setBounds(10, 408, 305, 43);

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        categoryDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        categoryDisplayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryDisplayTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(categoryDisplayTable);

        add(jScrollPane1);
        jScrollPane1.setBounds(333, 59, 452, 436);

        productsInThisCategoryList.setBorder(new javax.swing.border.MatteBorder(null));
        productsInThisCategoryList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(productsInThisCategoryList);

        add(jScrollPane2);
        jScrollPane2.setBounds(803, 61, 247, 434);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("< Products in the selected category >");
        add(jLabel3);
        jLabel3.setBounds(803, 23, 244, 32);

        showFullProductButton.setBackground(new java.awt.Color(51, 0, 51));
        showFullProductButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        showFullProductButton.setForeground(new java.awt.Color(255, 255, 255));
        showFullProductButton.setText("Show full products list in this category");
        showFullProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFullProductButtonActionPerformed(evt);
            }
        });
        add(showFullProductButton);
        showFullProductButton.setBounds(333, 506, 452, 43);
    }// </editor-fold>//GEN-END:initComponents

    private void updateCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCategoryButtonActionPerformed
        if(categoryDisplayTable.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "No category selected.", "message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            UpdateCategory.category = getSelectedCategoryDetail();
            new UpdateCategory(null, true).setVisible(true);
        }
    }//GEN-LAST:event_updateCategoryButtonActionPerformed

    private void showFullProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFullProductButtonActionPerformed
        if(categoryDisplayTable.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "No category selected.", "message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            if(productsInThisCategoryList.getModel().getSize() == 0){
                JOptionPane.showMessageDialog(this, "No Product in selected category.", "message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            ProductsInThisCategory.products = products;
            new ProductsInThisCategory(null, true).setVisible(true);
        }
        
    }//GEN-LAST:event_showFullProductButtonActionPerformed

    private void deleteCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCategoryButtonActionPerformed
        if(categoryDisplayTable.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "No category selected.", "message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            int deleteOption = JOptionPane.showConfirmDialog(this, "Do You Really Want To Delete This Category?", "Delete Product", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(deleteOption == JOptionPane.YES_OPTION){
                int categoryID = Integer.parseInt(categoryDisplayTable.getValueAt(categoryDisplayTable.getSelectedRow(), 0).toString());
                dbConnection.deleteCategory(categoryID);
                
                dtm.setRowCount(0);
                category = dbConnection.getProductCategories();
                populateCategoryTable(category);
                clearFields();
                JOptionPane.showMessageDialog(this, "Category Successfully Deleted.", "Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_deleteCategoryButtonActionPerformed

    private void insertNewCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertNewCategoryButtonActionPerformed
        new AddNewCategory(null, true).setVisible(true);
    }//GEN-LAST:event_insertNewCategoryButtonActionPerformed

    private void categoryDisplayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryDisplayTableMouseClicked
        
            //get the selected category details
            int categoryID = Integer.parseInt(categoryDisplayTable.getValueAt(categoryDisplayTable.getSelectedRow(), 0).toString());
            String categoryName = categoryDisplayTable.getValueAt(categoryDisplayTable.getSelectedRow(), 1).toString();

            Category category = new Category(categoryID, categoryName);
            products = dbConnection.getProductUsingCategory(categoryID);    //fetch products under this category
            
            //display category id and name on display fields
            categoryIdDisplay.setText(categoryID +"");
            categoryNameDisplay.setText(categoryName);

            //add names of products on this category to list model
            listModel = new DefaultListModel();
            for(int index = 0; index < products.size(); index++){
                listModel.addElement(products.get(index).getName());
            }
            productsInThisCategoryList.setModel(listModel);
            ProductsInThisCategory.category = categoryName;     //set category title in products in this category
    }//GEN-LAST:event_categoryDisplayTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable categoryDisplayTable;
    private javax.swing.JTextField categoryIdDisplay;
    private javax.swing.JTextField categoryNameDisplay;
    private javax.swing.JButton deleteCategoryButton;
    private javax.swing.JButton insertNewCategoryButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> productsInThisCategoryList;
    private javax.swing.JButton showFullProductButton;
    private javax.swing.JButton updateCategoryButton;
    // End of variables declaration//GEN-END:variables
}