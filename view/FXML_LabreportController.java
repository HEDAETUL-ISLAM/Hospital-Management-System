/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.LabReportController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import model.Labreport;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_LabreportController implements Initializable {



    
    @FXML
    private TableView<Labreport> labreportTable;
    @FXML
    private JFXTextField searchLabreport;
    @FXML
    private TableColumn<Labreport, String> labNo;
    @FXML
    private TableColumn<Labreport, String> patientId;
    @FXML
    private TableColumn<Labreport, String> doctorId;
    @FXML
    private TableColumn<Labreport, String> patientType;
    @FXML
    private TableColumn<Labreport, Double> amount;
    @FXML
    private TableColumn<Labreport, String> date;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<Labreport>labreportsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        LabReportController labReportController = new LabReportController();
        labreportTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            labreportsList=(ObservableList<Labreport>)labReportController.getAllLabreport();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_LabreportController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        labreportTable.setItems(labreportsList);
        labNo.setCellValueFactory(new PropertyValueFactory<Labreport,String>("labNo"));
        patientId.setCellValueFactory(new PropertyValueFactory<Labreport,String>("patientId"));
        doctorId.setCellValueFactory(new PropertyValueFactory<Labreport,String>("doctorId"));
        patientType.setCellValueFactory(new PropertyValueFactory<Labreport,String>("patientType"));
        amount.setCellValueFactory(new PropertyValueFactory<Labreport,Double>("amount"));
        date.setCellValueFactory(new PropertyValueFactory<Labreport,String>("testDate")); 
       
    }    

    @FXML
    private void searchLabreport(KeyEvent event) {
        if(!"".equals(searchLabreport.getText())){
            String s=searchLabreport.getText();
            ObservableList<Labreport>tempList=FXCollections.observableArrayList();
            for(Labreport labreport : labreportsList){
                if(labreport.getLabNo().contains(s) || labreport.getPatientId().contains(s) || labreport.getDoctorId().contains(s) || labreport.getPatientType().contains(s) ){
                    tempList.add(labreport);
                }
            }
            labreportTable.setItems(tempList);
        }
        else{
            labreportTable.setItems(labreportsList);
        }
    }

    public static Labreport selectLabreport=null;
    public static boolean updatebutton = false;
    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectLabreport=labreportTable.getSelectionModel().getSelectedItem();
        if(selectLabreport==null){
            return;
        }
        updatebutton=true;
        clicked=true;
    }

    public static Stage stage;
    @FXML
    private void labreportAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddLabreport.fxml"));   
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
    private void cancelLabreportAddAction(ActionEvent event) {
        labreportTable.getSelectionModel().clearSelection();
        selectLabreport=null;
        clicked=false;
        updatebutton=false;
    }
    public static boolean update=false;
    @FXML
    private void labreportUpdateAction(ActionEvent event) throws IOException {
        if(updatebutton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddLabreport.fxml"));
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
    private void labreportDelete(ActionEvent event) {
        if(clicked==true){
            LabReportController labReportController = new LabReportController();
            try{

                ObservableList<Labreport> selectItems = labreportTable.getSelectionModel().getSelectedItems();
                for(Labreport labreport : selectItems){
                    labReportController.deleteLabreport(labreport.getLabNo());
                }
                labreportsList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(ex);
            }
        }
        else{
            return;
        }
    }

    
}
