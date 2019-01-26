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
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import oracle.sql.*;
import oracle.jdbc.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Doctor;

/**
 *
 * @author FAISAL
 */
public class DoctorController {
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertDoctor(Doctor doctor) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        CallableStatement stmt = null;
        stmt = conn.prepareCall("{call HOSPITAL1.NEW_DOCTOR(?,?,?,?,?,?,?)}");

        String id = doctor.getPersonId();
        String add = doctor.getPersonAddress();
        String name = doctor.getPersonName();
        String phone = doctor.getPersonPhone();
        String department = doctor.getDoctorDepartment();
        String gender = doctor.getDoctorGender();
        String qualification = doctor.getDoctorQualification();
        stmt.setString(1,id );
         stmt.setString(2, add);
         stmt.setString(3,name );
         stmt.setString(4,phone );
         stmt.setString(5,department );
        stmt.setString(6,gender );
        stmt.setString(7,qualification );

        if(stmt.executeUpdate()==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Doctor getDoctor(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from doctor where person_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        if(rs.next()){
            Doctor doctor = new Doctor( rs.getString("doctor_department"),rs.getString("doctor_qualification"),rs.getString("doctor_gender"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            return doctor;
        }
        else{
            return null;
        }
    }
    
    public boolean updateDoctor(Doctor doctor) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("update doctor set  person_address='%s',person_name='%s',person_phone='%s',doctor_department='%s',doctor_qualification='%s',doctor_gender='%s' where person_id='%s'",doctor.getPersonAddress(),doctor.getPersonName(),doctor.getPersonPhone(),doctor.getDoctorDepartment(),doctor.getDoctorQualification(),doctor.getDoctorGender(),doctor.getPersonId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean deleteDoctor(String id) throws SQLException{
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query="delete from doctor where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public ObservableList<Doctor> getAllDoctor() throws SQLException{
    ObservableList<Doctor> doctorList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from Deptartment_Doctor";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Doctor doctor = new Doctor(rs.getString("doctor_department"),rs.getString("doctor_qualification"),rs.getString("doctor_gender"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            doctorList.add(doctor);
        }
        return doctorList;
    }
    
}
