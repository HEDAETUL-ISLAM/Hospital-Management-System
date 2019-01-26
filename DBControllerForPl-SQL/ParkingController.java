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
import model.Parking;

/**
 *
 * @author faisa
 */
public class ParkingController {
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertRoom(Parking parking) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("insert into parking (car_no, in_time,out_time) values ('%s','%s','%s')",parking.getCarNo(),parking.getInTime(),parking.getOutTime());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    } 
    public Parking getParking(String no) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from parking where car_no='"+no+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            Parking parking = new Parking(rs.getString("car_no"),rs.getTimestamp("in_time"),rs.getTimestamp("out_time"));
            return parking;
        }
        else{
            return null;
        }
    }
    
    public boolean updateParking(Parking parking) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update parking set in_time='%s',out_time='%s' where car_no='%s'",parking.getInTime(),parking.getOutTime(),parking.getCarNo());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteParking(String no) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete   from parking where car_no='"+no+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<Parking> getAllParking() throws SQLException{
        ObservableList<Parking> parkingList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from parking";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Parking parking = new Parking(rs.getString("car_no"),rs.getTimestamp("in_time"),rs.getTimestamp("out_time"));
            parkingList.add(parking);
        }
        return parkingList;
    }
    
}
