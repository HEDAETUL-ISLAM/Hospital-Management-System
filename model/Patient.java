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
public class Patient extends Person{
    
    private String patientDoctor;
    private String patientWeight;
    private String patientAge;
    private String patientGender;
    private String patientAppointNo;
    private String patientStatus;
    private String patientDisease;

    public Patient() {
    }

    public Patient(String patientDoctor, String patientWeight, String patientAge, String patientGender, String patientAppointNo, String patientStatus, String patientDisease, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.patientDoctor = patientDoctor;
        this.patientWeight = patientWeight;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientAppointNo = patientAppointNo;
        this.patientStatus = patientStatus;
        this.patientDisease = patientDisease;
    }

    public String getPatientDoctor() {
        return patientDoctor;
    }

    public void setPatientDoctor(String patientDoctor) {
        this.patientDoctor = patientDoctor;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(String patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAppointNo() {
        return patientAppointNo;
    }

    public void setPatientAppointNo(String patientAppointNo) {
        this.patientAppointNo = patientAppointNo;
    }

    public String getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(String patientStatus) {
        this.patientStatus = patientStatus;
    }

    public String getPatientDisease() {
        return patientDisease;
    }

    public void setPatientDisease(String patientDisease) {
        this.patientDisease = patientDisease;
    }

    @Override
    public String toString() {
        return "Patient{" + "patientDoctor=" + patientDoctor + ", patientWeight=" + patientWeight + ", patientAge=" + patientAge + ", patientGender=" + patientGender + ", patientAppointNo=" + patientAppointNo + ", patientStatus=" + patientStatus + ", patientDisease=" + patientDisease + '}';
    }

    
    
    
    
}
