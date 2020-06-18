/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Sales;
import Beans.SalesBreakdown;
import Beans.Staff;
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
public class UserSalesPanel extends javax.swing.JPanel {

    private DBConnection dbConnection = null;
    private DefaultTableModel dtm;
    protected static Staff staff;

    /**
     * Creates new form UserProfilePanel
     */
    public UserSalesPanel() {
        initComponents();

        dbConnection = new DBConnection();
        dtm = (DefaultTableModel) staffSalesDisplayTable.getModel();
//        staff = dbConnection.getStaffByID(staff.getId());     //refresh the staff attributes' values with the recent values

        populateStaffSalesTable();
    }

    //method to populate the staff sales table
    public void populateStaffSalesTable() {
        ArrayList<Sales> staffSales = dbConnection.getStaffSales(staff.getId());
        for (int index = 0; index < staffSales.size(); index++) {
            int salesID = staffSales.get(index).getSalesID();
            Date salesDate = staffSales.get(index).getSalesDate();
            String customer = staffSales.get(index).getCustomerFName() + " " + staffSales.get(index).getCustomerLName();
            double amount = staffSales.get(index).getAmount();
            String soldFrom = staffSales.get(index).getSoldFrom();
            String salesMode = staffSales.get(index).getSalesMode();
            String deliveryStatus = staffSales.get(index).getDeliveryStatus();

            int paymentModeID = staffSales.get(index).getPaymentMode();
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

            insertIntoStaffSalesTable(salesID + "", salesDate.toString(), customer, amount + "", soldFrom, salesMode, paymentMode, deliveryStatus);
        }
    }

    //populate staff sales table
    public void insertIntoStaffSalesTable(String salesID, String salesDate, String amount, String customer, String soldFrom, String salesMode, String paymentMode, String deliveryStatus) {
        String[] rowData = {salesID, salesDate, amount, customer, soldFrom, salesMode, paymentMode, deliveryStatus};
        dtm.addRow(rowData);
    }

    //display receipt on text area
    public void displayReceipt(ArrayList<SalesBreakdown> salesBreakdown, String customerName, String soldBy, String soldFrom, String paymentMode, String Date) {
        double totalPurchase = 0;

        staffSalesReceipt.setText(
                "              SEKARO INTEGRATED SERVICES                   " + "\n"
                + "-----------------------------------------------------------" + "\n"
                + "    House Appliances, Provision & General Supplies         " + "\n"
                + "     -Import   -Distributor   -WholeSale & Retail          " + "\n\n"
                + "  Head Office: 62 Aba Road, Opposite Govt Craft Dev.       " + "\n"
                + " Center, by Gada Junction, Port Harcourt Rivers State.      " + "\n"
                + "             Tel: 08032727999, 08137879809                 " + "\n\n\n"
                + //        "-----------------------------------------------------------" + "\n" +
                "-----------------------------------------------------------" + "\n"
                + " QTY |    DESCRIPTION OF GOODS     |  RATE   |    AMOUNT   " + "\n"
                + "-----------------------------------------------------------" + "\n"
        );

        for (int index = 0; index < salesBreakdown.size(); index++) {
            int qty = salesBreakdown.get(index).getQuantity();
            String name = salesBreakdown.get(index).getProductName();
            double price = salesBreakdown.get(index).getSalesPrice();
            double totalPrice = salesBreakdown.get(index).getQuantityXprice();

            staffSalesReceipt.setText(staffSalesReceipt.getText()
                    + " " + qty + "      " + name + "                     " + price + "      " + totalPrice + "\n"
            );

            totalPurchase += totalPrice;
        }

        staffSalesReceipt.setText(staffSalesReceipt.getText()
                + "\n\n\n"
                + "-----------------------------------------------------------" + "\n"
                + " Total amount: " + totalPurchase + "\n" 
                + " Payment Mode: "+paymentMode+"\n" 
                + "-----------------------------------------------------------" + "\n"
                + "Customer Name: " + customerName + "\n"
                + "Sold By: " + soldBy + "\n"
                + "Sold From: " + soldFrom + "\n"
                + "Date: " + Date + "\n"
                + "-----------------------------------------------------------" + "\n"
                + "                THANKS FOR VISITING US                     " + "\n"
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

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        staffSalesDisplayTable = new javax.swing.JTable();
        printStaffSales = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        staffSalesReceipt = new javax.swing.JTextArea();

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        staffSalesDisplayTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        staffSalesDisplayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sales ID", "Date", "Customer", "Amount", "Sold From", "Sales Mode", "Payment Mode", "Delivery Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffSalesDisplayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                staffSalesDisplayTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(staffSalesDisplayTable);

        printStaffSales.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        printStaffSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        printStaffSales.setText("Print");
        printStaffSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printStaffSalesActionPerformed(evt);
            }
        });

        staffSalesReceipt.setEditable(false);
        staffSalesReceipt.setColumns(20);
        staffSalesReceipt.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        staffSalesReceipt.setRows(5);
        jScrollPane7.setViewportView(staffSalesReceipt);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(printStaffSales, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(printStaffSales, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void printStaffSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printStaffSalesActionPerformed
        if (staffSalesReceipt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "No receipt yet", "message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                staffSalesReceipt.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_printStaffSalesActionPerformed

    private void staffSalesDisplayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffSalesDisplayTableMouseClicked
        int salesID = Integer.parseInt(staffSalesDisplayTable.getValueAt(staffSalesDisplayTable.getSelectedRow(), 0).toString());
        String customerName = staffSalesDisplayTable.getValueAt(staffSalesDisplayTable.getSelectedRow(), 2).toString();
        String soldBy = staff.getFirstName() + " " + staff.getLastName();
        String soldFrom = staffSalesDisplayTable.getValueAt(staffSalesDisplayTable.getSelectedRow(), 4).toString();
        String Date = staffSalesDisplayTable.getValueAt(staffSalesDisplayTable.getSelectedRow(), 1).toString();
        String paymentMode = staffSalesDisplayTable.getValueAt(staffSalesDisplayTable.getSelectedRow(), 6).toString();

        ArrayList<SalesBreakdown> salesBreakdown = dbConnection.getSalesBreakdown(salesID);

        //display receipt on text area
        displayReceipt(salesBreakdown, customerName, soldBy, soldFrom, paymentMode, Date);

    }//GEN-LAST:event_staffSalesDisplayTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton printStaffSales;
    private javax.swing.JTable staffSalesDisplayTable;
    private javax.swing.JTextArea staffSalesReceipt;
    // End of variables declaration//GEN-END:variables
}
