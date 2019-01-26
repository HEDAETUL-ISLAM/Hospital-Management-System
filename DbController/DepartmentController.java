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
import model.Department;

/**
 *
 * @author hedaetul
 */
public class DepartmentController {
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    public boolean insertDepartment(Department department) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into department (department_id, department_name, department_head, consultant) values('%s','%s','%s','%s')", department.getDepartmentId(),department.getDepartmentName(),department.getDepartmentHead(),department.getConsultant());
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public Department getDepartment(String id) throws SQLException {
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = "select * from department where department_id='"+id+"'";
        ResultSet rs=statement.executeQuery(query);
        if(rs.next()){
            Department department = new Department(rs.getString("department_id"),rs.getString("department_name"),rs.getString("department_head"),rs.getString("consultant"));
            return department;
        }
        else{
            return null;
        }
    }
    public boolean updateDepartment(Department department) throws SQLException {
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = String.format("update department set department_name='%s',department_head='%s',consultant='%s' where department_id='%s'",department.getDepartmentName(),department.getDepartmentHead(),department.getConsultant(), department.getDepartmentId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteDepartment(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = "delete from department where department_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public ObservableList<Department> getAllDepartment() throws SQLException{
        ObservableList<Department> departmentList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from department";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Department department = new Department(rs.getString("department_id"),rs.getString("department_name"),rs.getString("department_head"),rs.getString("consultant"));
            departmentList.add(department);
        }
        return departmentList;
    }
}

