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
import model.Medicine;
import view.FXML_MedicineController;

/**
 *
 * @author hedaetul
 */
public class MedicineController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    
    public boolean insertMedicine(Medicine medicine) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("insert into medicine (medicine_id,medicine_name, medicine_type,medicine_company,medicine_cost) values ('%s','%s','%s','%s','%f')",medicine.getMedicineId(),medicine.getMedicineName(),medicine.getMedicineType(),medicine.getMedicineCompany(),medicine.getMedicineCost());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Medicine getmedicine(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from medicine where medicine_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            Medicine medicine = new Medicine(rs.getString("medicine_id"),rs.getString("medicine_name"), rs.getString("medicine_type"), rs.getString("medicine_company"),rs.getDouble("medicine_cost"));
            return medicine;
        }
        else{
            return null;
        }
    }
    
    public boolean updateMedicine(Medicine medicine) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update medicine set medicine_name='%s',medicine_type='%s',medicine_company='%s',medicine_cost='%f' where medicine_id='%s'",medicine.getMedicineName(),medicine.getMedicineType(),medicine.getMedicineCompany(),medicine.getMedicineCost(),medicine.getMedicineId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteMedicine(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from medicine where medicine_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
     public ObservableList<Medicine> getAllMedicine() throws SQLException{
        ObservableList<Medicine> medicineList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from medicine";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Medicine medicine = new Medicine(rs.getString("medicine_id"),rs.getString("medicine_name"), rs.getString("medicine_type"), rs.getString("medicine_company"),rs.getDouble("medicine_cost"));
            medicineList.add(medicine);
        }
        return medicineList;
    }
    
   
    
}
