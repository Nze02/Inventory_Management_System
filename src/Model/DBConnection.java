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
import Beans.StaffStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class DBConnection {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/back_to_school_inventory";
    private static String username = "root";
    private static String password = "";
    
    private VerifyLogins verifyLogins;
    private PerformUpdates performUpdates;
    private RetrieveFromDB retrieveFromDB;
    private DeleteFromDB deleteFromDB;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("Error while creating database connection: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        verifyLogins = new VerifyLogins(connection);
        performUpdates = new PerformUpdates(connection);
        retrieveFromDB = new RetrieveFromDB(connection);
        deleteFromDB = new DeleteFromDB(connection);
    }
    
    
    //verify admin login details
    public Staff verifyAdminLogin(String username, String password) {
        return verifyLogins.verifyAdminLogin(username, password);
    }
    
    //verify admin login details
    public Staff verifyStaffLogin(String username, String password) {
        return verifyLogins.verifyStaffLogin(username, password);
    }
    
    //update staff profile
    public int updateStaffProfile(int id, String phone, String email, String password){
        return performUpdates.updateSaffProfile(id, phone, email, password);
    }
    
    
    //get staff details by id
    public Staff getStaffByID(int id) {
        return retrieveFromDB.getStaffByID(id);
    } 
    
    
    //get all sales
    public ArrayList<Sales> getAllSales(){
        return retrieveFromDB.getAllSales();
    }
    
    
    //get a particular staff's sales
    public ArrayList<Sales> getStaffSales(int staffID){
        return retrieveFromDB.getStaffSales(staffID);
    }
    
    
    //get a particular staff sales' breakdown
    public ArrayList<SalesBreakdown> getSalesBreakdown(int salesID){
        return retrieveFromDB.getSalesBreakdown(salesID);
    }
    
    
    //get all products order by id
    public ArrayList<Products> getAllProducts(){
        return retrieveFromDB.getAllProducts();
    }
    
    
    //get all products order by name
    public ArrayList<Products> getAllProductsByName(){
        return retrieveFromDB.getAllProductsByName();
    }
    
    
    //get all products using ID
    public Products getProductUsingID(int prodID){
        return retrieveFromDB.getProductUsingID(prodID);
    }
    
    
    //get all products
    public ArrayList<Products> getProductUsingBarcode(String barcode){
        return retrieveFromDB.getProductUsingBarcode(barcode);
    }
    
    
    //get all products
    public ArrayList<Products> getProductUsingProductName(String productName){
        return retrieveFromDB.getProductUsingProductName(productName);
    }
    
    
    //get product using barcode
    public ArrayList<Products> getProductUsingCategory(int categoryID){
        return retrieveFromDB.getProductUsingCategory(categoryID);
    }
    
    //delete a product using id
    public int deleteProduct(int productID){
        return deleteFromDB.deleteProduct(productID);
    }
    
    
    //get all categories
    public ArrayList<Category> getProductCategories(){
        return retrieveFromDB.getProductCategories();
    }
    
    //update product details
    public int updateProduct(Products product){
        return performUpdates.updateProduct(product);
    }
    
    //update product details
    public int updateProductWithNewImage(Products product){
        return performUpdates.updateProductWithNewImage(product);
    }
    
    //update product details
    public int insertProduct(Products product){
        return performUpdates.insertProduct(product);
    }
    
    //add new category
    public int insertCategory(Category category){
        return performUpdates.insertCategory(category);
    }

    //update category
    public int updateCategory(Category updatedCategory) {
        return performUpdates.updateCategory(updatedCategory);
    }
    
    //delete a category
    public int deleteCategory(int categoryID){
        return deleteFromDB.deleteCategory(categoryID);
    }
    
    //get all customers order by ID
    public ArrayList<Customer> getAllCustomers(){
        return retrieveFromDB.getAllCustomers();
    }
    
    //get all customers details order by ID
    public ArrayList<Customer> getAllCustomersDetails(){
        return retrieveFromDB.getAllCustomersDetails();
    }
    
    //get all customers order by name
    public ArrayList<Customer> getAllCustomersOrderByName(){
        return retrieveFromDB.getAllCustomersOrderByName();
    }
    
    //get all customers order by highest purchase
    public ArrayList<Customer> getAllCustomersOrderByHighestPurchase(){
        return retrieveFromDB.getAllCustomersOrderByHighestPurchase();
    }
    
    //retrieve customer's last purchase date and purchase count
    public ArrayList<String> getLastPurchaseDate_Count(int customerID){
        return retrieveFromDB.getLastPurchaseDate_Count(customerID);
    }
    
    //delete a customer
    public int deleteCustomer(int customerID){
        return deleteFromDB.deleteCustomer(customerID);
    }
    
    //update customer details
    public int updateCustomer(Customer customer){
        return performUpdates.updateCustomer(customer);
    }

    //add new customer
    public int insertCustomer(Customer customer){
        return performUpdates.insertCustomer(customer);
    }
    
    //get all staff order by ID
    public ArrayList<Staff> getAllStaff(){
        return retrieveFromDB.getAllStaff();
    }
    
    //delete a staff
    public int deleteStaff(int staffID){
        return deleteFromDB.deleteStaff(staffID);
    }
    
    //get all staff status
    public ArrayList<StaffStatus> getStaffStatus(){
        return retrieveFromDB.getStaffStatus();
    }
    
    //get all staff department
    public ArrayList<StaffDepartment> getStaffDepartment(){
        return retrieveFromDB.getStaffDepartment();
    }
    
    //add new staff
    public int insertStaff(Staff staff){
        return performUpdates.insertStaff(staff);
    }
    
    //get all departments
    public ArrayList<StaffDepartment> getDepartments(){
        return retrieveFromDB.getDepartments();
    }
    
    //get staff in this department
    public ArrayList<Staff> getStaffInThisDepartment(int departmentID){
        return retrieveFromDB.getStaffInThisDepartment(departmentID);
    }
    
    //add new department
    public int insertDepartment(StaffDepartment department){
        return performUpdates.insertDepartment(department);
    }
    
    //update department details
    public int updateDepartment(StaffDepartment department){
        return performUpdates.updateDepartment(department);
    }
    
    //delete a department
    public int deleteDepartment(int departmentID){
        return deleteFromDB.deleteDepartment(departmentID);
    }
    
    //get last sales ID
    public int getlastSalesID(){
        return retrieveFromDB.getlastSalesID();
    }
    
    //get Selected Product Qty
    public int getSelectedProductQty(int productID){
        return retrieveFromDB.getSelectedProductQty(productID);
    }
    
    //update Product Qty
    public int updateProductQty(int productID, int qty){
        return performUpdates.updateProductQty(productID, qty);
    }
    
    //add new sale
    public int addNewSale(Sales sale){
        return performUpdates.addNewSale(sale);
    }
    
    //add new sales Breakdowns
    public int addNewSaleBreakdown(SalesBreakdown salesBreakdowns){
        return performUpdates.addNewSaleBreakdown(salesBreakdowns);
    }
    
    //get staff department
    public String getDepartmentByID(int deptID){
        return retrieveFromDB.getDepartmentByID(deptID);
    }
    
    //get a product's image
    public String getProductImagePath(int productID){
        return retrieveFromDB.getProductImagePath(productID);
    }
}
