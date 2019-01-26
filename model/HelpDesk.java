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
public class HelpDesk extends Person{
    
    private String employeeType;
    private String employeeGender;
    private String helpDeskPassword;

    public HelpDesk() {
    }

    public HelpDesk(String employeeType, String employeeGender, String helpDeskPassword, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.employeeType = employeeType;
        this.employeeGender = employeeGender;
        this.helpDeskPassword = helpDeskPassword;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getHelpDeskPassword() {
        return helpDeskPassword;
    }

    public void setHelpDeskPassword(String helpDeskPassword) {
        this.helpDeskPassword = helpDeskPassword;
    }

    @Override
    public String toString() {
        return "HelpDesk{" + "employeeType=" + employeeType + ", employeeGender=" + employeeGender + ", helpDeskPassword=" + helpDeskPassword + '}';
    }

    
    
}
