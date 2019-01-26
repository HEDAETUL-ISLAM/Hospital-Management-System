/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.OutPatientController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.OutPatient;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddOutPatientController implements Initializable {

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
    private JFXTextField billAddField;
    @FXML
    private JFXTextField labNoAddField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_OutPatientController.selectedOutPatient != null){
            OutPatient outPatient = FXML_OutPatientController.selectedOutPatient;
            patientIdAddField.setText(outPatient.getPersonId());
        }
    }    

    public static FXML_OutPatientController fXML_OutPatientController = new FXML_OutPatientController();
    @FXML
    private void saveOutPatientAction(ActionEvent event) {
        OutPatientController outPatientController = new OutPatientController();
        if(patientIdAddField.getText().length() ==0 || patientNameAddField.getText().length()==0 ||patientAddressAddField.getText().length() ==0 || patientPhoneAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        //OutPatient(double bill, String labNo, String personId, String personName, String personAddress, String personPhone) {
        //super(personId, personName, personAddress, personPhone);
        else{
            try{
                OutPatient outPatient = new OutPatient(Double.parseDouble(billAddField.getText()), labNoAddField.getText(),  patientIdAddField.getText(),patientNameAddField.getText() ,patientAddressAddField.getText() ,patientPhoneAddField.getText() );
                if(FXML_OutPatientController.update == false){
                    outPatientController.insertOutPatient(outPatient);
                    FXML_OutPatientController.outPatientList.add(outPatient);
                }
                else{
                    outPatientController.updateOutPatient(outPatient);
                    FXML_OutPatientController.outPatientList.add(outPatient);
                    FXML_OutPatientController.update=false;
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
    private void cancelAddOutPatient(ActionEvent event) {
        FXML_OutPatientController.stage.close();
    }
    
}
