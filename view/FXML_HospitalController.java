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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import start.AppMain;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_HospitalController implements Initializable {

    @FXML
    public  BorderPane mainPan;
    @FXML
    private JFXButton manualButton;
    @FXML
    private JFXButton logutButton;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashbordButtons.fxml"));
            
            mainPan.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(FXML_HospitalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(FXML_LoginController.hdesk==true){
            manualButton.setVisible(false);
        }
        else{
            manualButton.setVisible(true);
        }
    }    

    

    @FXML
    private void dashboardButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DashbordButtons.fxml"));      
        mainPan.setCenter(root);
    }

    @FXML
    private void userInfoButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));      
        mainPan.setCenter(root);
        
    }

    @FXML
    private void aboutHospitalButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HospitalDetails.fxml"));      
        mainPan.setCenter(root);
    }

    @FXML
    private void manualButtonAddAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashbordButtons.fxml"));      
        mainPan.setCenter(root);
    }

    @FXML
    private void logutButtonAddAction(ActionEvent event) throws IOException {
        FXML_LoginController.stage.close();
        AppMain.mainStage.show();
        FXML_LoginController.hdesk=false;
        FXML_LoginController.adminDesk=false;
        
    }
    
}
