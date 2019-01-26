/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbController;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.time.*;
import oracle.sql.*;
import oracle.jdbc.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.Date;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 *
 * @author hedaetul
 */
public class AppointmentController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertAppointment(Appointment appointment) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
       String query = String.format("insert into appointment (appoint_no, doctor_id, patient_id, type,appoint_date) values ('%s','%s','%s','%s','%t')",appointment.getAppointNo(),appointment.getDoctorId(),appointment.getPatientId(),appointment.getType(),LocalDate.now());
       if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }

    public int getAppointment() throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = "select   appoint_no.nextval from dual";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            return rs.getInt(1);

        }
        else{
            return -1;
        }
        
    }
    
    public boolean updateAppointment(Appointment appointment) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update appointment set doctor_id='%s', patient_id='%s', type='%s',",appointment.getDoctorId(),appointment.getPatientId(),appointment.getType());
         String query2=String.format(" where appoint_no = '%s'",appointment.getAppointNo());
        String q="appoint_date=";
        String query3=" STR_TO_DATE('"+appointment.getAppointDate()+"', '%Y-%m-%d')";
        
        query+=q+query3;
        query+=query2;
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean deleteAppointment(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from appointment where appoint_no='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public ObservableList<Appointment> getAllAppointment() throws SQLException{
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from appointment";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Appointment appointment = new Appointment(rs.getString("appoint_no"),rs.getString("doctor_id"),rs.getString("patient_id"),rs.getString("type"),rs.getDate("appoint_date").toLocalDate());
            appointmentsList.add(appointment);
        }
        return appointmentsList;
        
        
    }
}
