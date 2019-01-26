/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.LabReportController;
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
import model.Labreport;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddLabreportController implements Initializable {

    @FXML
    private BorderPane addBill;
    @FXML
    private JFXTextField labNoAddField;
    @FXML
    private JFXTextField patientIdAddField;
    @FXML
    private JFXTextField doctorIDAddfield;
    @FXML
    private JFXTextField patientTypeAddField;
    @FXML
    private JFXTextField amountAddField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_LabreportController.selectLabreport != null){
            Labreport labreport=FXML_LabreportController.selectLabreport;
            labNoAddField.setText(labreport.getLabNo());
            FXML_LabreportController.selectLabreport=null;
        }
    }    

    public static FXML_LabreportController fXML_LabreportController= new FXML_LabreportController();
    @FXML
    private void saveLabreportAction(ActionEvent event) {
        LabReportController labReportController = new LabReportController();
        if(labNoAddField.getText().length()==0||patientIdAddField.getText().length()==0||doctorIDAddfield.getText().length()==0||patientTypeAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                Labreport labreport = new Labreport(labNoAddField.getText(), patientIdAddField.getText(), doctorIDAddfield.getText(), patientTypeAddField.getText(), Double.parseDouble(amountAddField.getText()), LocalDate.now());
                if(FXML_LabreportController.update==false){
                    labReportController.insertLabreport(labreport);
                }
                else{
                    labReportController.updateLabreport(labreport);
                    FXML_LabreportController.update=false;
                }
                FXML_LabreportController.labreportsList.add(labreport);
                JOptionPane.showMessageDialog(null, "Added.");
                
            }
            catch(SQLException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Already exist.");
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
    private void cancelAddLabreport(ActionEvent event) {
        FXML_LabreportController.stage.close();
    }
    
}
//labNoAddField.setText("");
//                 patientIdAddField.setText("");
//                 doctorIDAddfield.setText("");
//                 patientTypeAddField.setText("");
//                 amountAddField.setText("");