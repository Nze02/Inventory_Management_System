/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIs;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class WelcomeScreen extends javax.swing.JFrame {

    /**
     * Creates new form WelcomeScreen
     */
    public WelcomeScreen() {
        initComponents();
        this.setLocation(300,100);
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
        gotoStaffLoginButton = new javax.swing.JButton();
        gotoAdminLoginButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        exitStaffLoginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 553));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 553));
        jPanel1.setLayout(null);

        gotoStaffLoginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gotoStaffLoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ulogin.png"))); // NOI18N
        gotoStaffLoginButton.setText("Staff Login");
        gotoStaffLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoStaffLoginButtonActionPerformed(evt);
            }
        });
        gotoStaffLoginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gotoStaffLoginButtonKeyPressed(evt);
            }
        });
        jPanel1.add(gotoStaffLoginButton);
        gotoStaffLoginButton.setBounds(230, 290, 186, 49);

        gotoAdminLoginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gotoAdminLoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/alogin.png"))); // NOI18N
        gotoAdminLoginButton.setText("Admin Login");
        gotoAdminLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoAdminLoginButtonActionPerformed(evt);
            }
        });
        gotoAdminLoginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gotoAdminLoginButtonKeyPressed(evt);
            }
        });
        jPanel1.add(gotoAdminLoginButton);
        gotoAdminLoginButton.setBounds(230, 230, 186, 49);

        jLabel4.setFont(new java.awt.Font("Segoe Print", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("by LedTechnology");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(520, 440, 100, 20);

        exitStaffLoginButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exitStaffLoginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        exitStaffLoginButton.setText("Exit");
        exitStaffLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitStaffLoginButtonActionPerformed(evt);
            }
        });
        exitStaffLoginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                exitStaffLoginButtonKeyPressed(evt);
            }
        });
        jPanel1.add(exitStaffLoginButton);
        exitStaffLoginButton.setBounds(230, 350, 186, 49);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/students-cycling-street-front-blackboard-back-school-education-concept_33753-68.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(800, 553));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 553));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 553));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 630, 460);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gotoStaffLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoStaffLoginButtonActionPerformed
        dispose();
        StaffLogin staffLogin = new StaffLogin();
        staffLogin.setVisible(true);
    }//GEN-LAST:event_gotoStaffLoginButtonActionPerformed

    private void gotoStaffLoginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gotoStaffLoginButtonKeyPressed
        
    }//GEN-LAST:event_gotoStaffLoginButtonKeyPressed

    private void gotoAdminLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoAdminLoginButtonActionPerformed
        dispose();
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setVisible(true);
    }//GEN-LAST:event_gotoAdminLoginButtonActionPerformed

    private void gotoAdminLoginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gotoAdminLoginButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_gotoAdminLoginButtonKeyPressed

    private void exitStaffLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitStaffLoginButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitStaffLoginButtonActionPerformed

    private void exitStaffLoginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exitStaffLoginButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitStaffLoginButtonKeyPressed

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
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitStaffLoginButton;
    private javax.swing.JButton gotoAdminLoginButton;
    private javax.swing.JButton gotoStaffLoginButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}