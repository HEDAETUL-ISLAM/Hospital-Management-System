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
public class OperationTheater {

    private int roomNo;
    private String patientName;
    private String doctorName;

    public OperationTheater() {
    }

    public OperationTheater(int roomNo, String patientName, String doctorName) {
        this.roomNo = roomNo;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
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

    @Override
    public String toString() {
        return "OperationTheater{" + "roomNo=" + roomNo + ", patientName=" + patientName + ", doctorName=" + doctorName + '}';
    }


}
