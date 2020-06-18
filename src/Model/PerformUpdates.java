/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Beans.Customer;
import Beans.Category;
import Beans.Products;
import Beans.Sales;
import Beans.SalesBreakdown;
import Beans.Staff;
import Beans.StaffDepartment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class PerformUpdates {
    private Connection connection = null;

    public PerformUpdates(Connection connection) {
        this.connection = connection;
    }
    
    
    //update staff details
    public int updateSaffProfile(int id, String phone, String email, String password){
        String QUERY = "update staff set phone = ?, email = ?, password = ? where staff.id = ?";
        int updated = 0;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, phone);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4, id);
                

            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating staff details: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //update product details
    public int updateProduct(Products product){
        String QUERY = "update products "
                + "set category_id = ?, barcode = ?, name = ?, qty = ?, price = ?, purchased_from = ?, purchase_date = ?, description = ? "
                + "where products.id = ?";
        
        int updated = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, Integer.parseInt(product.getCategory()));
                preparedStatement.setString(2, product.getBarcode());
                preparedStatement.setString(3, product.getName());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setDouble(5, product.getCostPrice());
                preparedStatement.setString(6, product.getPurchasedFrom());
                preparedStatement.setDate(7, product.getDate());
                preparedStatement.setString(8, product.getDescription());
                preparedStatement.setInt(9, product.getProductID());
                
            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating product details: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //update product details
    public int updateProductWithNewImage(Products product){
        String QUERY = "update products "
                + "set category_id = ?, barcode = ?, name = ?, qty =?, price = ?, purchased_from = ?, purchase_date = ?, description = ?, image = ? "
                + "where products.id = ?";
        
        int updated = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, Integer.parseInt(product.getCategory()));
                preparedStatement.setString(2, product.getBarcode());
                preparedStatement.setString(3, product.getName());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setDouble(5, product.getCostPrice());
                preparedStatement.setString(6, product.getPurchasedFrom());
                preparedStatement.setDate(7, product.getDate());
                preparedStatement.setString(8, product.getDescription());
                preparedStatement.setString(9, product.getImage());
                preparedStatement.setInt(10, product.getProductID());
                
            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating product details: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //add new product
    public int insertProduct(Products product){
        String QUERY = "insert into products(category_id, barcode, name, qty, price, purchased_from, purchase_date, description, image) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, Integer.parseInt(product.getCategory()));
                preparedStatement.setString(2, product.getBarcode());
                preparedStatement.setString(3, product.getName());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setDouble(5, product.getCostPrice());
                preparedStatement.setString(6, product.getPurchasedFrom());
                preparedStatement.setDate(7, product.getDate());
                preparedStatement.setString(8, product.getDescription());
                preparedStatement.setString(9, product.getImage());
                
            inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new products: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
    
    
    //add new product
    public int insertCategory(Category category){
        String QUERY = "insert into category(name) values(?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, category.getCategoryName());
                
            inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new category: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
    
    
    //update category details
    public int updateCategory(Category category){
        String QUERY = "update category "
                + "set name = ? "
                + "where category.id = ?";
        
        int updated = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, category.getCategoryName());
                preparedStatement.setInt(2, category.getCategoryID());
                
            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating category details: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //update customer details
    public int updateCustomer(Customer customer){
        String QUERY = "update customer "
                + "set firstname = ?, lastname = ?, phone = ?, email = ? "
                + "where customer.id = ?";
        
        int updated = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, customer.getCustomerFirstName());
                preparedStatement.setString(2, customer.getCustomerLastName());
                preparedStatement.setString(3, customer.getCustomerPhone());
                preparedStatement.setString(4, customer.getCustomerEmail());
                preparedStatement.setInt(5, customer.getCustomerID());
                
            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating customer details: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //add new staff
    public int insertStaff(Staff staff){
        String QUERY = "insert into staff(firstname, lastname, phone, email, department, username, password, user_type) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, staff.getFirstName());
                preparedStatement.setString(2, staff.getLastName());
                preparedStatement.setString(3, staff.getPhone());
                preparedStatement.setString(4, staff.getEmail());
                preparedStatement.setInt(5, Integer.parseInt(staff.getDepartment()));
                preparedStatement.setString(6, staff.getUsername());
                preparedStatement.setString(7, staff.getPassword());
                preparedStatement.setString(8, staff.getStatus());
                
            inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new staff: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
    
    
    //add new department
    public int insertDepartment(StaffDepartment department){
        String QUERY = "insert into department(department_desc) values(?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, department.getDepartment());
                
            inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new department: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
    
    
    //update department details
    public int updateDepartment(StaffDepartment department){
        String QUERY = "update department "
                + "set department_desc = ? "
                + "where department.department_id = ?";
        
        int updated = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, department.getDepartment());
                preparedStatement.setInt(2, department.getDeptID());
                
            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating department details: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //add new customer
    public int insertCustomer(Customer customer){
        String QUERY = "insert into customer(firstname, lastname, phone, email) values(?, ?, ?, ?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setString(1, customer.getCustomerFirstName());
                preparedStatement.setString(2, customer.getCustomerLastName());
                preparedStatement.setString(3, customer.getCustomerPhone());
                preparedStatement.setString(4, customer.getCustomerEmail());
                
            inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new customer: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
    
    
    //update Product Qty
    public int updateProductQty(int productID, int qty){
        
        String QUERY = "update products "
                + "set qty = ? "
                + "where products.id = ?";
        
        int updated = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, qty);
                preparedStatement.setInt(2, productID);
                
            updated = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while updating Product Qty: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return updated;
    }
    
    
    //add new sale
    public int addNewSale(Sales sale){
        
        String QUERY = "insert into sales(customer_id, staff_id, sales_date, amount, sold_from, sales_mode, delivery_status_id, payment_Mode_id) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, sale.getCustID());
                preparedStatement.setInt(2, sale.getStaffID());
                preparedStatement.setDate(3, sale.getSalesDate());
                preparedStatement.setDouble(4, sale.getAmount());
                preparedStatement.setString(5, sale.getSoldFrom());
                preparedStatement.setString(6, sale.getSalesMode());
                preparedStatement.setInt(7, 1);
                preparedStatement.setInt(8, sale.getPaymentMode());
                
                inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new sale: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
    
    
    //add new sales Breakdowns
    public int addNewSaleBreakdown(SalesBreakdown salesBreakdowns){
        
        String QUERY = "insert into sales_breakdown(sales_id, product_id, sales_price, quantity, quantity_x_price) "
                + "values(?, ?, ?, ?, ?)";
        
        int inserted = 0;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
                preparedStatement.setInt(1, salesBreakdowns.getSalesID());
                preparedStatement.setInt(2, salesBreakdowns.getProductID());
                preparedStatement.setDouble(3, salesBreakdowns.getSalesPrice());
                preparedStatement.setInt(4, salesBreakdowns.getQuantity());
                preparedStatement.setDouble(5, salesBreakdowns.getQuantityXprice());
                
                inserted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error while inserting new sales Breakdowns: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return inserted;
    }
}
