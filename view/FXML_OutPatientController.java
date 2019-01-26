/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.OutPatientController;
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
import model.OutPatient;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_OutPatientController implements Initializable {

    @FXML
    private BorderPane inPatientBorderPane;
    @FXML
    private JFXTextField searchOutPatient;
    @FXML
    private TableView<OutPatient> outpatientTable;
    @FXML
    private TableColumn<OutPatient,String> patientId;
    @FXML
    private TableColumn<OutPatient,String> patientName;
    @FXML
    private TableColumn<OutPatient,String> patientAddress;
    @FXML
    private TableColumn<OutPatient,String> patientPhone;
    @FXML
    private TableColumn<OutPatient,String> patientLabNo;
    @FXML
    private TableColumn<OutPatient,Double> patientBill;
    
    
    @FXML
    private BorderPane outPatientBorderPane;
    

    
//    OutPatient(double bill, String labNo, String personId, String personName, String personAddress, String personPhone) {
//        super(personId, personName, personAddress, personPhone);
    /**
     * Initializes the controller class.
     */
    public static ObservableList<OutPatient> outPatientList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OutPatientController outPatientController = new OutPatientController();
        outpatientTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            outPatientList=(ObservableList<OutPatient>)outPatientController.getAllOutPatient();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_OutPatientController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        outpatientTable.setItems(outPatientList);
        patientId.setCellValueFactory(new PropertyValueFactory<OutPatient,String>("personId"));
        patientName.setCellValueFactory(new PropertyValueFactory<OutPatient,String>("personName"));
        patientAddress.setCellValueFactory(new PropertyValueFactory<OutPatient,String>("personAddress"));
        patientPhone.setCellValueFactory(new PropertyValueFactory<OutPatient,String>("personPhone"));
        patientLabNo.setCellValueFactory(new PropertyValueFactory<OutPatient,String>("labNo"));
        patientBill.setCellValueFactory(new PropertyValueFactory<OutPatient,Double>("bill"));
    }    

    public static OutPatient selectedOutPatient = null;
    public static boolean updateButton = false;
    public static boolean update = false;
    public static boolean clicked = false;
    @FXML
    private void searchOutPatient(KeyEvent event) {
        if(!"".equals(searchOutPatient.getText())){
            String s = searchOutPatient.getText();
            ObservableList<OutPatient>tempList=FXCollections.observableArrayList();
            for(OutPatient inPatient : outPatientList){
                if(inPatient.getPersonId().contains(s) || inPatient.getPersonName().contains(s) || inPatient.getPersonAddress().contains(s)|| inPatient.getPersonPhone().contains(s) || inPatient.getLabNo().contains(s)){
                    tempList.add(inPatient);
                }
            }
            outpatientTable.setItems(tempList);
        }
        else{
            outpatientTable.setItems(outPatientList);
        }
    }

    @FXML
    private void tableItemClicked(MouseEvent event) {
        OutPatient selectedOutPatient=outpatientTable.getSelectionModel().getSelectedItem();
        if(selectedOutPatient  == null){
            return;
        }
        updateButton=true;
        clicked = true;
    }

    public static Stage stage;
    @FXML
    private void outpatientAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddOutPatient.fxml"));  
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
    private void cancelOutPatientAddAction(ActionEvent event) {
        outpatientTable.getSelectionModel().clearSelection();
        selectedOutPatient = null;
        clicked=false;
        updateButton=false;
    }

    @FXML
    private void outpatientUpdateAction(ActionEvent event) throws IOException {
        if(updateButton ==true){
            selectedOutPatient=outpatientTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddOutPatient.fxml"));
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
    private void outpatientDelete(ActionEvent event) {
        if(clicked == true){
            OutPatientController  outPatientController = new OutPatientController();
            try{
                ObservableList<OutPatient> selectItems = outpatientTable.getSelectionModel().getSelectedItems();
                for(OutPatient outPatient : selectItems){
                    outPatientController.deleteOutPatient(outPatient.getPersonId());
                }
                outPatientList.removeAll(selectItems);
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
    private void patientAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Patient.fxml"));     
        outPatientBorderPane.setCenter(root);
    }

    @FXML
    private void inPatientAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_InPatient.fxml"));     
        outPatientBorderPane.setCenter(root);
    }
}
