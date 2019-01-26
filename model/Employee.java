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
public class Employee extends Person{
    
    private String employeeGender;
    private String employeeType;
    private Double employeeSalary;

    public Employee() {
    }

    public Employee(String employeeGender, String employeeType, Double employeeSalary, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.employeeGender = employeeGender;
        this.employeeType = employeeType;
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeGender=" + employeeGender + ", employeeType=" + employeeType + ", employeeSalary=" + employeeSalary + '}';
    }
    
    
}
