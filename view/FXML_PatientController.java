/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.PatientController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Patient;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_PatientController implements Initializable {

    @FXML
    private JFXTextField searchPatient;
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> patientId;
    @FXML
    private TableColumn<Patient, String> patientName;
    @FXML
    private TableColumn<Patient, String> patientAddress;
    @FXML
    private TableColumn<Patient, String> patientPhone;
    @FXML
    private TableColumn<Patient, String> patientDoctor;
    @FXML
    private TableColumn<Patient, String> patientWeight;
    @FXML
    private TableColumn<Patient, String> patientAge;
    @FXML
    private TableColumn<Patient, String> patientGender;
    @FXML
    private TableColumn<Patient, String> patientAppoint;
    @FXML
    private TableColumn<Patient, String> patientStatus;
    @FXML
    private TableColumn<Patient, String> patientDisease;
    @FXML
    private JFXButton getPatient;
    @FXML
    private JFXButton cancelPatient;
    @FXML
    private JFXButton updatePatient;

    /**
     * Initializes the controller class.
     */
      public static ObservableList<Patient>patientList = FXCollections.observableArrayList();
    @FXML
    private BorderPane patientBorderPane;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            PatientController patientController = new PatientController();
            patientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            patientList=(ObservableList<Patient>)patientController.getAllPatient();
        }
        catch (SQLException ex) {
            Logger.getLogger(FXML_PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        patientTable.setItems(patientList);
        patientId.setCellValueFactory(new PropertyValueFactory<Patient, String>("personId"));
        patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("personName"));
        patientAddress.setCellValueFactory(new PropertyValueFactory<Patient, String>("personAddress"));
        patientPhone.setCellValueFactory(new PropertyValueFactory<Patient, String>("personPhone"));
        patientDoctor.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientDoctor"));
        patientWeight.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientWeight"));
        patientAge.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientAge"));
        patientGender.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientGender"));
        patientAppoint.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientAppointNo"));
        patientStatus.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientStatus"));
        patientDisease.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientDisease"));
      
    }    
    @FXML
    private void searchPatient(KeyEvent event) {
        if(!"".equals(searchPatient.getText())){
            String s = searchPatient.getText();
            ObservableList<Patient>tempList=FXCollections.observableArrayList();
            for(Patient patient : patientList){
                if(patient.getPersonId().contains(s) || patient.getPersonName().contains( s)|| patient.getPersonAddress().contains(s) || patient.getPersonPhone().contains(s)){
                    tempList.add(patient);
                }
            }
            patientTable.setItems(tempList);
        }
        else{
            patientTable.setItems(patientList);
        }
    }

    public static Patient selectedPatient = null;
    public static boolean updateButton = false;
    public static boolean clicked = false;
    public static Stage stage;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if(selectedPatient == null){
            return;
        }
        updateButton=true;
        clicked = true;
    }

    @FXML
    private void patientAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddPatient.fxml"));  
             Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        else{
            return;
        }
    }

    @FXML
    private void cancelPatientAddAction(ActionEvent event) {
        patientTable.getSelectionModel().clearSelection();
        selectedPatient=null;
        clicked=false;
        updateButton=false;
    }

    public static boolean update = false;
    @FXML
    private void patientUpdateAction(ActionEvent event) throws IOException {
        if(updateButton == true){
            selectedPatient = patientTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddPatient.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            updateButton = true;
            update = true;
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        else{
            return;
        }
    }

    @FXML
    private void patientDelete(ActionEvent event) {
        if(clicked==true){
            PatientController patientController = new PatientController();
            try{
                ObservableList<Patient> selectItems = patientTable.getSelectionModel().getSelectedItems();
                for(Patient patient : selectItems){
                    patientController.deletePatient(patient.getPersonId());
                }
                patientList.removeAll(selectedPatient);
                JOptionPane.showMessageDialog(null, "Deleted ");
           }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(ex);
            }
        }
        else{
            return;
        }
    }

    @FXML
    private void inPatientAddAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_InPatient.fxml"));     
        patientBorderPane.setCenter(root);

    }

    @FXML
    private void outPatientAddAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_OutPatient.fxml"));     
        patientBorderPane.setCenter(root); 

    }
    
}
