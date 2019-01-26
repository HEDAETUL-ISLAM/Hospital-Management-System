/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.AppointmentController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Appointment;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddAppointmentController implements Initializable {

    @FXML
    private BorderPane addAppoint;
    @FXML
    private JFXTextField appointNoAdd;
    @FXML
    private JFXTextField doctorNoAdd;
    @FXML
    private JFXTextField patientIdAdd;
    @FXML
    private JFXTextField diseaseAdd;
    @FXML
    private JFXButton cancelAppoint;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_AppointmentController.selectAppointment!=null){
            Appointment appointment=FXML_AppointmentController.selectAppointment;
            appointNoAdd.setText(appointment.getAppointNo());

            FXML_AppointmentController.selectAppointment=null;
        }

    }

    public static FXML_AppointmentController fXML_AppointmentController = new FXML_AppointmentController();
    @FXML
    private void saveAppointAction(ActionEvent event) throws Exception{

        AppointmentController appointmentController = new AppointmentController();

        if(appointNoAdd.getText().length()==0 || doctorNoAdd.getText().length()==0 || patientIdAdd.getText().length()==0 || diseaseAdd.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{

            try {

                 Appointment appointment = new Appointment(appointNoAdd.getText(),doctorNoAdd.getText(),patientIdAdd.getText(),diseaseAdd.getText(),LocalDate.now());
                 if(FXML_AppointmentController.update==false){
                    appointmentController.insertAppointment(appointment);
                    FXML_AppointmentController.appointmentsList.add(appointment);
                 }
                 else{
                    appointmentController.updateAppointment(appointment);
                    FXML_AppointmentController.appointmentsList.add(appointment);
                    FXML_AppointmentController.update=false;
                 }

                 JOptionPane.showMessageDialog(null, "Added");
                 appointNoAdd.setText("");
                 doctorNoAdd.setText("");
                 patientIdAdd.setText("");
                 diseaseAdd.setText("");
                 return;

            }
            catch (SQLException ex) {
                //Logger.getLogger(FXML_AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Already exist.");
                appointNoAdd.setText("");
                doctorNoAdd.setText("");
                patientIdAdd.setText("");
                diseaseAdd.setText("");
                System.out.println(ex);

            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Please valid input");
            }
            catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Please valid input");
            }
        }
    }

    @FXML
    private void cancelAddAppoint(ActionEvent event) {

        FXML_AppointmentController.stage.close();
    }

}
