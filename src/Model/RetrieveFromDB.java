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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class RetrieveFromDB {

    private Connection connection = null;

    public RetrieveFromDB(Connection connection) {
        this.connection = connection;
    }

    //get all details about a staff with id = id
    public Staff getStaffByID(int id) {
        Staff staff = null;

        String QUERY = "select staff.Id, firstname, lastname, phone, email, department_desc, username, password, user_type "
                + "FROM staff JOIN department "
                + "ON staff.department = department.department_id "
                + "where id = ?";
        ResultSet result = null;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeQuery();
            while (result.next()) {
//                int id = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String department = result.getString("department_desc");
                String username = result.getString("username");
                String password = result.getString("password");
                String userType = result.getString("user_type");
                staff = new Staff(id, firstName, lastName, department, phone, email, username, password, userType);
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving staff details by id: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get staff by ID: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staff;
    }

    //get all sales
    public ArrayList<Sales> getAllSales() {
        ArrayList<Sales> staffSales = new ArrayList<>();

        String QUERY = "select sales_id, customer_id, staff_id, sales_date, amount, sold_from, sales_mode, delivery_status_description.delivery_status_desc, payment_mode_id, "
                + "customer.firstname AS custFName, customer.lastname AS custLName, staff.firstname AS staffFName, staff.lastname AS staffLName, delivery_status_desc "
                + "from sales "
                + "join customer on sales.customer_id = customer.id "
                + "join staff on sales.staff_id = staff.id "
                + "join delivery_status_description on sales.delivery_status_id = delivery_status_description.delivery_status_id "
                + "ORDER BY sales_id";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int salesID = result.getInt("sales_id");
                String customerFName = result.getString("custFName");
                String customerLName = result.getString("custLName");
                String staffFName = result.getString("staffFName");
                String staffLName = result.getString("staffLName");
                Date salesDate = result.getDate("sales_date");
                double amount = result.getDouble("amount");
                String soldFrom = result.getString("sold_from");
                String salesMode = result.getString("sales_mode");
                int paymentModeID = result.getInt("payment_Mode_id");
                String deliveryStatus = result.getString("delivery_status_desc");

                staffSales.add(new Sales(salesID, salesDate, amount, soldFrom, salesMode, paymentModeID, deliveryStatus, customerFName, customerLName, staffFName, staffLName));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving all sales details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get sales: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staffSales;
    }

    //get a particular staff's sales
    public ArrayList<Sales> getStaffSales(int staffID) {
        ArrayList<Sales> staffSales = new ArrayList<>();

        String QUERY = "select sales_id, customer_id, staff_id, sales_date, amount, sold_from, sales_mode, delivery_status_description.delivery_status_desc, payment_mode_id, "
                + "customer.firstname AS custFName, customer.lastname AS custLName, staff.firstname AS staffFName, staff.lastname AS staffLName, delivery_status_desc "
                + "from sales "
                + "join customer on sales.customer_id = customer.id "
                + "join staff on sales.staff_id = staff.id "
                + "join delivery_status_description on sales.delivery_status_id = delivery_status_description.delivery_status_id "
                + "where staff_id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, staffID);

            result = preparedStatement.executeQuery();
            while (result.next()) {
//                int id = result.getInt("id");
                int salesID = result.getInt("sales_id");
                String customerFName = result.getString("custFName");
                String customerLName = result.getString("custLName");
                String staffFName = result.getString("staffFName");
                String staffLName = result.getString("staffLName");
                Date salesDate = result.getDate("sales_date");
                double amount = result.getDouble("amount");
                String soldFrom = result.getString("sold_from");
                String salesMode = result.getString("sales_mode");
                int paymentModeID = result.getInt("payment_mode_id");
                String deliveryStatus = result.getString("delivery_status_desc");
                
                staffSales.add(new Sales(salesID, salesDate, amount, soldFrom, salesMode, paymentModeID, deliveryStatus, customerFName, customerLName, staffFName, staffLName));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving sales details using staffID:: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get sales by staffID: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staffSales;
    }

    //get a particular staff sales' breakdown
    public ArrayList<SalesBreakdown> getSalesBreakdown(int salesID) {
        ArrayList<SalesBreakdown> salesBreakdowns = new ArrayList<>();

        String QUERY = "select sales_breakdown_id, sales_id, product_id, sales_price, quantity, quantity_x_price, products.name AS product_name "
                + "from sales_breakdown "
                + "join products on sales_breakdown.product_id = products.id "
                + "where sales_id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, salesID);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int salesBreakdownID = result.getInt("sales_breakdown_id");
                String productName = result.getString("product_name");
                double salesPrice = result.getDouble("sales_price");
                int quantity = result.getInt("quantity");
                double quantityXprice = result.getDouble("quantity_x_price");

                salesBreakdowns.add(new SalesBreakdown(salesBreakdownID, salesID, productName, salesPrice, quantity, quantityXprice));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving sales breakdown details using salesID: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get sales breakdown by salesID: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return salesBreakdowns;
    }

    //get all products order by ID
    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> productsList = new ArrayList<>();

        String QUERY = "select products.id, category_id, barcode, products.name, qty, price, purchased_from, purchase_date, image, description, category.name AS category_name "
                + "from products "
                + "join category on products.category_id = category.id "
                + "where qty > 0 order by products.id";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("id");
                String barcode = result.getString("barcode");
                String category = result.getString("category_name");
                String name = result.getString("name");
                double costPrice = result.getDouble("price");
                int quantity = result.getInt("qty");
                Date date = result.getDate("purchase_date");
                String description = result.getString("description");
                String purchasedFrom = result.getString("purchased_from");
                String image = result.getString("image");

                productsList.add(new Products(productID, barcode, category, name, costPrice, quantity, date, description, purchasedFrom, image));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return productsList;
    }

    //get all products order by name
    public ArrayList<Products> getAllProductsByName() {
        ArrayList<Products> productsList = new ArrayList<>();

        String QUERY = "select products.id, category_id, barcode, products.name, qty, price, purchased_from, purchase_date, image, description, category.name AS category_name "
                + "from products "
                + "join category on products.category_id = category.id "
                + "where qty > 0 order by name";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("id");
                String barcode = result.getString("barcode");
                String category = result.getString("category_name");
                String name = result.getString("name");
                double costPrice = result.getDouble("price");
                int quantity = result.getInt("qty");
                Date date = result.getDate("purchase_date");
                String description = result.getString("description");
                String purchasedFrom = result.getString("purchased_from");
                String image = result.getString("image");

                productsList.add(new Products(productID, barcode, category, name, costPrice, quantity, date, description, purchasedFrom, image));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return productsList;
    }

    //get all products using ID
    public Products getProductUsingID(int prodID){
        Products product = null;

        String QUERY = "select products.id, category_id, barcode, products.name, qty, price, purchased_from, purchase_date, image, description, category.name AS category_name "
                + "from products "
                + "join category on products.category_id = category.id "
                + "where qty > 0 and products.id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, prodID);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("id");
                String barcode = result.getString("barcode");
                String category = result.getString("category_name");
                String name = result.getString("name");
                double costPrice = result.getDouble("price");
                int quantity = result.getInt("qty");
                Date date = result.getDate("purchase_date");
                String description = result.getString("description");
                String purchasedFrom = result.getString("purchased_from");
                String image = result.getString("image");

                product = new Products(productID, barcode, category, name, costPrice, quantity, date, description, purchasedFrom, image);
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products details using ID: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products using ID: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return product;
    }

    //get product using barcode
    public ArrayList<Products> getProductUsingBarcode(String barcode) {
        ArrayList<Products> productsList = new ArrayList<>();

        String QUERY = "select products.id, category_id, barcode, products.name, qty, price, purchased_from, purchase_date, image, description, category.name AS category_name "
                + "from products "
                + "join category on products.category_id = category.id "
                + "where qty > 0 and barcode = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setString(1, barcode);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("id");
//                String barcode = result.getString("barcode");
                String category = result.getString("category_name");
                String name = result.getString("name");
                double costPrice = result.getDouble("price");
                int quantity = result.getInt("qty");
                Date date = result.getDate("purchase_date");
                String description = result.getString("description");
                String purchasedFrom = result.getString("purchased_from");
                String image = result.getString("image");

                productsList.add(new Products(productID, barcode, category, name, costPrice, quantity, date, description, purchasedFrom, image));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return productsList;
    }

    //get product using barcode
    public ArrayList<Products> getProductUsingProductName(String productName) {
        ArrayList<Products> productsList = new ArrayList<>();

        String QUERY = "select * from products where qty > 0 and name = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setString(1, productName);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("id");
                String barcode = result.getString("barcode");
                String category = result.getString("category_id");
                String name = result.getString("name");
                double costPrice = result.getDouble("price");
                int quantity = result.getInt("qty");
                Date date = result.getDate("purchase_date");
                String description = result.getString("description");
                String purchasedFrom = result.getString("purchased_from");
                String image = result.getString("image");

                productsList.add(new Products(productID, barcode, category, name, costPrice, quantity, date, description, purchasedFrom, image));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return productsList;
    }

    //get product using barcode
    public ArrayList<Products> getProductUsingCategory(int categoryID) {
        ArrayList<Products> productsList = new ArrayList<>();

        String QUERY = "select * from products where qty > 0 and category_id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, categoryID);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int productID = result.getInt("id");
                String barcode = result.getString("barcode");
                String category = result.getString("category_id");
                String name = result.getString("name");
                double costPrice = result.getDouble("price");
                int quantity = result.getInt("qty");
                Date date = result.getDate("purchase_date");
                String description = result.getString("description");
                String purchasedFrom = result.getString("purchased_from");
                String image = result.getString("image");

                productsList.add(new Products(productID, barcode, category, name, costPrice, quantity, date, description, purchasedFrom, image));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products details using category: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products using category: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return productsList;
    }

    //get all categories
    public ArrayList<Category> getProductCategories() {
        ArrayList<Category> Categories = new ArrayList<>();

        String QUERY = "select * from category";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int CategoryID = result.getInt("id");
                String CategoryName = result.getString("name");

                Categories.add(new Category(CategoryID, CategoryName));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products category details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products category: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return Categories;
    }

    //get all customers order by ID
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customersList = new ArrayList<>();

        String QUERY = "select * from customer";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int customerID = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastname = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");

                customersList.add(new Customer(customerID, firstName, lastname, phone, email));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving customers details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get customers: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return customersList;
    }

    //get all customers details order by ID
    public ArrayList<Customer> getAllCustomersDetails() {
        ArrayList<Customer> customersList = new ArrayList<>();

        String QUERY = "select customer.id, firstname, lastname, phone, email, SUM(amount) AS total_purchase "
                + "from customer "
                + "join sales on customer.id = sales.customer_id "
                + "GROUP BY customer.id ORDER BY customer.id";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int customerID = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastname = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                double totalPurchase = result.getDouble("total_purchase");

                customersList.add(new Customer(customerID, firstName, lastname, phone, email, totalPurchase));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving customers full details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get customers full details: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return customersList;
    }

    //get all customers order by name
    public ArrayList<Customer> getAllCustomersOrderByName() {
        ArrayList<Customer> customersList = new ArrayList<>();

        String QUERY = "select customer.id, firstname, lastname, phone, email, SUM(amount) AS total_purchase "
                + "from customer "
                + "join sales on customer.id = sales.customer_id "
                + "GROUP BY customer.id ORDER BY firstname, lastname";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int customerID = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastname = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                double totalPurchase = result.getDouble("total_purchase");

                customersList.add(new Customer(customerID, firstName, lastname, phone, email, totalPurchase));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving customers details (sort by name): " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get customers (sort by name): " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return customersList;
    }

    //get all customers order by highest purchase
    public ArrayList<Customer> getAllCustomersOrderByHighestPurchase() {
        ArrayList<Customer> customersList = new ArrayList<>();

        String QUERY = "select customer.id, firstname, lastname, phone, email, SUM(amount) AS total_purchase "
                + "from customer "
                + "join sales on customer.id = sales.customer_id "
                + "GROUP BY customer.id ORDER BY total_purchase DESC";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int customerID = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastname = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                double totalPurchase = result.getDouble("total_purchase");

                customersList.add(new Customer(customerID, firstName, lastname, phone, email, totalPurchase));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving customers details (sort by highest purchase): " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get customers (sort by highest purchase): " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return customersList;
    }

    //retrieve customer's last purchase date and purchase count
    public ArrayList<String> getLastPurchaseDate_Count(int customerID) {
        ArrayList<String> lastPurchaseDate_Count = new ArrayList<>();

        String QUERY = "select MAX(sales_date) AS date, COUNT(customer_id) AS count FROM sales where customer_id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, customerID);

            result = preparedStatement.executeQuery();
            if (result == null) {
                lastPurchaseDate_Count = null;
            }

            while (result.next()) {

                lastPurchaseDate_Count.add(result.getString("date"));
                lastPurchaseDate_Count.add(result.getString("count"));

            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving customer's last purchase date and purchase count: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for customer's last purchase date and purchase count: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return lastPurchaseDate_Count;
    }

    //get all staff order by ID
    public ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> staffList = new ArrayList<>();

        String QUERY = "select staff.Id, firstname, lastname, phone, email, department_desc, username, password, user_type "
                + "FROM staff JOIN department "
                + "ON staff.department = department.department_id";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int staffID = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastname = result.getString("lastname");
                String department = result.getString("department_desc");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String username = result.getString("username");
                String password = result.getString("password");
                String status = result.getString("user_type");

                staffList.add(new Staff(staffID, firstName, lastname, department, phone, email, username, password, status));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving staff details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get staff: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staffList;
    }

    //get all staff status
    public ArrayList<StaffStatus> getStaffStatus() {
        ArrayList<StaffStatus> staffStatus = new ArrayList<>();

        String QUERY = "select * from staff_status";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int statusID = result.getInt("status_id");
                String status = result.getString("status_desc");

                staffStatus.add(new StaffStatus(statusID, status));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving staff status: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get staff status: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staffStatus;
    }

    //get all staff department
    public ArrayList<StaffDepartment> getStaffDepartment() {
        ArrayList<StaffDepartment> staffDepartment = new ArrayList<>();

        String QUERY = "select * from department";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int departmentID = result.getInt("department_id");
                String department = result.getString("department_desc");

                staffDepartment.add(new StaffDepartment(departmentID, department));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving staff department: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get staff department: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staffDepartment;
    }

    //get all departments
    public ArrayList<StaffDepartment> getDepartments() {
        ArrayList<StaffDepartment> departments = new ArrayList<>();

        String QUERY = "select * from department";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int departmentsID = result.getInt("department_id");
                String departmentsName = result.getString("department_desc");

                departments.add(new StaffDepartment(departmentsID, departmentsName));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving products category details: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get products category: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return departments;
    }

    //get staff in this department
    public ArrayList<Staff> getStaffInThisDepartment(int departmentID) {
        ArrayList<Staff> staff = new ArrayList<>();

        String QUERY = "select * from staff  where department = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, departmentID);

            result = preparedStatement.executeQuery();
            while (result.next()) {
                int staffID = result.getInt("id");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String department = result.getString("department");
                String username = result.getString("username");
                String password = result.getString("password");
                String userStatus = result.getString("user_type");

                staff.add(new Staff(staffID, firstName, lastName, phone, email, department, username, password, userStatus));
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving staffs in this department: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get staffs in this department: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return staff;
    }

    //get last sales ID
    public int getlastSalesID() {
        int lastOrderID = 0;

        String QUERY = "select MAX(sales_id) AS lastSalesID from sales";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            result = preparedStatement.executeQuery();
            while (result.next()) {

                lastOrderID = result.getInt("lastSalesID");
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving last sales ID: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get last sales ID: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return lastOrderID;
    }

    //get Selected Product Qty
    public int getSelectedProductQty(int productID) {
        int qty = 0;

        String QUERY = "select qty from products where products.id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, productID);

            result = preparedStatement.executeQuery();
            while (result.next()) {

                qty = result.getInt("qty");
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving Selected Product Qty: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get Selected Product Qty: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return qty;
    }

    
    //get staff department
    public String getDepartmentByID(int deptID){
        String dept = "";

        String QUERY = "select department_desc from department where department_id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, deptID);

            result = preparedStatement.executeQuery();
            while (result.next()) {

                dept = result.getString("department_desc");
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving Selected department: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get department: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return dept;
    }
    
    //get a product's image
    public String getProductImagePath(int productID){
        String imagePath = "";

        String QUERY = "select image from products where id = ?";

        ResultSet result = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, productID);

            result = preparedStatement.executeQuery();
            while (result.next()) {

                imagePath = result.getString("image");
            }

        } catch (SQLException ex) {
            System.out.println("Error while retrieving product image: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error while closing ResultSet resource for get product image: " + ex.getMessage());
                ex.printStackTrace();
            }

        }

        return imagePath;
    }
}
