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
import model.OutPatient;

/**
 *
 * @author hedaetul
 */
public class OutPatientController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertOutPatient(OutPatient outpatient) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into out_patient (bill, lab_no,person_id,person_name,person_address,person_phone) values ('%f','%s','%s','%s','%s','%s')",outpatient.getBill(),outpatient.getLabNo(),outpatient.getPersonId(),outpatient.getPersonName(),outpatient.getPersonAddress(),outpatient.getPersonPhone());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }   
    public OutPatient getOutPatient(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from out_patient where person_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            OutPatient outpatient = new OutPatient(rs.getDouble("bill"),rs.getString("lab_no"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            return outpatient;
        }
        else{
            return null;
        }
    }
    
    public boolean updateOutPatient(OutPatient outpatient) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update out_patient set bill='%f', lab_no='%s',person_name='%s',person_address='%s',person_phone='%s' where person_id='%s'",outpatient.getBill(),outpatient.getLabNo(),outpatient.getPersonName(),outpatient.getPersonAddress(),outpatient.getPersonPhone(),outpatient.getPersonId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteOutPatient(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from out_patient where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<OutPatient> getAllOutPatient() throws SQLException{
        ObservableList<OutPatient> outpatientList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from out_patient";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            OutPatient outpatient = new OutPatient(rs.getDouble("bill"),rs.getString("lab_no"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            outpatientList.add(outpatient);
        }
        return outpatientList;
    }
    
   
    
}
