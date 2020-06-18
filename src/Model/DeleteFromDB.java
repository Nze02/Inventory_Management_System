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
public class DeleteFromDB {
    private Connection connection = null;

    public DeleteFromDB(Connection connection) {
        this.connection = connection;
    }
    
    
    //delete a product
    public int deleteProduct(int productID){
        
        String QUERY = "delete from products where id = ?";
        int deleteStatus = 0;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, productID);

            deleteStatus = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while deleting product by id: " + ex.getMessage());
            ex.printStackTrace();
        }

        return deleteStatus;
    }
    
    
    //delete a category
    public int deleteCategory(int categoryID){
        
        String QUERY = "delete from category where id = ?";
        int deleteStatus = 0;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, categoryID);

            deleteStatus = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while deleting category by id: " + ex.getMessage());
            ex.printStackTrace();
        }

        return deleteStatus;
    }
    
    
    //delete a customer
    public int deleteCustomer(int customerID){
        
        String QUERY = "delete from customer where id = ?";
        int deleteStatus = 0;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, customerID);

            deleteStatus = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while deleting customer by id: " + ex.getMessage());
            ex.printStackTrace();
        }

        return deleteStatus;
    }
    
    
    //delete a staff
    public int deleteStaff(int staffID){
        
        String QUERY = "delete from staff where id = ?";
        int deleteStatus = 0;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, staffID);

            deleteStatus = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while deleting staff by id: " + ex.getMessage());
            ex.printStackTrace();
        }

        return deleteStatus;
    }
    
    
    //delete a department
    public int deleteDepartment(int departmentID){
        
        String QUERY = "delete from department where department_id = ?";
        int deleteStatus = 0;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, departmentID);

            deleteStatus = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while deleting department by id: " + ex.getMessage());
            ex.printStackTrace();
        }

        return deleteStatus;
    }
    
}
