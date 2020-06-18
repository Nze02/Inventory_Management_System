/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Beans.Sales;
import Beans.SalesBreakdown;
import java.awt.print.PrinterException;
import java.util.ArrayList;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class CustomerPurchaseReceipt extends javax.swing.JDialog {

    protected static ArrayList<SalesBreakdown> salesBreakdown;
    protected static Sales sale;
    protected static String customerName;
    protected static String soldBy;

    /**
     * Creates new form EditProduct
     */
    public CustomerPurchaseReceipt(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(200, 50);
        callDisplayReceipt();
    }

    //display recsipt
    public void callDisplayReceipt() {
        
        String soldFrom = sale.getSoldFrom();

        int paymentModeID = sale.getPaymentMode();
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
        String date = sale.getSalesDate().toString();

        displayReceipt(salesBreakdown, customerName, soldBy, soldFrom, paymentMode, date);
    }

    //display receipt on text area
    public void displayReceipt(ArrayList<SalesBreakdown> salesBreakdown, String customerName, String soldBy, String soldFrom, String paymentMode, String Date) {
        double totalPurchase = 0;

        customerReceipt.setText(
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

            customerReceipt.setText(customerReceipt.getText()
                    + " " + qty + "      " + name + "                     " + price + "      " + totalPrice + "\n"
            );

            totalPurchase += totalPrice;
        }

        customerReceipt.setText(customerReceipt.getText()
                + "\n\n\n"
                + "-----------------------------------------------------------" + "\n"
                + " Total amount: " + totalPurchase + "\n"
                + " Payment Mode: " + paymentMode + "\n"
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerReceipt = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Receipt");

        jPanel1.setBackground(new java.awt.Color(0, 0, 29));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("  Sales Receipt");
        jLabel1.setOpaque(true);

        customerReceipt.setEditable(false);
        customerReceipt.setColumns(20);
        customerReceipt.setRows(5);
        jScrollPane2.setViewportView(customerReceipt);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //        JOptionPane.showMessageDialog(this, "Data Exported.", "Message.", JOptionPane.WARNING_MESSAGE);
        try {
            customerReceipt.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
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
            java.util.logging.Logger.getLogger(CustomerPurchaseReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerPurchaseReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerPurchaseReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerPurchaseReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerPurchaseReceipt dialog = new CustomerPurchaseReceipt(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextArea customerReceipt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
