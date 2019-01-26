/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.PatientController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddPatientController implements Initializable {

    @FXML
    private BorderPane addPatient;
    @FXML
    private JFXTextField patientIdAddField;
    @FXML
    private JFXTextField patientNameAddField;
    @FXML
    private JFXTextField patientAddressAddField;
    @FXML
    private JFXTextField patientPhoneAddField;
    @FXML
    private JFXTextField patientDoctorAddField;
    @FXML
    private JFXTextField patientWeightAddField;
    @FXML
    private JFXTextField patientAgeAddField;
    @FXML
    private JFXTextField patientGenderAddField;
    @FXML
    private JFXTextField patientAppointAddField;
    @FXML
    private JFXTextField patientStatusAddField;
    @FXML
    private JFXTextField patientDiseaseAddField;

    /**Patient patient = new Patient(patientDoctorAddField.getText(), patientWeightAddField.getText(), patientAgeAddField.getText(), patientGenderAddField.getText(), patientAppointAddField.getText(), patientStatusAddField.getText(), patientDiseaseAddField.getText(), patientIdAddField.getText(), patientNameAddField.getText(), patientAddressAddField.getText(), patientPhoneAddField.getText()){
patientIdAddField.getText().length()==0 || patientNameAddField.getText().length()==0 || patientAddressAddField.getText().length()==0 || patientPhoneAddField.getText().length()==0
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_PatientController.selectedPatient != null){
            Patient patient = FXML_PatientController.selectedPatient;
            patientIdAddField.setText(patient.getPersonId());
            FXML_PatientController.selectedPatient=null;
        }
    }    

    public static FXML_PatientController fXML_PatientController = new FXML_PatientController();
    @FXML
    private void savePatientAction(ActionEvent event) throws SQLException {
        
        PatientController patientController = new PatientController();
        if(patientIdAddField.getText().length()==0 || patientNameAddField.getText().length()==0 || patientAddressAddField.getText().length()==0 || patientPhoneAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
        }
        else{
            try{
                Patient patient = new Patient(patientDoctorAddField.getText(), patientWeightAddField.getText(), patientAgeAddField.getText(), patientGenderAddField.getText(), patientAppointAddField.getText(), patientStatusAddField.getText(), patientDiseaseAddField.getText(), patientIdAddField.getText(), patientNameAddField.getText(), patientAddressAddField.getText(), patientPhoneAddField.getText());
                if(FXML_PatientController.update== false){
                    patientController.insertPatient(patient);
                }
                else{
                    patientController.updatePatient(patient);
                    FXML_PatientController.update= false;
                }
                fXML_PatientController.patientList.add(patient);
                JOptionPane.showMessageDialog(null, "Added.");
                patientIdAddField.setText("");
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Already exist.");
                 patientIdAddField.setText("");
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
    private void cancelAddPatient(ActionEvent event) {
        FXML_PatientController.stage.close();
    }
}
    
   
