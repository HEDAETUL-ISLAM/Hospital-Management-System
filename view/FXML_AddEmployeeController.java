/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.EmployeeController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Employee;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddEmployeeController implements Initializable {

    @FXML
    private BorderPane addEmployee;
    @FXML
    private JFXTextField employeeIdAddField;
    @FXML
    private JFXTextField employeeNameAddField;
    @FXML
    private JFXTextField employeeAddressAddfield;
    @FXML
    private JFXTextField employeePhoneAddField;
    @FXML
    private JFXTextField employeeGenderAddField;
    @FXML
    private JFXTextField employeeTypeAddfield;
    @FXML
    private JFXTextField employeeSalaryAddField;

    /**
     * Initializes the controller class.
     */
     public static FXML_EmployeeController fXML_EmployeeController = new FXML_EmployeeController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if(FXML_EmployeeController.selectEmployee != null){
            Employee  employee = FXML_EmployeeController.selectEmployee;
            employeeIdAddField.setText(employee.getPersonId());
            FXML_EmployeeController.selectEmployee=null;
        }
    }    

    @FXML
    private void saveEmployeeAction(ActionEvent event) {
         EmployeeController  employeeController = new EmployeeController();
        if(employeeIdAddField.getText().length()==0 || employeeNameAddField.getText().length()==0 || employeeSalaryAddField.getText().length()==0 || employeePhoneAddField.getText().length()==0 || employeeTypeAddfield.getText().length()==0 || employeeAddressAddfield.getText().length()==0 || employeeGenderAddField.getText().length()==0 ){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try {
                 Employee employee = new Employee(employeeGenderAddField.getText(), employeeTypeAddfield.getText(), Double.parseDouble(employeeSalaryAddField.getText()), employeeIdAddField.getText(), employeeNameAddField.getText(),employeeAddressAddfield.getText(),employeePhoneAddField.getText());
                 if(FXML_EmployeeController.update==false)
                    employeeController.insertEmployee(employee);
                 else{
                     employeeController.updateEmployee(employee);
                    FXML_EmployeeController.update=false;
                 }
                 FXML_EmployeeController.employeeList.add(employee);
                 JOptionPane.showMessageDialog(null, "Added.");
                 employeeIdAddField.setText("");
                employeeNameAddField.setText("");
                employeePhoneAddField.setText("");
                employeeSalaryAddField.setText("");
                employeeAddressAddfield.setText("");
                employeeGenderAddField.setText("");
                employeeTypeAddfield.setText("");
                 
            } 
            catch (SQLException ex) {
                //Logger.getLogger(FXML_AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Already exist.");
                employeeIdAddField.setText("");
                employeeNameAddField.setText("");
                employeePhoneAddField.setText("");
                employeeSalaryAddField.setText("");
                employeeAddressAddfield.setText("");
                employeeGenderAddField.setText("");
                employeeTypeAddfield.setText("");
                System.out.println(ex);
                
            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Please valid input");
            }
            catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Please valid input");
            }
        }
    }

    @FXML
    private void cancelAddEmployee(ActionEvent event) {
         FXML_EmployeeController.stage.close();
    }
    
}
