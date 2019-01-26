/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hedaetul
 */
public class Admin extends Person{
    
    private String adminPassword;

    public Admin() {
    }

    public Admin(String adminPassword, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.adminPassword = adminPassword;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "Admin{" + "adminPassword=" + adminPassword + '}';
    }
    
    
    
}
