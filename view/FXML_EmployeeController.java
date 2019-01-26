/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.EmployeeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Employee;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_EmployeeController implements Initializable {

    @FXML
    private JFXTextField searchEmployee;
    @FXML
    private TableColumn<Employee,String> employeeNo;
    @FXML
    private TableColumn<Employee,String> employeeName;
    @FXML
    private TableColumn<Employee,String> employeeAddress;
    @FXML
    private TableColumn<Employee,String> employeePhone;
    @FXML
    private TableColumn<Employee,String> employeeGender;
    @FXML
    private TableColumn<Employee,String> employeeType;
    @FXML
    private TableColumn<Employee,Double> employeeSalary;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private JFXButton addEmployeeButton;
    @FXML
    private JFXButton cancelEmployeeButton;
    @FXML
    private JFXButton updateEmployeeButton;
    @FXML
    private JFXButton deleteEmployeeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EmployeeController employeeController = new EmployeeController();
        employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        try{
            employeeList=(ObservableList<Employee>)employeeController.getAllEmployee();
        } 
        catch (SQLException ex) {
                Logger.getLogger(FXML_EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
         }
        employeeTable.setItems(employeeList);
        employeeNo.setCellValueFactory(new PropertyValueFactory<Employee,String>("personId"));
        employeeName.setCellValueFactory(new PropertyValueFactory<Employee,String>("personName"));
        employeeAddress.setCellValueFactory(new PropertyValueFactory<Employee,String>("personAddress"));
        employeePhone.setCellValueFactory(new PropertyValueFactory<Employee,String>("personPhone"));
        employeeGender.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeGender"));
        employeeType.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeType"));
        employeeSalary.setCellValueFactory(new PropertyValueFactory<Employee,Double>("employeeSalary"));
        if(FXML_LoginController.hdesk==true){
            addEmployeeButton.setVisible(false);
            cancelEmployeeButton.setVisible(false);
            updateEmployeeButton.setVisible(false);
            deleteEmployeeButton.setVisible(false);
        }
        else{
            addEmployeeButton.setVisible(true);
            cancelEmployeeButton.setVisible(true);
            updateEmployeeButton.setVisible(true);
            deleteEmployeeButton.setVisible(true);
        }
    }    

    public static ObservableList<Employee>employeeList = FXCollections.observableArrayList();
    @FXML
    private void searchEmployee(KeyEvent event) {
        if(!"".equals(searchEmployee.getText())){
            String s = searchEmployee.getText();
            ObservableList<Employee>tempList=FXCollections.observableArrayList();
            for(Employee employee : employeeList){
                if(employee.getPersonId().contains(s) || employee.getPersonName().contains(s) || employee.getPersonPhone().contains(s) ||employee.getPersonAddress().contains(s) || employee.getEmployeeGender().contains(s) || (employee.getEmployeeSalary()+"").contains(s) || employee.getEmployeeType().contains(s)){
                    tempList.add(employee);
                }
            }
           employeeTable.setItems(tempList);
        }
        else{
            employeeTable.setItems(employeeList);
        }
    }

    public static Employee selectEmployee = null;
    public static boolean updatebutton = false;
    public static boolean clicked = false;
    public static boolean update = false;
    
    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectEmployee= employeeTable.getSelectionModel().getSelectedItem();
        if(selectEmployee == null){
            return;
        }
        updatebutton=true;
        clicked=true;
    }

    public static Stage stage;
    @FXML
    private void employeeAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddEmployee.fxml"));   
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        else{
            return;
        }    
    }

    @FXML
    private void cancelemployeeAddAction(ActionEvent event) {
        employeeTable.getSelectionModel().clearSelection();
        selectEmployee = null;
       clicked=false;
        updatebutton=false;
    }

    @FXML
    private void employeeUpdateAction(ActionEvent event) throws IOException {
        if(updatebutton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddEmployee.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            update = true;
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        else{
            return;
        }
    }

    @FXML
    private void employeeDelete(ActionEvent event) throws SQLException {
        if(clicked==true){
            EmployeeController employeeController = new EmployeeController();
            try{
                ObservableList<Employee> selectItems = employeeTable.getSelectionModel().getSelectedItems();
                for(Employee employee  : selectItems){
                    employeeController.deleteEmployee(employee.getPersonId());
                }
                employeeList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(ex);
            }
        }
        else{
            return;
        }
    }



    
}
