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
public class StaffDepartment {
    private int deptID;
    private String department;

    public StaffDepartment() {
    }

    public StaffDepartment(String department) {
        this.department = department;
    }

    public StaffDepartment(int deptID, String department) {
        this.deptID = deptID;
        this.department = department;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    
}
