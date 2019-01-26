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
import model.Hospital;

/**
 *
 * @author hedaetul
 */
public class HospitalController {
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    public boolean insertHospital(Hospital hospital) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into hospital (hospital_name, hospital_address,hospital_code) values ('%s','%s','%s')",hospital.getHospitalName(),hospital.getHospitalCode(),hospital.getHospitalAddress());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean updateHospital(Hospital hospital) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update hospital set hospital_name='%s',hospital_address='%s',hospital_code='%s' ",hospital.getHospitalName(),hospital.getHospitalCode(),hospital.getHospitalAddress());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
     public Object[][] gethospital() throws SQLException{
            List<Hospital> hospitalList=new ArrayList();
            
            Object hospitalArray[][]=new Object[1000][1000];
            
            Connection conn=getConnection();
            Statement statement=conn.createStatement();
            String query="select  *  from hospital";
            ResultSet rs=statement.executeQuery(query);
           
            int j=0;
            while(rs.next()){
                Hospital hospital = new Hospital(rs.getString("hospital_name"),rs.getString("hospital_code"),rs.getString("hospital_address"));
                hospitalList.add(hospital);
               
                    hospitalArray[j][0]=hospital.getHospitalCode();
                    hospitalArray[j][1]=hospital.getHospitalName();
                    hospitalArray[j][2]=hospital.getHospitalAddress();
                    
                    j++;        
     }
            return hospitalArray;
     }
    
}
