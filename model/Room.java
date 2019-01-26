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
public class Room {
    
    private int roomNo;
    private String status;
    private String roomType;

    public Room() {
    }

    public Room(int roomNo, String status, String roomType) {
        this.roomNo = roomNo;
        this.status = status;
        this.roomType = roomType;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" + "roomNo=" + roomNo + ", status=" + status + ", roomType=" + roomType + '}';
    }

    
    
    

}
