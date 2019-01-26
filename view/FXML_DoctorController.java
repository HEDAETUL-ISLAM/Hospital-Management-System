/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.DoctorController;
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
import model.Doctor;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_DoctorController implements Initializable {

    @FXML
    private JFXTextField searchDoctor;
    @FXML
    private TableView<Doctor> drTable;
    @FXML
    private TableColumn<Doctor, String> drNo;
    @FXML
    private TableColumn<Doctor, String> drName;
    @FXML
    private TableColumn<Doctor, String> drAddress;
    @FXML
    private TableColumn<Doctor, String> drPhone;
    @FXML
    private TableColumn<Doctor, String> drDepartment;
    @FXML
    private TableColumn<Doctor, String> drGender;
    @FXML
    private TableColumn<Doctor, String> drQualification;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<Doctor>drList = FXCollections.observableArrayList();
    @FXML
    private JFXButton addDoctorButton;
    @FXML
    private JFXButton cancelDoctorButton;
    @FXML
    private JFXButton updateDoctorButton;
    @FXML
    private JFXButton deleteDoctorButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            DoctorController doctorController = new DoctorController();
            drTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            drList=(ObservableList<Doctor>)doctorController.getAllDoctor();
        }
        catch (SQLException ex) {
            Logger.getLogger(FXML_DoctorController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }System.out.println("hello");
        drTable.setItems(drList);
        drNo.setCellValueFactory(new PropertyValueFactory<Doctor,String>("personId"));
        drName.setCellValueFactory(new PropertyValueFactory<Doctor,String>("personName"));
        drAddress.setCellValueFactory(new PropertyValueFactory<Doctor,String>("personAddress"));
        drPhone.setCellValueFactory(new PropertyValueFactory<Doctor,String>("personPhone"));
        drDepartment.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorDepartment"));
        drGender.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorGender"));
        drQualification.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorQualification"));
        if(FXML_LoginController.hdesk==true){
            addDoctorButton.setVisible(false);
            cancelDoctorButton.setVisible(false);
            updateDoctorButton.setVisible(false);
            deleteDoctorButton.setVisible(false);
        }
        else{
            addDoctorButton.setVisible(true);
            cancelDoctorButton.setVisible(true);
            updateDoctorButton.setVisible(true);
            deleteDoctorButton.setVisible(true);
        }
    }



    public static boolean clicked = false;

    @FXML
    private void tableItemClicked(MouseEvent event) {
        Doctor selectDoctor = drTable.getSelectionModel().getSelectedItem();
        if(selectDoctor == null){
            return;
        }
        updateButton=true;
        clicked = true;
    }

    public static Stage stage;
    @FXML
    private void drAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddDoctor.fxml"));
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
    private void cancelDrAddAction(ActionEvent event) {
        drTable.getSelectionModel().clearSelection();
        selectDoctor = null;
        clicked=false;
        updateButton=false;
    }
    public static Doctor selectDoctor = null;
    public static boolean updateButton = false;
    public static boolean update = false;
    @FXML
    private void drUpdateAction(ActionEvent event) throws IOException {
        if(updateButton == true){
            selectDoctor=drTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddDoctor.fxml"));
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
    private void drDelete(ActionEvent event) {
         if(clicked == true){
            DoctorController doctorController = new DoctorController();

             try{
                ObservableList<Doctor> selectItems = drTable.getSelectionModel().getSelectedItems();
                for(Doctor appointment : selectItems){
                    doctorController.deleteDoctor(appointment.getPersonId());
                }
                drList.removeAll(selectItems);
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
    private void searchDoctor(KeyEvent event) {

        if(!"".equals(searchDoctor.getText())){
            String s = searchDoctor.getText();
            ObservableList<Doctor>tempList=FXCollections.observableArrayList();
            for(Doctor doctror : drList){
                if(doctror.getPersonId().contains(s) || doctror.getPersonName().contains(s) ||doctror.getPersonAddress().contains(s)|| doctror.getPersonPhone().contains(s) || doctror.getDoctorDepartment().contains(s)  || doctror.getDoctorGender().contains(s) || doctror.getDoctorQualification().contains(s)){
                    tempList.add(doctror);
                }
            }
           drTable.setItems(tempList);
        }
        else{
            drTable.setItems(drList);
        }

   }
}
