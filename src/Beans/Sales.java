/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Date;

/**
 *
 * @author Emmanuel Nzekwe
 */
public class Sales {
    private int salesID;
    private int custID;
    private int staffID;
    private Date salesDate;
    private double amount;
    private String soldFrom;
    private String salesMode;
    private int paymentMode;
    private String deliveryStatus;
    
    private String customerFName;
    private String customerLName;
    private String staffFName;
    private String staffLName;

    public Sales() {
    }

    public Sales(int custID, int staffID, Date salesDate, double amount, String soldFrom, String salesMode, int paymentMode, String deliveryStatus) {
        this.custID = custID;
        this.staffID = staffID;
        this.salesDate = salesDate;
        this.amount = amount;
        this.soldFrom = soldFrom;
        this.salesMode = salesMode;
        this.paymentMode = paymentMode;
        this.deliveryStatus = deliveryStatus;
    }

    public Sales(int salesID, int custID, int staffID, Date salesDate, double amount, String soldFrom, String salesMode, int paymentMode, String deliveryStatus) {
        this.salesID = salesID;
        this.custID = custID;
        this.staffID = staffID;
        this.salesDate = salesDate;
        this.amount = amount;
        this.soldFrom = soldFrom;
        this.salesMode = salesMode;
        this.paymentMode = paymentMode;
        this.deliveryStatus = deliveryStatus;
    }
    
    public Sales(int salesID, Date salesDate, double amount, String soldFrom, String salesMode, int paymentMode, String deliveryStatus, String customerFName, String customerLName, String staffFName, String staffLName) {
        this.salesID = salesID;
        this.salesDate = salesDate;
        this.amount = amount;
        this.soldFrom = soldFrom;
        this.salesMode = salesMode;
        this.paymentMode = paymentMode;
        this.deliveryStatus = deliveryStatus;
        this.customerFName = customerFName;
        this.customerLName = customerLName;
        this.staffFName = staffFName;
        this.staffLName = staffLName;
    }
    
    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSoldFrom() {
        return soldFrom;
    }

    public void setSoldFrom(String soldFrom) {
        this.soldFrom = soldFrom;
    }

    public String getSalesMode() {
        return salesMode;
    }

    public void setSalesMode(String salesMode) {
        this.salesMode = salesMode;
    }

    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public String getStaffFName() {
        return staffFName;
    }

    public void setStaffFName(String staffFName) {
        this.staffFName = staffFName;
    }

    public String getStaffLName() {
        return staffLName;
    }

    public void setStaffLName(String staffLName) {
        this.staffLName = staffLName;
    }

}
