/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.PatientRecordController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.PatientRecord;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_Add_PatientRecordController implements Initializable {

    @FXML
    private BorderPane addPatientRecord;
    @FXML
    private JFXTextField patientIdAddField;
    @FXML
    private JFXTextField patientNameAddField;
    @FXML
    private JFXTextField doctorIdAddfield;
    @FXML
    private JFXTextField doctorRecordAddField;
    @FXML
    private JFXTextField problemAddField;
    @FXML
    private JFXTextField dateOfAdmitAddField;
    @FXML
    private JFXTextField dateOfDischargeAddField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_PatientRecordController.selectPatientRecord != null){
            PatientRecord patientRecord  = FXML_PatientRecordController.selectPatientRecord;
            patientIdAddField.setText(patientRecord.getPatientId());
            FXML_PatientRecordController.selectPatientRecord=null;
        }
    }    

    public FXML_PatientRecordController fXML_PatientRecordController = new FXML_PatientRecordController();
    @FXML
    private void savePatientRecordAction(ActionEvent event) throws SQLException {
        PatientRecordController patientRecordController = new PatientRecordController();
        if(patientIdAddField.getText().length()==0 || patientNameAddField.getText().length()==0 || doctorIdAddfield.getText().length()==0 || doctorRecordAddField.getText().length()==0 ){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                 PatientRecord patientRecord1= new PatientRecord(patientIdAddField.getText(), doctorRecordAddField.getText(), doctorIdAddfield.getText(), patientNameAddField.getText(),LocalDate.parse( dateOfDischargeAddField.getText()),LocalDate.parse( dateOfAdmitAddField.getText()), problemAddField.getText());
                if(FXML_PatientRecordController.update==false){
                    patientRecordController.insertPatientRecord(patientRecord1);
                    FXML_PatientRecordController.patientRecordList.add(patientRecord1);
                }
                else{
                    patientRecordController.updatePatientRecord(patientRecord1);
                    FXML_PatientRecordController.patientRecordList.add(patientRecord1);
                    FXML_PatientRecordController.update=false;
                }
                patientIdAddField.setText("");
                JOptionPane.showMessageDialog(null, "Added.");
                return;
            }
            catch (SQLException ex) {
                //Logger.getLogger(FXML_AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Already exist.");
                patientIdAddField.setText("");
                System.out.println(ex);
                
            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Please valid input");
            }
            catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Please valid date");
            }
        }
    }
    @FXML
    private void cancelPatientRecordAction(ActionEvent event) {
        FXML_PatientRecordController.stage.close();
    }
    
}
