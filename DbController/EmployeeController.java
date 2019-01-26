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
import model.Employee;

/**
 *
 * @author hedaetul
 */
public class EmployeeController {
    
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","bulbul");
        return connection;
    }
    public boolean insertEmployee(Employee employee) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into employee (employee_gender,employee_type,employee_salary,person_id,person_name, person_address,person_phone) values ('%s','%s','%f','%s','%s','%s','%s')", employee.getEmployeeGender(),employee.getEmployeeType(),employee.getEmployeeSalary(),employee.getPersonId(),employee.getPersonName(),employee.getPersonAddress(),employee.getPersonPhone());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Employee getEmployee(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = "select * from employee where person_id='"+id+"'";
        ResultSet rs =statement.executeQuery(query);
        if(rs.next()){
            Employee employee = new Employee(rs.getString("employee_gender"),rs.getString("employee_type"),rs.getDouble("employee_salary"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            return employee;
        }
        else{
            return null;
        }
    }
    public  boolean updateEmployee(Employee employee) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= String.format("update employee set employee_gender='%s', employee_type='%s', employee_salary='%f', person_name='%s',person_address='%s',person_phone='%s' where person_id = '%s'", employee.getEmployeeGender(),employee.getEmployeeType(),employee.getEmployeeSalary(),employee.getPersonName(),employee.getPersonAddress(),employee.getPersonPhone(),employee.getPersonId());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteEmployee(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="delete from employee where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public ObservableList<Employee> getAllEmployee() throws SQLException{
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from employee";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            Employee employee = new Employee(rs.getString("employee_gender"),rs.getString("employee_type"),rs.getDouble("employee_salary"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            employeeList.add(employee);
        }
        return employeeList;
    }
    
}
