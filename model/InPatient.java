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
public class InPatient extends Person{
    
    private LocalDate dateOfDischarge;
    private int roomNo;
    private String labNo;
    private LocalDate dateOfAdmit;

    public InPatient() {
    }

    public InPatient(LocalDate dateOfDischarge, int roomNo, String labNo, LocalDate dateOfAdmit, String personId, String personName, String personAddress, String personPhone) {
        super(personId, personName, personAddress, personPhone);
        this.dateOfDischarge = dateOfDischarge;
        this.roomNo = roomNo;
        this.labNo = labNo;
        this.dateOfAdmit = dateOfAdmit;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getLabNo() {
        return labNo;
    }

    public void setLabNo(String labNo) {
        this.labNo = labNo;
    }

    public LocalDate getDateOfAdmit() {
        return dateOfAdmit;
    }

    public void setDateOfAdmit(LocalDate dateOfAdmit) {
        this.dateOfAdmit = dateOfAdmit;
    }

    @Override
    public String toString() {
        return "InPatient{" + "dateOfDischarge=" + dateOfDischarge + ", roomNo=" + roomNo + ", labNo=" + labNo + ", dateOfAdmit=" + dateOfAdmit + '}';
    }
    

    
    
    
}
