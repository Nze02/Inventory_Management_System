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
public class Products {

    private int productID;
    private String barcode;
    private String category;
    private String name;
    private double costPrice;
    private int quantity;
    private Date date;
    private String description;
    private String purchasedFrom;
    private String image;

    public Products(int productID, String barcode, String category, String name, double costPrice, int quantity, Date date, String description, String purchasedFrom, String image) {
        this.productID = productID;
        this.barcode = barcode;
        this.category = category;
        this.name = name;
        this.costPrice = costPrice;
        this.quantity = quantity;
        this.date = date;
        this.description = description;
        this.purchasedFrom = purchasedFrom;
        this.image = image;
    }

    public Products(String barcode, String category, String name, double costPrice, int quantity, Date date, String description, String purchasedFrom, String image) {
        this.barcode = barcode;
        this.category = category;
        this.name = name;
        this.costPrice = costPrice;
        this.quantity = quantity;
        this.date = date;
        this.description = description;
        this.purchasedFrom = purchasedFrom;
        this.image = image;
    }

    public Products(int productID, String barcode, String name, double costPrice, int quantity, String description, String image) {
        this.productID = productID;
        this.barcode = barcode;
        this.name = name;
        this.costPrice = costPrice;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
    }

    public Products(int productID, String barcode, String category, String name, double costPrice, int quantity, Date date, String description, String purchasedFrom) {
        this.productID = productID;
        this.barcode = barcode;
        this.category = category;
        this.name = name;
        this.costPrice = costPrice;
        this.quantity = quantity;
        this.date = date;
        this.description = description;
        this.purchasedFrom = purchasedFrom;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurchasedFrom() {
        return purchasedFrom;
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
