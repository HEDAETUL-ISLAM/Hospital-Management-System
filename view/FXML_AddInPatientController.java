/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.InPatientController;
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
import model.InPatient;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddInPatientController implements Initializable {

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
    private JFXTextField roomNoAddField;
    @FXML
    private JFXTextField labNoAddField;
    @FXML
    private JFXTextField admitDtaeAddField;
    @FXML
    private JFXTextField dischargeDtaeAddField;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_InPatientController.selectedInPatient != null){
            InPatient inPatient = FXML_InPatientController.selectedInPatient;
            patientIdAddField.setText(inPatient.getPersonId());
        }
    }    

    public static FXML_InPatientController fXML_InPatientController = new FXML_InPatientController();
    @FXML
    private void saveInPatientAction(ActionEvent event) {
        InPatientController inPatientController = new InPatientController();
        if(patientIdAddField.getText().length() ==0 || patientNameAddField.getText().length()==0 ||patientAddressAddField.getText().length() ==0 || patientPhoneAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        //InPatient(LocalDate dateOfDischarge, int roomNo, String labNo, LocalDate dateOfAdmit, String personId, String personName, String personAddress, String personPhone) {
        //super(personId, personName, personAddress, personPhone);
        else{
            try{
                InPatient inPatient = new InPatient(LocalDate.parse( dischargeDtaeAddField.getText()), Integer.parseInt(roomNoAddField.getText()), labNoAddField.getText(), LocalDate.parse( admitDtaeAddField.getText()), patientIdAddField.getText(),patientNameAddField.getText() ,patientAddressAddField.getText() ,patientPhoneAddField.getText() );
                if(FXML_InPatientController.update == false){
                    inPatientController.insertInPatient(inPatient);
                    FXML_InPatientController.inPatientList.add(inPatient);
                }
                else{
                    inPatientController.updateInPatient(inPatient);
                    FXML_InPatientController.inPatientList.add(inPatient);
                    FXML_InPatientController.update=false;
                }
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
                  JOptionPane.showMessageDialog(null, "Please valid date");
            }
        }
    }

    @FXML
    private void cancelAddInPatient(ActionEvent event) {
        FXML_InPatientController.stage.close();
    }
    
}
