/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbController;

import java.sql.CallableStatement;
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
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
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
        CallableStatement stmt = null;
        stmt = conn.prepareCall("{call HOSPITAL3.EMPLOYEE_SALARY_CHECK(?,?,?,?,?,?,?)}");

        String id = employee.getPersonId();
        String add = employee.getPersonAddress();
        String name = employee.getPersonName();
        String phone = employee.getPersonPhone();
        String gender = employee.getEmployeeGender();
        Double salary = employee.getEmployeeSalary();
        String type = employee.getEmployeeType();
        stmt.setString(1,id );
        stmt.setString(2, add);
        stmt.setString(3,name );
        stmt.setString(4,phone );
        stmt.setString(5,gender );
        stmt.setDouble(6,salary );
        stmt.setString(7,type );
        stmt.executeUpdate();
        if(stmt.executeUpdate()==1){
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
