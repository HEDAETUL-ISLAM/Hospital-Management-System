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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OperationTheater;

/**
 *
 * @author hedaetul
 */
public class OperationTheaterController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertOperationTheater(OperationTheater operationtheater) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("insert into operation_theater (room_no, patient_name,doctor_name) values ('%d','%s','%s')",operationtheater.getRoomNo(),operationtheater.getPatientName(),operationtheater.getDoctorName());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public OperationTheater getOperationTheater(Integer no) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from operation_theater where room_no='"+no+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            OperationTheater operationtheater = new OperationTheater(rs.getInt("room_no"), rs.getString("patient_name"), rs.getString("doctor_name"));
            return operationtheater;
        }
        else{
            return null;
        }
    }
    
    public boolean updateOperationController(OperationTheater operationtheater) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update operation_theater set patient_name='%s',doctor_name='%s' where room_no='%d'",operationtheater.getPatientName(),operationtheater.getDoctorName(),operationtheater.getRoomNo());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteOperationTheater(Integer no) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete  from operation_theater where room_no='"+no+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<OperationTheater> getAllOperationTheater() throws SQLException{
        ObservableList<OperationTheater> operationtheaterList =FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from operation_theater";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            OperationTheater operationtheater = new OperationTheater(rs.getInt("room_no"), rs.getString("patient_name"), rs.getString("doctor_name"));
            operationtheaterList.add(operationtheater);
        }
        return operationtheaterList;
    }
    
   
    
}
