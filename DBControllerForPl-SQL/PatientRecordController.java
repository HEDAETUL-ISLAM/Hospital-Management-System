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
import model.PatientRecord;

/**
 *
 * @author hedaetul
 */
public class PatientRecordController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertPatientRecord(PatientRecord patientrecord) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
                                                                                                                                                                                            //String patientId, String doctorName, String doctorId, String patientName, LocalDate dateOfDischarge, LocalDate dateOfAdmit, String problem
        String query = String.format("insert into patient_record (patient_id,doctor_name, doctor_id,patient_name, date_of_discharge,date_of_admit, problem) values ('%s','%s','%s','%s','%s','%s','%s')",patientrecord.getPatientId(),patientrecord.getDoctorName(),patientrecord.getDoctorId(),patientrecord.getPatientName(),patientrecord.getDateOfDischarge(),patientrecord.getDateOfAdmit(),patientrecord.getProblem());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }   
    public PatientRecord getpatientrecord(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from patient_record where patient_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            PatientRecord patientrecord = new PatientRecord(rs.getString("patient_id"), rs.getString("doctor_name"), rs.getString("doctor_id"), rs.getString("patient_name"), rs.getDate("date_of_discharge").toLocalDate(),rs.getDate("date_of_admit").toLocalDate(),rs.getString("problem"));
            return patientrecord;
        }
        else{
            return null;
        }
    }
    
    public boolean updatePatientRecord(PatientRecord patientrecord) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update patient_record set doctor_name='%s',doctor_id='%s' ,patient_name='%s',date_of_discharge='%s',date_of_admit='%s',problem='%s'  where patient_id='%s'",patientrecord.getDoctorName(),patientrecord.getDoctorId(),patientrecord.getPatientName(),patientrecord.getDateOfDischarge(),patientrecord.getDateOfAdmit(),patientrecord.getProblem(),patientrecord.getPatientId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deletePatientRecord(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete  from patient_record where patient_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<PatientRecord> getAllPatientRecord() throws SQLException{
        ObservableList<PatientRecord> patientrecordList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from patient_record";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            PatientRecord patientrecord = new PatientRecord(rs.getString("patient_id"), rs.getString("doctor_name"), rs.getString("doctor_id"), rs.getString("patient_name"), rs.getDate("date_of_discharge").toLocalDate(),rs.getDate("date_of_admit").toLocalDate(),rs.getString("problem"));
            patientrecordList.add(patientrecord);
        }
        return patientrecordList;
    }
    
   
    
}
