/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.AppointmentController;
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
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AppointmentController implements Initializable {

    @FXML
    private JFXTextField searchAppointment;
    @FXML
     private TableView<Appointment> appointTable;
    @FXML
    private TableColumn<Appointment, String> appointNo;
    @FXML
    private TableColumn<Appointment, String> doctorId;
    @FXML
    private TableColumn<Appointment, String> patientId;
    @FXML
    private TableColumn<Appointment, String> type;
    @FXML
    private TableColumn<Appointment, String> appointDate;

    /**
     * initial
     */
    public static ObservableList<Appointment>appointmentsList = FXCollections.observableArrayList();
    @FXML
    private JFXButton getAppoint;
    @FXML
    private JFXButton cancelAppoint;
    @FXML
    private JFXButton updateAppointment;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AppointmentController appointmentController = new AppointmentController();
        appointTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            appointmentsList=(ObservableList<Appointment>)appointmentController.getAllAppointment();
        }
        catch (SQLException ex) {
                Logger.getLogger(FXML_AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
         }
        appointTable.setItems(appointmentsList);
        appointNo.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appointNo"));
        doctorId.setCellValueFactory(new PropertyValueFactory<Appointment,String>("doctorId"));
        patientId.setCellValueFactory(new PropertyValueFactory<Appointment,String>("patientId"));
        type.setCellValueFactory(new PropertyValueFactory<Appointment,String>("type"));
        appointDate.setCellValueFactory(new PropertyValueFactory<Appointment,String>("appointDate"));
        appointTable.setEditable(false);
    }
    public static Appointment selectAppointment = null;
    public static boolean updatebutton = false;
    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectAppointment =appointTable.getSelectionModel().getSelectedItem();
        if(selectAppointment == null){
            return;
        }
        updatebutton = true;
        clicked = true;
    }


    @FXML
    private void appointmentDelete(ActionEvent event) throws SQLException{
        if(clicked==true){
            AppointmentController appointmentController = new AppointmentController();

            try{

                ObservableList<Appointment> selectItems = appointTable.getSelectionModel().getSelectedItems();
                for(Appointment appointment : selectItems){
                    appointmentController.deleteAppointment(appointment.getAppointNo());
                }
                appointmentsList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(ex);
            }
        }
        else{
            return ;
        }
    }

    @FXML
    private void searchAppoint(KeyEvent event) {
        if(!"".equals(searchAppointment.getText())){
            String s = searchAppointment.getText();
            ObservableList<Appointment>tempList=FXCollections.observableArrayList();
            for(Appointment appointment:appointmentsList){
                if(appointment.getAppointNo().contains(s) || appointment.getDoctorId().contains(s) || appointment.getPatientId().contains(s) || appointment.getType().contains(s)){
                    tempList.add(appointment);
                }
            }
           appointTable.setItems(tempList);
        }
        else{
            appointTable.setItems(appointmentsList);
        }
    }
    public static Stage stage;
    @FXML
    private void appointmentAddAction(ActionEvent event) throws IOException {
        if(clicked == false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddAppointment.fxml"));
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
    private void cancelAppointmentAddAction(ActionEvent event) {
       appointTable.getSelectionModel().clearSelection();
       selectAppointment = null;
       clicked=false;
        updatebutton=false;
    }
    public static boolean update=false;
    @FXML
    private void appointmentUpdateAction(ActionEvent event) throws IOException {
        if(updatebutton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddAppointment.fxml"));
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



}
