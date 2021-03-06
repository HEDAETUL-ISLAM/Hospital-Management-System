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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InPatient;
import view.FXML_MortuaryController;

/**
 *
 * @author hedaetul
 */
public class InPatientController {
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    
    public boolean insertInPatient(InPatient inPatient) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
          String query = String.format("insert into in_patient (date_of_discharge, room_no, lab_no, date_of_admit,person_id,person_name,person_address,person_phone) values('%s','%d','%s','%s','%s','%s','%s','%s')", inPatient.getDateOfDischarge(),inPatient.getRoomNo(),inPatient.getLabNo(),inPatient.getDateOfAdmit(),inPatient.getPersonId(),inPatient.getPersonName(),inPatient.getPersonAddress(),inPatient.getPersonPhone());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public InPatient getInPatient(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from in_patient where person_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            InPatient inPatient = new InPatient(rs.getDate("date_of_discharge").toLocalDate(),rs.getInt("room_no"),rs.getString("lab_no"),rs.getDate("date_of_admit").toLocalDate(),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
                    return inPatient;
        }
        else{
            return null;
        }
    }
    
    public boolean updateInPatient(InPatient inPatient) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update in_patient set date_of_discharge='%s', room_no='%d', lab_no='%s', date_of_admit='%s',person_name='%s',person_address='%s',person_phone='%s'  where person_id='%s'", inPatient.getDateOfDischarge(),inPatient.getRoomNo(),inPatient.getLabNo(),inPatient.getDateOfAdmit(),inPatient.getPersonName(),inPatient.getPersonAddress(),inPatient.getPersonPhone(),inPatient.getPersonId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public boolean deleteInPatient(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from in_patient where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
     public ObservableList<InPatient> getAllInPatient() throws SQLException{
        ObservableList<InPatient> inPatientList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from in_patient";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            InPatient inPatient = new InPatient(rs.getDate("date_of_discharge").toLocalDate(),rs.getInt("room_no"),rs.getString("lab_no"),rs.getDate("date_of_admit").toLocalDate(),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            inPatientList.add(inPatient);
        }
        return inPatientList;
    }
    
    
}
