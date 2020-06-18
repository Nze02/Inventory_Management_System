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
public class SalesBreakdown {
    private int salesBreakdownID;
    private int salesID;
    private int productID;
    private String productName;
    private double salesPrice;
    private int quantity;
    private double quantityXprice;

    public SalesBreakdown(int salesID, String productName, int productID, double salesPrice, int quantity, double quantityXprice) {
        this.salesID = salesID;
        this.productID = productID;
        this.productName = productName;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.quantityXprice = quantityXprice;
    }

    public SalesBreakdown(int salesBreakdownID, int salesID, String productName, double salesPrice, int quantity, double quantityXprice) {
        this.salesBreakdownID = salesBreakdownID;
        this.salesID = salesID;
        this.productName = productName;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.quantityXprice = quantityXprice;
    }

    public int getSalesBreakdownID() {
        return salesBreakdownID;
    }

    public void setSalesBreakdownID(int salesBreakdownID) {
        this.salesBreakdownID = salesBreakdownID;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getQuantityXprice() {
        return quantityXprice;
    }

    public void setQuantityXprice(double quantityXprice) {
        this.quantityXprice = quantityXprice;
    }
    
    
}
