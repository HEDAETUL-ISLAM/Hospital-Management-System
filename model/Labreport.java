/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author hedaetul
 */
public class Labreport {
    
    private String labNo;
    private String patientId;
    private String doctorId;
    private String patientType;
    private double amount;
    private LocalDate testDate;

    public Labreport() {
    }

    public Labreport(String labNo, String patientId, String doctorId, String patientType, double amount, LocalDate testDate) {
        this.labNo = labNo;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientType = patientType;
        this.amount = amount;
        this.testDate = testDate;
    }

    public String getLabNo() {
        return labNo;
    }

    public void setLabNo(String labNo) {
        this.labNo = labNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }
    
    
    
    

   
    @Override
    public String toString() {
        return "Labreport{" + "labNo=" + labNo + ", patientId=" + patientId + ", doctorId=" + doctorId + ", patientType=" + patientType + ", amount=" + amount + ", testDate=" + testDate + '}';
    }
    
    
    
}
