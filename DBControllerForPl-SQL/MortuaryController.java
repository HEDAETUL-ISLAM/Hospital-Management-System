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
import model.Mortuary;

/**
 *
 * @author hedaetul
 */
public class MortuaryController {

    Connection getConnection() throws SQLException{

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }

    public boolean insertMortuary(Mortuary mortuary) throws SQLException{

        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("insert into mortuary (room_no,patient_id, patient_name,doctor_name,problem,date_of_dead) values ('%d','%s','%s','%s','%s','%s')",mortuary.getRoomNo(),mortuary.getPatientId(),mortuary.getPatientName(),mortuary.getDoctorName(),mortuary.getProblem(),mortuary.getDateOfDead());

        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public Mortuary getMortuary(Integer no) throws SQLException{

        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from mortuary where room_no='"+no+"'";
        ResultSet rs=statement.executeQuery(query);

        if(rs.next()){
            Mortuary mortuary = new Mortuary(rs.getInt("room_no"),rs.getString("patient_id"), rs.getString("patient_name"), rs.getString("doctor_name"),rs.getString("problem"),rs.getDate("date_of_dead").toLocalDate());
            return mortuary;
        }
        else{
            return null;
        }
    }

    public boolean updateMortuary(Mortuary mortuary) throws SQLException{

        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update mortuary set patient_id='%s',patient_name='%s',doctor_name='%s',problem='%s',date_of_dead='%s' where room_no='%d'",mortuary.getPatientId(),mortuary.getPatientName(),mortuary.getDoctorName(),mortuary.getProblem(),mortuary.getDateOfDead(),mortuary.getRoomNo());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }

    }
    public boolean deleteMortuary(Integer no) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete  from mortuary where room_no='"+no+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }

    }
     public ObservableList<Mortuary> getAllMortuary() throws SQLException{
        ObservableList<Mortuary> mortuaryList = FXCollections.observableArrayList();

        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from mortuary";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Mortuary mortuary = new Mortuary(rs.getInt("room_no"),rs.getString("patient_id"), rs.getString("patient_name"), rs.getString("doctor_name"),rs.getString("problem"),rs.getDate("date_of_dead").toLocalDate());
            mortuaryList.add(mortuary);
        }
        return mortuaryList;
    }



}
