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
public class AdminDashbordButtonsController implements Initializable {

    @FXML
    private BorderPane borderPan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void addDoctorButtonController(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Doctor.fxml"));     
        borderPan.setCenter(root);
    }


    @FXML
    private void addHelpdeskButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_HelpDesk.fxml"));     
        borderPan.setCenter(root);
    }


    @FXML
    private void addEmployeeButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Employee.fxml"));     
        borderPan.setCenter(root);
    }

    
}
