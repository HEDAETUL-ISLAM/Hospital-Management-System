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
public class OutPatient extends Person{
    
    private double bill;
    private String labNo;

    public OutPatient() {
    }

    public OutPatient(double bill, String labNo, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.bill = bill;
        this.labNo = labNo;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getLabNo() {
        return labNo;
    }

    public void setLabNo(String labNo) {
        this.labNo = labNo;
    }

    @Override
    public String toString() {
        return "OutPatient{" + "bill=" + bill + ", labNo=" + labNo + '}';
    }

    
    
    
}
