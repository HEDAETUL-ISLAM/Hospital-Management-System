/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.DoctorController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Doctor;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddDoctorController implements Initializable {

    @FXML
    private BorderPane addBill;
    @FXML
    private JFXTextField drIdAddField;
    @FXML
    private JFXTextField drNameAddField;
    @FXML
    private JFXTextField drAddressAddfield;
    @FXML
    private JFXTextField drPhoneAddField;
    @FXML
    private JFXTextField drDepartmentAddField;
    @FXML
    private JFXTextField drQualificationAddfield;
    @FXML
    private JFXTextField drGenderAddField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_DoctorController.selectDoctor!=null){
            Doctor doctor =FXML_DoctorController.selectDoctor;
            drIdAddField.setText(doctor.getPersonId());
            
            FXML_AppointmentController.selectAppointment=null;
        }
    }    

    public static FXML_DoctorController fXML_DoctorController = new FXML_DoctorController();
    @FXML
    private void saveDoctorAction(ActionEvent event) {
        DoctorController doctorController = new DoctorController();
        if(drIdAddField.getText().length()==0 || drNameAddField.getText().length()==0 || drAddressAddfield.getText().length()==0 || drPhoneAddField.getText().length()==0 || drDepartmentAddField.getText().length()==0 || drQualificationAddfield.getText().length()==0 || drGenderAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
        }
        else{
            try{
                    Doctor doctor = new Doctor(drDepartmentAddField.getText(), drQualificationAddfield.getText(), drGenderAddField.getText(), drIdAddField.getText(), drNameAddField.getText(), drAddressAddfield.getText(), drPhoneAddField.getText());
                    if(FXML_DoctorController.update == false){
                        doctorController.insertDoctor(doctor);
                    }
                    else{
                        doctorController.updateDoctor(doctor);
                        FXML_DoctorController.update=false;
                    }
                    fXML_DoctorController.drList.add(doctor);
                    JOptionPane.showMessageDialog(null, "Added.");
                    drIdAddField.setText("");
                    drNameAddField.setText("");
            
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Already exist.");
                drIdAddField.setText("");
                drNameAddField.setText("");
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
    private void cancelAddDoctor(ActionEvent event) {
        FXML_DoctorController.stage.close();
    }
    
}
