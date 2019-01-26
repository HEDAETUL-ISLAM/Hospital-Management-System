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
public class Hospital {
    private String hospitalName;
    private String hospitalCode;
    private String hospitalAddress;

    public Hospital() {
    }

    public Hospital(String hospitalName, String hospitalCode, String hospitalAddress) {
        this.hospitalName = hospitalName;
        this.hospitalCode = hospitalCode;
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    @Override
    public String toString() {
        return "Hospital{" + "hospitalName=" + hospitalName + ", hospitalCode=" + hospitalCode + ", hospitalAddress=" + hospitalAddress + '}';
    }
    
    
    
}
