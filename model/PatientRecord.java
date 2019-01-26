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
public class PatientRecord {
    
    private String patientId;
    private String doctorName;
    private String doctorId;
    private String patientName;
    private LocalDate dateOfDischarge;
    private LocalDate dateOfAdmit;
    private String problem;

    public PatientRecord() {
    }

    public PatientRecord(String patientId, String doctorName, String doctorId, String patientName, LocalDate dateOfDischarge, LocalDate dateOfAdmit, String problem) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.dateOfDischarge = dateOfDischarge;
        this.dateOfAdmit = dateOfAdmit;
        this.problem = problem;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public LocalDate getDateOfAdmit() {
        return dateOfAdmit;
    }

    public void setDateOfAdmit(LocalDate dateOfAdmit) {
        this.dateOfAdmit = dateOfAdmit;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return "PatientRecord{" + "patientId=" + patientId + ", doctorName=" + doctorName + ", doctorId=" + doctorId + ", patientName=" + patientName + ", dateOfDischarge=" + dateOfDischarge + ", dateOfAdmit=" + dateOfAdmit + ", problem=" + problem + '}';
    }
    
    
    
}
