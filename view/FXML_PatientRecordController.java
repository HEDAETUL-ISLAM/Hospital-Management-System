/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.PatientRecordController;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.PatientRecord;
import static view.FXML_AppointmentController.stage;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_PatientRecordController implements Initializable {

    @FXML
    private JFXTextField searchPatientRecord;
    @FXML
    private TableView<PatientRecord> patientRecordTable;
    @FXML
    private TableColumn<PatientRecord,String> patientId;
    @FXML
    private TableColumn<PatientRecord,String> patientName;
    @FXML
    private TableColumn<PatientRecord,String> doctorId;
    @FXML
    private TableColumn<PatientRecord,String> doctorName;
    @FXML
    private TableColumn<PatientRecord,String> admidDate;
    @FXML
    private TableColumn<PatientRecord,String> dischargeDate;
    @FXML
    private TableColumn<PatientRecord,String> problem;
    @FXML
    private JFXButton getPatientRecordButton;
    @FXML
    private JFXButton cancelPatientRecordButton;
    @FXML
    private JFXButton updatePatientRecordButton;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<PatientRecord>patientRecordList= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PatientRecordController patientRecordController= new PatientRecordController();
        patientRecordTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            patientRecordList=(ObservableList<PatientRecord>)patientRecordController.getAllPatientRecord();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_PatientRecordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        patientRecordTable.setItems(patientRecordList);
        patientId.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("patientId"));
        patientName.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("patientName"));
        doctorId.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("doctorId"));
        doctorName.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("doctorName"));
        admidDate.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("dateOfAdmit"));
        dischargeDate.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("dateOfDischarge"));
        problem.setCellValueFactory(new PropertyValueFactory<PatientRecord,String>("problem"));
    }    

    public static PatientRecord selectPatientRecord=null;
    
    public static boolean updateButton = false;
    public static boolean clicked = false;
    @FXML
    private void searchPatientRecord(KeyEvent event) {
        if(!"".equals(searchPatientRecord.getText())){
            String s = searchPatientRecord.getText();
            ObservableList<PatientRecord>tempList=FXCollections.observableArrayList();
            for(PatientRecord patientRecord : patientRecordList){
                if(patientRecord.getPatientId().contains(s) || patientRecord.getPatientName().contains(s) || patientRecord.getDoctorId().contains(s) || patientRecord.getDoctorName().contains(s) || patientRecord.getProblem().contains(s)){
                    tempList.add(patientRecord);
                }
            }
            patientRecordTable.setItems(tempList);
        }
        else{
            patientRecordTable.setItems(patientRecordList);
        }
    }

    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectPatientRecord=patientRecordTable.getSelectionModel().getSelectedItem();
        if(selectPatientRecord == null){
            return;
        }
        updateButton=true;
        clicked=true;
    }

    public static Stage stage;
    @FXML
    private void patientRecordButtonAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_Add_PatientRecord.fxml"));  
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
    private void cancelPatientRecordButtonAction(ActionEvent event) {
        patientRecordTable.getSelectionModel().clearSelection();
        selectPatientRecord=null;
        clicked=false;
        updateButton=false;
    }

    public static boolean update=false;
    @FXML
    private void patientRecordUpdateButtonAction(ActionEvent event) throws IOException {
        if(updateButton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_Add_PatientRecord.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            update = true;
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }
        else{
            return;
        }
    }

    @FXML
    private void deletePatientRecordButtonAction(ActionEvent event) {
        if(clicked==true){
            PatientRecordController patientRecordController = new PatientRecordController();
            try{
                ObservableList<PatientRecord> selectItems = patientRecordTable.getSelectionModel().getSelectedItems();
                for(PatientRecord patientRecord : selectItems){
                    patientRecordController.deletePatientRecord(patientRecord.getPatientId());
                }
                patientRecordList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
            }
            catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(exception);
            }
        }
        else{
            return;
        }
    }
    
}
