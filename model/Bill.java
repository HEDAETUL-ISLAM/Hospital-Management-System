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
public class Bill {
    
    private String billNo;
    private String patientId;
    private double doctorCharge;
    private double medicineCharge;
    private double roomCharge;
    private double operationCharge;
    private int noOfDays;
    private double healthCard;
    private double labCharge;
    private double advancePay;
    private double totalBill; 
    private double due;

    public Bill() {
    }

    public Bill(String billNo, String patientId, double doctorCharge, double medicineCharge, double roomCharge, double operationCharge, int noOfDays, double healthCard, double labCharge, double advancePay) {
        this.billNo = billNo;
        this.patientId = patientId;
        this.doctorCharge = doctorCharge;
        this.medicineCharge = medicineCharge;
        this.roomCharge = roomCharge;
        this.operationCharge = operationCharge;
        this.labCharge = labCharge;
        this.noOfDays = noOfDays;
        this.healthCard = healthCard;
        this.advancePay = advancePay;
        this.totalBill = ((doctorCharge+medicineCharge+roomCharge+operationCharge+labCharge)*noOfDays)-((doctorCharge+medicineCharge+roomCharge+operationCharge+labCharge)*noOfDays)*(healthCard/100);
        this.due = totalBill-advancePay;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getDoctorCharge() {
        return doctorCharge;
    }

    public void setDoctorCharge(double doctorCharge) {
        this.doctorCharge = doctorCharge;
    }

    public double getMedicineCharge() {
        return medicineCharge;
    }

    public void setMedicineCharge(double medicineCharge) {
        this.medicineCharge = medicineCharge;
    }

    public double getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(double roomCharge) {
        this.roomCharge = roomCharge;
    }

    public double getOperationCharge() {
        return operationCharge;
    }

    public void setOperationCharge(double operationCharge) {
        this.operationCharge = operationCharge;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public double getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(double healthCard) {
        this.healthCard = healthCard;
    }

    public double getLabCharge() {
        return labCharge;
    }

    public void setLabCharge(double labCharge) {
        this.labCharge = labCharge;
    }

    public double getAdvancePay() {
        return advancePay;
    }

    public void setAdvancePay(double advancePay) {
        this.advancePay = advancePay;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }

    @Override
    public String toString() {
        return "Bill{" + "billNo=" + billNo + ", patientId=" + patientId + ", doctorCharge=" + doctorCharge + ", medicineCharge=" + medicineCharge + ", roomCharge=" + roomCharge + ", operationCharge=" + operationCharge + ", noOfDays=" + noOfDays + ", healthCard=" + healthCard + ", labCharge=" + labCharge + ", advancePay=" + advancePay + ", totalBill=" + totalBill + ", due=" + due + '}';
    }
    

    
    
}
