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
public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private String department;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String status;

    public Staff() {
    }

    public Staff(String firstName, String lastName, String department, String phone, String email, String username, String password, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Staff(int id, String firstName, String lastName, String department, String phone, String email, String username, String password, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
}
