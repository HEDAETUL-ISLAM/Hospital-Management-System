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
public class Doctor extends Person{
    
    private String doctorDepartment;
    private String doctorQualification;
    private String doctorGender;

    public Doctor() {
    }

    public Doctor(String doctorDepartment, String doctorQualification, String doctorGender, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.doctorDepartment = doctorDepartment;
        this.doctorQualification = doctorQualification;
        this.doctorGender = doctorGender;
    }

    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(String doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }

    public String getDoctorQualification() {
        return doctorQualification;
    }

    public void setDoctorQualification(String doctorQualification) {
        this.doctorQualification = doctorQualification;
    }

    public String getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctorDepartment=" + doctorDepartment + ", doctorQualification=" + doctorQualification + ", doctorGender=" + doctorGender + '}';
    }

    
}
