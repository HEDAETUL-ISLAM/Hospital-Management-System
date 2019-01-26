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
import model.HelpDesk;

/**
 *
 * @author hedaetul
 */
public class HelpDeskController {
    Connection getConnection() throws SQLException{
    
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","root","bulbul");
        return connection;
    }
    public boolean insertEmployee(HelpDesk helpDesk) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query = String.format("insert into help_desk (person_id, person_address,person_name,person_phone,employee_gender,employee_type,help_desk_password) values ('%s','%s','%s','%s','%s','%s','s')", helpDesk.getPersonId(),helpDesk.getPersonAddress(),helpDesk.getPersonName(),helpDesk.getPersonPhone(),helpDesk.getEmployeeGender(),helpDesk.getEmployeeType(),helpDesk.getHelpDeskPassword());
        
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public HelpDesk getHelpDesk(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query = "select * from help_desk where person_id='"+id+"'";
        ResultSet rs =statement.executeQuery(query);
        if(rs.next()){
            HelpDesk helpDesk = new HelpDesk(rs.getString("employee_type"),rs.getString("employee_gender"),rs.getString("help_desk_password"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            return helpDesk;
        }
        else{
            return null;
        }
    }
    public  boolean updateEmployee(HelpDesk helpDesk) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        
        String query= String.format("update help_desk set employee_gender='%s', employee_type='%s', person_id='%s', person_name='%s',person_address='%s',person_phone='%s',help_desk_password='%s'", helpDesk.getEmployeeGender(),helpDesk.getEmployeeType(),helpDesk.getPersonId(),helpDesk.getPersonName(),helpDesk.getPersonAddress(),helpDesk.getPersonPhone(),helpDesk.getHelpDeskPassword());
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean deleteHelpDesk(String id) throws SQLException{
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from help_desk where person_id='"+id+"'";
        statement.executeUpdate(query);
        if(statement.executeUpdate(query)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public int loginAction(HelpDesk helpDesk ) throws SQLException{
        Connection conn=getConnection();
        String query = "select * from help_desk where person_id='"+helpDesk.getPersonId()+"' and help_desk_password='"+helpDesk.getHelpDeskPassword()+"'";
        Statement statement=conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        if(rs.next()){
            return 1;
        }
        else{
            return -1;
        }
    }
    public ObservableList<HelpDesk> getAllHelpDesk() throws SQLException{
        ObservableList<HelpDesk> helpDeskList = FXCollections.observableArrayList();
        
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        String query="select  *  from help_desk";
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
            HelpDesk helpDesk = new HelpDesk(rs.getString("employee_type"),rs.getString("employee_gender"),rs.getString("help_desk_password"),rs.getString("person_id"),rs.getString("person_name"),rs.getString("person_address"),rs.getString("person_phone"));
            helpDeskList.add(helpDesk);
        }
        return helpDeskList;
    }
    
    
}
