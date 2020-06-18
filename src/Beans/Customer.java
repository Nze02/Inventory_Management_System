/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class Customer {
    private int customerID;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerEmail;
    private double totalPurchase;

    public Customer(int customerID, String customerFirstName, String customerLastName, String customerPhone, String customerEmail, double totalPurchase) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.totalPurchase = totalPurchase;
    }

    public Customer(int customerID, String customerFirstName, String customerLastName, String customerPhone, String customerEmail) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    public Customer(String customerFirstName, String customerLastName, String customerPhone, String customerEmail) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
    
}
