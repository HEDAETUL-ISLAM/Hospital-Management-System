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
public class Appointment {
    
    private String appointNo;
    private String doctorId;
    private String patientId;
    private String type;
    private LocalDate appointDate;

    public Appointment() {
    }

    public Appointment(String appointNo, String doctorId, String patientId, String type, LocalDate appointDate) {
        this.appointNo = appointNo;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.type = type;
        this.appointDate = appointDate;
    }

    public String getAppointNo() {
        return appointNo;
    }

    public void setAppointNo(String appointNo) {
        this.appointNo = appointNo;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(LocalDate appointDate) {
        this.appointDate = appointDate;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointNo=" + appointNo + ", doctorId=" + doctorId + ", patientId=" + patientId + ", type=" + type + ", appointDate=" + appointDate + '}';
    }
    
}
