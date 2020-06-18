/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Sales;
import Beans.SalesBreakdown;
import Model.DBConnection;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class ViewAllSales extends javax.swing.JPanel {
    private DBConnection dbConnection = null;
    
    private DefaultTableModel dtm;
    
    /**
     * Creates new form AllOrders
     */
    public ViewAllSales() {
        initComponents();
        
        dbConnection = new DBConnection();
        dtm = (DefaultTableModel) salesDisplayTable.getModel();
        
        populateStaffSalesTable();
    }
    
    //method to populate the staff sales table
    public void populateStaffSalesTable(){
        ArrayList<Sales> allSales = dbConnection.getAllSales();
        for(int index = 0; index < allSales.size(); index++){
            int salesID = allSales.get(index).getSalesID();
            Date salesDate = allSales.get(index).getSalesDate();
            String staff = allSales.get(index).getStaffFName()+" "+ allSales.get(index).getStaffLName();
            String customer = allSales.get(index).getCustomerFName() +" "+ allSales.get(index).getCustomerLName();
            double amount = allSales.get(index).getAmount();
            String soldFrom = allSales.get(index).getSoldFrom();
            String salesMode = allSales.get(index).getSalesMode();
            String deliveryStatus = allSales.get(index).getDeliveryStatus();

            int paymentModeID = allSales.get(index).getPaymentMode();
            String paymentMode = "";
            switch (paymentModeID) {
                case 1:
                    paymentMode = "Cash Payment";
                    break;
                case 2:
                    paymentMode = "POS";
                    break;
                case 3:
                    paymentMode = "Cash Transfer";
                    break;
                default:
                    break;
            }

            
            insertIntoStaffSalesTable(salesID +"", salesDate.toString(), staff, customer, amount +"", soldFrom, salesMode, paymentMode, deliveryStatus);
        }
    }
    
    
    //populate sales table
    public void insertIntoStaffSalesTable(String salesID, String salesDate, String staff, String customer, String amount, String soldFrom, String salesMode, String paymentMode, String deliveryStatus){
        String[] rowData = {salesID, salesDate, staff, customer, amount, soldFrom, salesMode, paymentMode, deliveryStatus};
        dtm.addRow(rowData);
    }
    
    
    //display receipt on text area
    public void displayReceipt(ArrayList<SalesBreakdown> salesBreakdown, String customerName, String soldBy, String soldFrom, String paymentMode, String Date){
        double totalPurchase = 0;
        
        allSalesReceipt.setText(
        "              SEKARO INTEGRATED SERVICES                   " + "\n" +
        "-----------------------------------------------------------" + "\n" +
        "    House Appliances, Provision & General Supplies         " + "\n" +
        "     -Import   -Distributor   -WholeSale & Retail          " + "\n\n" +
        "  Head Office: 62 Aba Road, Opposite Govt Craft Dev.       " + "\n" +
        " Center, by Gada Junction, Port Harcourt Rivers State.      " + "\n" +
        "             Tel: 08032727999, 08137879809                 " + "\n\n\n" +
//        "-----------------------------------------------------------" + "\n" +
        "-----------------------------------------------------------" + "\n" +
        " QTY |    DESCRIPTION OF GOODS     |  RATE   |    AMOUNT   " + "\n" +
        "-----------------------------------------------------------" + "\n"
        );
        
        for(int index = 0; index < salesBreakdown.size(); index++){
            int qty = salesBreakdown.get(index).getQuantity();
            String name = salesBreakdown.get(index).getProductName();
            double price = salesBreakdown.get(index).getSalesPrice();
            double totalPrice = salesBreakdown.get(index).getQuantityXprice();
            
            allSalesReceipt.setText(allSalesReceipt.getText() +
                    " "+qty+"      "+name+"                     "+price+"      "+totalPrice+"\n"
            );
            
            totalPurchase += totalPrice;
        }
        
        allSalesReceipt.setText(allSalesReceipt.getText() +
                "\n\n\n" +
                "-----------------------------------------------------------" + "\n" +
                " Total amount: "+totalPurchase+"\n" +
                " Payment Mode: "+paymentMode+"\n" +
                "-----------------------------------------------------------" + "\n" +
                "Customer Name: " + customerName + "\n" +
                "Sold By: " + soldBy + "\n" +
                "Sold From: " + soldFrom + "\n" +
                "Date: " + Date + "\n" +
                "-----------------------------------------------------------" + "\n" +
                "                THANKS FOR VISITING US                     " + "\n" 
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        salesDisplayTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        allSalesReceipt = new javax.swing.JTextArea();
        printSales = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 29));

        jLabel5.setBackground(new java.awt.Color(51, 51, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("  Select to view order breakdown:");
        jLabel5.setOpaque(true);

        salesDisplayTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        salesDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sales ID", "Date", "Sold By", "Customer", "Amount", "Sold From", "Sales Mode", "Payment Mode", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salesDisplayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesDisplayTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(salesDisplayTable);

        jLabel6.setBackground(new java.awt.Color(51, 51, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("  Order Breakdown:");
        jLabel6.setOpaque(true);

        allSalesReceipt.setEditable(false);
        allSalesReceipt.setColumns(20);
        allSalesReceipt.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        allSalesReceipt.setRows(5);
        jScrollPane7.setViewportView(allSalesReceipt);

        printSales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        printSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        printSales.setText("Print");
        printSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printSalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printSales, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(printSales))
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void printSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printSalesActionPerformed
        if(allSalesReceipt.getText().equals("")){
            JOptionPane.showMessageDialog(this, "No receipt yet", "message", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                allSalesReceipt.print();
            }
            catch(PrinterException ex){
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_printSalesActionPerformed

    private void salesDisplayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesDisplayTableMouseClicked
        int salesID = Integer.parseInt(salesDisplayTable.getValueAt(salesDisplayTable.getSelectedRow(), 0).toString());
        String customerName = salesDisplayTable.getValueAt(salesDisplayTable.getSelectedRow(), 3).toString();
        String soldBy = salesDisplayTable.getValueAt(salesDisplayTable.getSelectedRow(), 2).toString();
        String soldFrom = salesDisplayTable.getValueAt(salesDisplayTable.getSelectedRow(), 5).toString();
        String Date = salesDisplayTable.getValueAt(salesDisplayTable.getSelectedRow(), 1).toString();
        String paymentMode = salesDisplayTable.getValueAt(salesDisplayTable.getSelectedRow(), 7).toString();
        
        ArrayList<SalesBreakdown> salesBreakdown = dbConnection.getSalesBreakdown(salesID);
        
        //display receipt on text area
        displayReceipt(salesBreakdown, customerName, soldBy, soldFrom, paymentMode, Date);
    }//GEN-LAST:event_salesDisplayTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea allSalesReceipt;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton printSales;
    private javax.swing.JTable salesDisplayTable;
    // End of variables declaration//GEN-END:variables
}
