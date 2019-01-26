/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.InPatientController;
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
import model.InPatient;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_InPatientController implements Initializable {

    @FXML
    private TableView<InPatient> inpatientTable;
    @FXML
    private TableColumn<InPatient,String> patientId;
    @FXML
    private TableColumn<InPatient,String> patientName;
    @FXML
    private TableColumn<InPatient,String> patientAddress;
    @FXML
    private TableColumn<InPatient,String> patientPhone;
    @FXML
    private JFXTextField searchInPatient;
    @FXML
    private TableColumn<InPatient,String> patientLabNo;
    @FXML
    private TableColumn<InPatient,String> patientRoom;
    @FXML
    private TableColumn<InPatient,String> patientAdmit;
    @FXML
    private TableColumn<InPatient,String> patientDischarge;
    @FXML
    private BorderPane inPatientBorderPane;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<InPatient> inPatientList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        InPatientController inPatientController = new InPatientController();
        inpatientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            inPatientList=(ObservableList<InPatient>)inPatientController.getAllInPatient();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_InPatientController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        inpatientTable.setItems(inPatientList);
        //InPatient(LocalDate dateOfDischarge, int roomNo, String labNo, LocalDate dateOfAdmit, String personId, String personName, String personAddress, String personPhone) {
        //super(personId, personName, personAddress, personPhone);
        patientId.setCellValueFactory(new PropertyValueFactory<InPatient,String>("personId"));
        patientName.setCellValueFactory(new PropertyValueFactory<InPatient,String>("personName"));
        patientAddress.setCellValueFactory(new PropertyValueFactory<InPatient,String>("personAddress"));
        patientPhone.setCellValueFactory(new PropertyValueFactory<InPatient,String>("personPhone"));
        patientLabNo.setCellValueFactory(new PropertyValueFactory<InPatient,String>("labNo"));
        patientRoom.setCellValueFactory(new PropertyValueFactory<InPatient,String>("roomNo"));
        patientAdmit.setCellValueFactory(new PropertyValueFactory<InPatient,String>("dateOfAdmit"));
        patientDischarge.setCellValueFactory(new PropertyValueFactory<InPatient,String>("dateOfDischarge"));
    }    

    public static InPatient selectedInPatient = null;
    public static boolean updateButton = false;
    public static boolean update = false;
    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        InPatient selectedInPatient=inpatientTable.getSelectionModel().getSelectedItem();
        if(selectedInPatient  == null){
            return;
        }
        updateButton=true;
        clicked = true;
        
    }

    public static Stage stage;
    @FXML
    private void inpatientAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddInPatient.fxml"));  
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
    private void cancelinPatientAddAction(ActionEvent event) {
        inpatientTable.getSelectionModel().clearSelection();
        selectedInPatient = null;
        clicked=false;
        updateButton=false;
    }

    @FXML
    private void inpatientUpdateAction(ActionEvent event) throws IOException {
        if(updateButton ==true){
            selectedInPatient=inpatientTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddInPatient.fxml"));
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
    private void inpatientDelete(ActionEvent event) {
        if(clicked == true){
            InPatientController  inPatientController = new InPatientController();
            try{
                ObservableList<InPatient> selectItems = inpatientTable.getSelectionModel().getSelectedItems();
                for(InPatient inPatient : selectItems){
                    inPatientController.deleteInPatient(inPatient.getPersonId());
                }
                inPatientList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
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
    private void searchInPatient(KeyEvent event) {
        if(!"".equals(searchInPatient.getText())){
            String s = searchInPatient.getText();
            ObservableList<InPatient>tempList=FXCollections.observableArrayList();
            for(InPatient inPatient : inPatientList){
                if(inPatient.getPersonId().contains(s) || inPatient.getPersonName().contains(s) || inPatient.getPersonAddress().contains(s)|| inPatient.getPersonPhone().contains(s) || inPatient.getLabNo().contains(s)){
                    tempList.add(inPatient);
                }
            }
            inpatientTable.setItems(tempList);
        }
        else{
            inpatientTable.setItems(inPatientList);
        }
    }

    @FXML
    private void patientAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Patient.fxml"));     
        inPatientBorderPane.setCenter(root);
    }

    @FXML
    private void outPatientAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_OutPatient.fxml"));     
        inPatientBorderPane.setCenter(root);
    }
    
}
