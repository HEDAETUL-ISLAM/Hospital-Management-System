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
import model.Bill;
import oracle.sql.*;
import oracle.jdbc.*;

/**
 *
 * @author hedaetul
 */
public class BillController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertBill(Bill bill) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into bill (bill_no,patient_id,doctor_charge,medicine_charge ,room_charge,operation_charge,no_of_days,health_card,lab_charge,advance_pay,total_bill,due) values('%s','%s','%f','%f','%f','%f','%d','%f','%f','%f','%f','%f')",bill.getBillNo(),bill.getPatientId(),bill.getDoctorCharge(),bill.getMedicineCharge(),bill.getRoomCharge(),bill.getOperationCharge(),bill.getNoOfDays(),bill.getHealthCard(),bill.getLabCharge(),bill.getAdvancePay(),bill.getTotalBill(),bill.getDue());
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public int getBill() throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = "select bill_no.nextval from dual";
        ResultSet rs=statement.executeQuery(query);
        if(rs.next()){
            return rs.getInt(1);
        }
        else{
            return -1;
        }
        
    }
//    public int getAppointment() throws SQLException{
//        Connection conn=getConnection();
//        Statement statement=conn.createStatement();
//        
//        String query = "select   appoint_no.nextval from dual";
//        System.out.println("ashce");
//        ResultSet rs=statement.executeQuery(query);
//        
//        if(rs.next()){
//            return rs.getInt(1);
//
//        }
//        else{
//            return -1;
//        }
//        
//    }
    public boolean updateBill(Bill bill) throws SQLException {
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update bill set patient_id='%s',doctor_charge='%f',medicine_charge='%f',room_charge='%f',operation_charge='%f',lab_charge='%f',no_of_days='%d',health_card='%f',advance_pay='%f',total_bill='%f', due ='%f' where bill_no='%s'",bill.getPatientId(),bill.getDoctorCharge(),bill.getMedicineCharge(),bill.getRoomCharge(),bill.getOperationCharge(),bill.getLabCharge(),bill.getNoOfDays(),bill.getHealthCard(),bill.getAdvancePay(),bill.getTotalBill(),bill.getDue(),bill.getBillNo());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteBill(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = "delete from bill where bill_no='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public  ObservableList<Bill> getAllBill() throws SQLException{
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from bill";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Bill bill = new Bill(rs.getString("bill_no"),rs.getString("patient_id"),rs.getDouble("doctor_charge"),rs.getDouble("medicine_charge"),rs.getDouble("room_charge"),rs.getDouble("operation_charge"),rs.getInt("no_of_days"),rs.getDouble("health_card"),rs.getDouble("lab_charge"),rs.getDouble("advance_pay"));
            billList.add(bill);
        }
        return billList;
    }
}
