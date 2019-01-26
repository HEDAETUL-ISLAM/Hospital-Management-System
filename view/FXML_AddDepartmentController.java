/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.DepartmentController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Department;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddDepartmentController implements Initializable {

    @FXML
    private JFXTextField departmentNoAddField;
    @FXML
    private JFXTextField departmentNameAddField;
    @FXML
    private JFXTextField departmentHeadAddfield;
    @FXML
    private JFXTextField consultantAddField;
    @FXML
    private BorderPane addDepartment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_DepartmentController.selectDepartment != null){
            Department department = FXML_DepartmentController.selectDepartment;
            departmentNoAddField.setText(department.getDepartmentId());
            departmentNameAddField.setText(department.getDepartmentName());
            
            FXML_DepartmentController.selectDepartment=null;
        }
    }    

    public static FXML_DepartmentController fXML_DepartmentController = new FXML_DepartmentController();
    @FXML
    private void saveDepartmentAction(ActionEvent event) {
        DepartmentController departmentController = new DepartmentController();
        if(departmentNoAddField.getText().length() == 0 || departmentNameAddField.getText().length() == 0 || departmentHeadAddfield.getText().length()==0 || consultantAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
        }
        else{
            try{
                Department department = new Department(departmentNoAddField.getText(), departmentNameAddField.getText(), departmentHeadAddfield.getText(), consultantAddField.getText());
                if(FXML_DepartmentController.update == false){
                    departmentController.insertDepartment(department);
                }
                else{
                    departmentController.updateDepartment(department);
                    fXML_DepartmentController.update=false;
                }
                fXML_DepartmentController.departmentList.add(department);
                JOptionPane.showMessageDialog(null, "Added.");
                departmentNoAddField.setText("");
                departmentNameAddField.setText("");
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Already exist.");
                departmentNoAddField.setText("");
                departmentNameAddField.setText("");
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
    private void cancelAddDepartment(ActionEvent event) {
        FXML_DepartmentController.stage.close();
        
    }
    
}
