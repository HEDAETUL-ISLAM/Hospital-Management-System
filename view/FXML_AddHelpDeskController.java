/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.HelpDeskController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.HelpDesk;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddHelpDeskController implements Initializable {

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
    private JFXTextField employeePasswordAddField;

    /**
     * Initializes the controller class.
     */
    public static FXML_HelpDeskController fXML_HelpDeskController = new FXML_HelpDeskController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_HelpDeskController.selectedHelpdesk != null){
            HelpDesk helpDesk = FXML_HelpDeskController.selectedHelpdesk;
            employeeIdAddField.setText(helpDesk.getPersonId());
            FXML_HelpDeskController.selectedHelpdesk=null;
        }
    }    

    @FXML
    private void saveEmployeeAction(ActionEvent event) {
        HelpDeskController  employeeController = new HelpDeskController();
        if(employeeIdAddField.getText().length()==0 || employeeNameAddField.getText().length()==0 || employeePasswordAddField.getText().length()==0 || employeePhoneAddField.getText().length()==0 || employeeTypeAddfield.getText().length()==0 || employeeAddressAddfield.getText().length()==0 || employeeGenderAddField.getText().length()==0 ){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try {
                 HelpDesk employee = new HelpDesk(employeeTypeAddfield.getText(),employeeGenderAddField.getText(), employeePasswordAddField.getText(), employeeIdAddField.getText(), employeeNameAddField.getText(),employeeAddressAddfield.getText(),employeePhoneAddField.getText());
                 if(FXML_EmployeeController.update==false)
                    employeeController.insertEmployee(employee);
                 else{
                     employeeController.updateEmployee(employee);
                    FXML_EmployeeController.update=false;
                 }
                 FXML_HelpDeskController.helpDeskList.add(employee);
                 JOptionPane.showMessageDialog(null, "Added.");
                 employeeIdAddField.setText("");
                employeeNameAddField.setText("");
                employeePhoneAddField.setText("");
                employeePasswordAddField.setText("");
                employeeAddressAddfield.setText("");
                employeeGenderAddField.setText("");
                employeeTypeAddfield.setText("");
                 
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Already exist.");
                employeeIdAddField.setText("");
                employeeNameAddField.setText("");
                employeePhoneAddField.setText("");
                employeePasswordAddField.setText("");
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
        fXML_HelpDeskController.stage.close();
    }
    
}
