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
import model.Admin;

/**
 *
 * @author hedaetul
 */
public class AdminController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    
    public boolean insertAdmin(Admin admin) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into admin (person_id, person_address,person_name,person_phone,admin_password) values ('%s','%s','%s','%s','%s')",admin.getPersonId(),admin.getPersonAddress(),admin.getPersonName(),admin.getPersonPhone(),admin.getAdminPassword());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
//    public Admin(String adminPassword, String personId, String personName, String personAddress, String personPhone) {
//        super(personId, personName, personAddress, personPhone);   
    
    
    public Admin getAdmin(String id) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from admin where person_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
            Admin admin = new Admin(rs.getString("admin_password"), rs.getString("person_id"),rs.getString("person_name"), rs.getString("person_address"), rs.getString("person_phone"));
            return admin;
        }
        else{
            return null;
        }
    }
    
    public boolean updateAdmin(Admin admin) throws SQLException{
    
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update admin set person_id='%s',person_address='%s',person_name='%s' ,person_phone='%s',admin_password='%s'  where admin_id='%s'",admin.getPersonId(),admin.getPersonAddress(), admin.getPersonName(),admin.getPersonPhone(), admin.getAdminPassword());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public boolean deleteAdmin(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from admin where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public int loginAction(Admin admin) throws SQLException{
        System.out.println(admin.getPersonId()+' '+admin.getAdminPassword());
        Connection conn=getConnection();
        String query = "select * from admin where person_id= '"+admin.getPersonId()+"' and admin_password = '"+admin.getAdminPassword()+"'";
        Statement statement=conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        if(rs.next()){
            return 1;
        }
        else{
            return -1;
        }
    }
    
    public boolean updatePassword(String id, String password) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = "update admin set admin_password='"+password+"' where person_id='"+id+"'";
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    
   
    
}
