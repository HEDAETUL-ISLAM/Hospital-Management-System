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
import model.Patient;

/**
 *
 * @author hedaetul
 */
public class PatientController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    
    public boolean insertPatient(Patient patient) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("insert into patient (patient_doctor,patient_weight, patient_age,patient_gender,patient_appoint_no,patient_status,patient_disease,person_id,person_name, person_address,person_phone) values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",patient.getPatientDoctor(),patient.getPatientWeight(),patient.getPatientAge(),patient.getPatientGender(),patient.getPatientAppointNo(),patient.getPatientStatus(),patient.getPatientDisease(),patient.getPersonId(),patient.getPersonName(),patient.getPersonAddress(),patient.getPersonPhone());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
  
    public Patient getpatient(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from patient where person_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            Patient patient = new Patient(rs.getString("patient_doctor"),rs.getString("patient_weight"),rs.getString("patient_age"),rs.getString("patient_gender"),rs.getString("patient_appoint_no"),rs.getString("patient_status"),rs.getString("patient_disease"),rs.getString("person_id"), rs.getString("person_name"), rs.getString("person_address"), rs.getString("person_phone"));
            return patient;
        }
        else{
            return null;
        }
    }
    
    public boolean updatePatient(Patient patient) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update patient set patient_doctor='%s',patient_weight='%s', patient_age='%s',patient_gender='%s',patient_appoint_no='%s',patient_status='%s',patient_disease='%s',person_name='%s',person_address='%s' ,person_phone='%s'  where person_id='%s'",patient.getPatientDoctor(),patient.getPatientWeight(),patient.getPatientAge(),patient.getPatientGender(),patient.getPatientAppointNo(),patient.getPatientStatus(),patient.getPatientDisease(),patient.getPersonName(),patient.getPersonAddress(),patient.getPersonPhone(),patient.getPersonId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deletePatient(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete  from patient where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<Patient> getAllPatient() throws SQLException{
        ObservableList<Patient> patientList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from patient";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Patient patient = new Patient( rs.getString("patient_doctor"),rs.getString("patient_weight"),rs.getString("patient_age"),rs.getString("patient_gender"),rs.getString("patient_appoint_no"),rs.getString("patient_status"),rs.getString("patient_disease"),rs.getString("person_id"), rs.getString("person_name"), rs.getString("person_address"), rs.getString("person_phone"));
            patientList.add(patient);
        }
        return patientList;
    }
    
   
    
}
