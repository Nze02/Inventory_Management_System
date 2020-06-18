/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Beans.Staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class VerifyLogins {
    private Connection connection = null;

    public VerifyLogins(Connection connection) {
        this.connection = connection;
    }
    
        
    //verify admin login details
    public Staff verifyAdminLogin(String username, String password) {
        Staff staff = null;
        
        String QUERY = "select * from staff where username = ? and password = ? and user_type = ?";
        ResultSet result = null;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, "Admin");

            result = preparedStatement.executeQuery();
            while (result.next()) {
//                adminStatus = true;
                int id = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String department = result.getString("Department");
                String uname = result.getString("username");
                String pword = result.getString("password");
                String userType = result.getString("user_type");
                staff = new Staff(id, firstName, lastName, department, phone, email, username, password, userType);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error while verifying admin login: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for admin login verification: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staff;
    }

    
    //verify admin login details
    public Staff verifyStaffLogin(String username, String password) {
        Staff staff = null;
        
        String QUERY = "select * from staff where username = ? and password = ?";
        ResultSet result = null;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String department = result.getString("Department");
                String uname = result.getString("username");
                String pword = result.getString("password");
                String userType = result.getString("user_type");
                staff = new Staff(id, firstName, lastName, department, phone, email, username, password, userType);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error while verifying staff login: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for staff login verification: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staff;
    }
    

}
