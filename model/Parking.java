/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author hedaetul
 */
public class Parking {
    
    private String carNo;
    private Timestamp inTime;
    private Timestamp outTime;

    public Parking() {
    }

    public Parking(String carNo, Timestamp inTime, Timestamp outTime) {
        this.carNo = carNo;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "Parking{" + "carNo=" + carNo + ", inTime=" + inTime + ", outTime=" + outTime + '}';
    }
    
    
}
