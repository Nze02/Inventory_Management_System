/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

import Utils.SizeImage;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class AdminDashboard extends javax.swing.JPanel {
    protected static JPanel display;
    AdminHome adminHome;
    AdminProductsScreen product;
    CategoryScreen category;
    CustomerScreen customer;
    StaffScreen staff;
    ViewAllSales allSales;
    DepartmentScreen department;
    
    /**
     * Creates new form AdminProductsScreen
     */
    public AdminDashboard() {
        initComponents();
        display = displayScreenPanel;
        
        adminHome = new AdminHome();
        product = new AdminProductsScreen();
        category = new CategoryScreen();
        customer = new CustomerScreen();
        staff = new StaffScreen();
        allSales = new ViewAllSales();
        department = new DepartmentScreen();
        
        SizeImage.sizeImageToLabelSize2(logoLabel);
//        changePanelsVisibilityOnHomeScreen();
        addPanelsToMainScreenContentPanel();

        adminHome.setSize(displayScreenPanel.getSize());
        adminHome.setVisible(true);
    }
    
//    public void init2() {
//        addPanelsToMainScreenContentPanel();
//        changePanelsVisibilityOnHomeScreen();
//
//        customer.setSize(displayScreenPanel.getSize());
//        customer.setVisible(true);
//    }
    //method to add panels for display
    public void addPanelsToMainScreenContentPanel(){
        displayScreenPanel.add(adminHome);
        displayScreenPanel.add(product);
        displayScreenPanel.add(category);
        displayScreenPanel.add(customer);
        displayScreenPanel.add(staff);
        displayScreenPanel.add(allSales);
        displayScreenPanel.add(department);
    }

    //method to clear all visible panels on home screen
    public void changePanelsVisibilityOnHomeScreen(){
        for(Component component : this.displayScreenPanel.getComponents()){
            component.setVisible(false);
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
        productLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        studentLabel = new javax.swing.JLabel();
        usersLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        salesLabel = new javax.swing.JLabel();
        departmentLabel = new javax.swing.JLabel();
        adminDashboardHome = new javax.swing.JLabel();
        displayScreenPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 0));
        setPreferredSize(new java.awt.Dimension(1300, 615));

        jPanel1.setBackground(new java.awt.Color(0, 0, 11));
        jPanel1.setLayout(null);

        productLabel.setBackground(new java.awt.Color(255, 153, 51));
        productLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        productLabel.setForeground(new java.awt.Color(255, 255, 255));
        productLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productLabel.setText("PRODUCTS");
        productLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        productLabel.setOpaque(true);
        productLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productLabelMouseExited(evt);
            }
        });
        jPanel1.add(productLabel);
        productLabel.setBounds(0, 230, 210, 40);

        categoryLabel.setBackground(new java.awt.Color(255, 153, 51));
        categoryLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        categoryLabel.setForeground(new java.awt.Color(255, 255, 255));
        categoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        categoryLabel.setText("CATEGORY");
        categoryLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        categoryLabel.setOpaque(true);
        categoryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categoryLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categoryLabelMouseExited(evt);
            }
        });
        jPanel1.add(categoryLabel);
        categoryLabel.setBounds(0, 270, 210, 40);

        studentLabel.setBackground(new java.awt.Color(255, 153, 51));
        studentLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        studentLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        studentLabel.setText("CUSTOMER");
        studentLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        studentLabel.setOpaque(true);
        studentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                studentLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                studentLabelMouseExited(evt);
            }
        });
        jPanel1.add(studentLabel);
        studentLabel.setBounds(0, 310, 210, 40);

        usersLabel.setBackground(new java.awt.Color(255, 153, 51));
        usersLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        usersLabel.setForeground(new java.awt.Color(255, 255, 255));
        usersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersLabel.setText("STAFF");
        usersLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        usersLabel.setOpaque(true);
        usersLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usersLabelMouseExited(evt);
            }
        });
        jPanel1.add(usersLabel);
        usersLabel.setBounds(0, 350, 210, 40);

        logoLabel.setFont(new java.awt.Font("Gill Sans MT Ext Condensed Bold", 1, 48)); // NOI18N
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sis.png"))); // NOI18N
        logoLabel.setPreferredSize(new java.awt.Dimension(210, 99));
        jPanel1.add(logoLabel);
        logoLabel.setBounds(0, 30, 210, 110);

        salesLabel.setBackground(new java.awt.Color(255, 153, 51));
        salesLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        salesLabel.setForeground(new java.awt.Color(255, 255, 255));
        salesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesLabel.setText("SALES");
        salesLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        salesLabel.setOpaque(true);
        salesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salesLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salesLabelMouseExited(evt);
            }
        });
        jPanel1.add(salesLabel);
        salesLabel.setBounds(0, 390, 210, 40);

        departmentLabel.setBackground(new java.awt.Color(255, 153, 51));
        departmentLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        departmentLabel.setForeground(new java.awt.Color(255, 255, 255));
        departmentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        departmentLabel.setText("DEPARTMENTS");
        departmentLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        departmentLabel.setOpaque(true);
        departmentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                departmentLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                departmentLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                departmentLabelMouseExited(evt);
            }
        });
        jPanel1.add(departmentLabel);
        departmentLabel.setBounds(0, 421, 210, 60);

        adminDashboardHome.setBackground(new java.awt.Color(255, 153, 51));
        adminDashboardHome.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        adminDashboardHome.setForeground(new java.awt.Color(255, 255, 255));
        adminDashboardHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminDashboardHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home_24px.png"))); // NOI18N
        adminDashboardHome.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        adminDashboardHome.setOpaque(true);
        adminDashboardHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminDashboardHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminDashboardHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminDashboardHomeMouseExited(evt);
            }
        });
        jPanel1.add(adminDashboardHome);
        adminDashboardHome.setBounds(0, 180, 210, 50);

        displayScreenPanel.setBackground(new java.awt.Color(51, 51, 0));
        displayScreenPanel.setPreferredSize(new java.awt.Dimension(1080, 615));
        displayScreenPanel.setLayout(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(displayScreenPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(displayScreenPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void productLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productLabelMouseExited
        productLabel.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_productLabelMouseExited

    private void productLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productLabelMouseEntered
        productLabel.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_productLabelMouseEntered

    private void productLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productLabelMouseClicked
        changePanelsVisibilityOnHomeScreen();

        product.setSize(displayScreenPanel.getSize());
        product.setVisible(true);
    }//GEN-LAST:event_productLabelMouseClicked

    private void studentLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentLabelMouseExited
        studentLabel.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_studentLabelMouseExited

    private void studentLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentLabelMouseEntered
        studentLabel.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_studentLabelMouseEntered

    private void studentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentLabelMouseClicked
        changePanelsVisibilityOnHomeScreen();

        customer.setSize(displayScreenPanel.getSize());
        customer.setVisible(true);
    }//GEN-LAST:event_studentLabelMouseClicked

    private void usersLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersLabelMouseExited
        usersLabel.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_usersLabelMouseExited

    private void usersLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersLabelMouseEntered
        usersLabel.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_usersLabelMouseEntered

    private void usersLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersLabelMouseClicked
        changePanelsVisibilityOnHomeScreen();

        staff.setSize(displayScreenPanel.getSize());
        staff.setVisible(true);
    }//GEN-LAST:event_usersLabelMouseClicked

    private void categoryLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryLabelMouseExited
        categoryLabel.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_categoryLabelMouseExited

    private void categoryLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryLabelMouseEntered
        categoryLabel.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_categoryLabelMouseEntered

    private void categoryLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryLabelMouseClicked
        changePanelsVisibilityOnHomeScreen();

        category.setSize(displayScreenPanel.getSize());
        category.setVisible(true);
    }//GEN-LAST:event_categoryLabelMouseClicked

    private void salesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesLabelMouseClicked
        changePanelsVisibilityOnHomeScreen();

        allSales.setSize(displayScreenPanel.getSize());
        allSales.setVisible(true);
    }//GEN-LAST:event_salesLabelMouseClicked

    private void salesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesLabelMouseEntered
        salesLabel.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_salesLabelMouseEntered

    private void salesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesLabelMouseExited
        salesLabel.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_salesLabelMouseExited

    private void departmentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentLabelMouseClicked
        changePanelsVisibilityOnHomeScreen();

        department.setSize(displayScreenPanel.getSize());
        department.setVisible(true);
    }//GEN-LAST:event_departmentLabelMouseClicked

    private void departmentLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentLabelMouseEntered
        departmentLabel.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_departmentLabelMouseEntered

    private void departmentLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentLabelMouseExited
        departmentLabel.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_departmentLabelMouseExited

    private void adminDashboardHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminDashboardHomeMouseClicked
        changePanelsVisibilityOnHomeScreen();

        adminHome.setSize(displayScreenPanel.getSize());
        adminHome.setVisible(true);
    }//GEN-LAST:event_adminDashboardHomeMouseClicked

    private void adminDashboardHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminDashboardHomeMouseEntered
        adminDashboardHome.setBackground(new Color(255, 117, 52));
    }//GEN-LAST:event_adminDashboardHomeMouseEntered

    private void adminDashboardHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminDashboardHomeMouseExited
        adminDashboardHome.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_adminDashboardHomeMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adminDashboardHome;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JPanel displayScreenPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel productLabel;
    private javax.swing.JLabel salesLabel;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JLabel usersLabel;
    // End of variables declaration//GEN-END:variables
}