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
public class Ambulance {
    
    private String ambulanceNo;
    private String driverName;

    public Ambulance() {
    }

    public Ambulance(String ambulanceNo, String driverName) {
        this.ambulanceNo = ambulanceNo;
        this.driverName = driverName;
    }

    public String getAmbulanceNo() {
        return ambulanceNo;
    }

    public void setAmbulanceNo(String ambulanceNo) {
        this.ambulanceNo = ambulanceNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "Ambulance{" + "ambulanceNo=" + ambulanceNo + ", driverName=" + driverName + '}';
    }
    
    
}
