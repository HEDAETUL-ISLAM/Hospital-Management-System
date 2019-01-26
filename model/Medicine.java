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
public class Medicine {
    
    private String medicineId;
    private String medicineName;
    private String medicineType;
    private String medicineCompany;
    private Double medicineCost;

    public Medicine() {
    }

    public Medicine(String medicineId, String medicineName, String medicineType, String medicineCompany, Double medicineCost) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.medicineCompany = medicineCompany;
        this.medicineCost = medicineCost;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineCompany() {
        return medicineCompany;
    }

    public void setMedicineCompany(String medicineCompany) {
        this.medicineCompany = medicineCompany;
    }

    public Double getMedicineCost() {
        return medicineCost;
    }

    public void setMedicineCost(Double medicineCost) {
        this.medicineCost = medicineCost;
    }

    @Override
    public String toString() {
        return "Medicine{" + "medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineType=" + medicineType + ", medicineCompany=" + medicineCompany + ", medicineCost=" + medicineCost + '}';
    }
    
    
}
