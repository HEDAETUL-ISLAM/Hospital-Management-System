/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Room;

/**
 *
 * @author hedaetul
 */
public class RoomController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    
    public boolean insertRoom(Room room) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into room (room_no,status,room_type) values ('%d','%s','%s')",room.getRoomNo(),room.getStatus(),room.getRoomType());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    } 
    public Room getRoom(Integer no) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from room where room_no='"+no+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            Room room = new Room(rs.getInt("room_no"), rs.getString("status"), rs.getString("room_type"));
            return room;
        }
        else{
            return null;
        }
    }
    
    public boolean updateRoom(Room room) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update room set status='%s',room_type='%s' where room_no='%d'", room.getStatus(),room.getRoomType(),room.getRoomNo());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteRoom(Integer no) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete  from room where room_no='"+no+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<Room> getAllRoom() throws SQLException{
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from room";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Room room = new Room(rs.getInt("room_no"), rs.getString("status"), rs.getString("room_type"));
            roomList.add(room);
        }
        return roomList;
    }
    
   
    
}
