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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ambulance;

/**
 *
 * @author hedaetul
 */
public class AmbulanceController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    
    public boolean insertAmbulance(Ambulance ambulance) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into ambulance  (ambulance_no, driver_name)  values ('%s','%s')", ambulance.getAmbulanceNo(),ambulance.getDriverName());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Ambulance getAmbulance(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = "select * from ambulance where ambulance_no='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        if(rs.next()){
            Ambulance ambulance = new Ambulance(rs.getString("ambulance_no"),rs.getString("driver_name"));
            return ambulance;
        }
        else{
            return null;
        }
    }
    
    public boolean updatAmbulance(Ambulance ambulance){
        try {
            Connection conn=getConnection();
            Statement statement=conn.createStatement();
            String query = String.format("update ambulance set driver_name='%s' where ambulance_no='%s'",ambulance.getDriverName(),ambulance.getAmbulanceNo());
            
            statement.executeUpdate(query);
            if(statement.executeUpdate(query)==1){
                
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AmbulanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteAmbulance(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete  from ambulance where ambulance_no='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public ObservableList<Ambulance> getAllAmbulance() throws SQLException{
        ObservableList<Ambulance> ambulanceList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from ambulance";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Ambulance ambulance = new Ambulance(rs.getString("ambulance_no"),rs.getString("driver_name"));
            ambulanceList.add(ambulance);
        }
        return ambulanceList;
    }
}
