/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class DashbordButtonsController implements Initializable {

    @FXML
     public   BorderPane borderPan;
    @FXML
    private JFXButton ambulanceButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void ambulanceButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Ambulance.fxml"));      
        borderPan.setCenter(root);
    }

    @FXML
    private void appointmentButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Appointment.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void billButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Bill.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void departmentButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Department.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void doctorButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Doctor.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void employeeButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Employee.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void labreportButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Labreport.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void medicineButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Medicine.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void mortuaryButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Mortuary.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void otButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_OperationTheater.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void parkingButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Parking.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void patientRecordButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_PatientRecord.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void patientButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Patient.fxml"));     
        borderPan.setCenter(root);
    }

    @FXML
    private void roomButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Room.fxml"));     
        borderPan.setCenter(root);
    }
    
}
