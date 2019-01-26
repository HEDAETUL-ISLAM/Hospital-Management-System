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
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Labreport;

/**
 *
 * @author hedaetul
 */
public class LabReportController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    
    public boolean insertLabreport(Labreport labreport) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("insert into labreport (lab_no,patient_id, doctor_id,patient_type,amount,test_date) values ('%s','%s','%s','%s','%f','%s')",labreport.getLabNo(),labreport.getPatientId(),labreport.getDoctorId(),labreport.getPatientType(),labreport.getAmount(),LocalDate.now());

        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    } 
    public Labreport getlabreport(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from labreport where patient_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            Labreport labreport = new Labreport(rs.getString("lab_no"),rs.getString("patient_id"), rs.getString("doctor_id"), rs.getString("patient_type"),rs.getDouble("amount"),rs.getDate("test_date").toLocalDate());
            return labreport;
        }
        else{
            return null;
        }
    }
    
    public boolean updateLabreport(Labreport labreport) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update labreport set patient_id='%s',doctor_id='%s',patient_type='%s',amount='%f',test_date='%s' where lab_no='%s'",labreport.getPatientId(),labreport.getDoctorId(),labreport.getPatientType(),labreport.getAmount(),LocalDate.now(),labreport.getLabNo());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteLabreport(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from labreport where lab_no='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<Labreport> getAllLabreport() throws SQLException{
        ObservableList<Labreport> labreportList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from labreport";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Labreport labreport = new Labreport(rs.getString("lab_no"),rs.getString("patient_id"), rs.getString("doctor_id"), rs.getString("patient_type"),rs.getDouble("amount"),rs.getDate("test_date").toLocalDate());
            labreportList.add(labreport);
        }
        
        return labreportList;
    }
    
   
    
}
