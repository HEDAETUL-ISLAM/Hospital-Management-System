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
public class Mortuary {

    private int roomNo;
    private String patientId;
    private String patientName;
    private String doctorName;
    private String problem;
    private LocalDate dateOfDead;

    public Mortuary() {
    }

    public Mortuary(int roomNo, String patientId, String patientName, String doctorName, String problem, LocalDate dateOfDead) {
        this.roomNo = roomNo;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.problem = problem;
        this.dateOfDead = dateOfDead;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public LocalDate getDateOfDead() {
        return dateOfDead;
    }

    public void setDateOfDead(LocalDate dateOfDead) {
        this.dateOfDead = dateOfDead;
    }

    @Override
    public String toString() {
        return "Mortuary{" + "roomNo=" + roomNo + ", patientId=" + patientId + ", patientName=" + patientName + ", doctorName=" + doctorName + ", problem=" + problem + ", dateOfDead=" + dateOfDead + '}';
    }



}
