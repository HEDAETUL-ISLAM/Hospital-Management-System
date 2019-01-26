/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.MortuaryController;
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
import model.Mortuary;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddMortuaryController implements Initializable {

    @FXML
    private BorderPane addMortuary;
    @FXML
    private JFXTextField mortuaryNoAddField;
    @FXML
    private JFXTextField patientIDAddField;
    @FXML
    private JFXTextField patientNameAddfield;
    @FXML
    private JFXTextField doctoeNameAddField;
    @FXML
    private JFXTextField problemAddField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_MortuaryController.selectMortuary!=null){
            Mortuary mortuary=FXML_MortuaryController.selectMortuary;
            mortuaryNoAddField.setText(Integer.toString(mortuary.getRoomNo()));
            
            FXML_MortuaryController.selectMortuary=null;
        }
    }    

    public static FXML_MortuaryController fXML_MortuaryController = new FXML_MortuaryController();
    @FXML
    private void saveMortuaryAction(ActionEvent event) throws Exception{
        MortuaryController mortuaryController = new MortuaryController();
        if(mortuaryNoAddField.getText().length()==0 || patientIDAddField.getText().length()==0 || patientNameAddfield.getText().length()==0 || doctoeNameAddField.getText().length()==0 || problemAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                Mortuary mortuary = new Mortuary(Integer.parseInt( mortuaryNoAddField.getText()), patientIDAddField.getText(), patientNameAddfield.getText(), doctoeNameAddField.getText(), problemAddField.getText(), LocalDate.now());
                if(FXML_MortuaryController.update==false){
                    mortuaryController.insertMortuary(mortuary);
                    FXML_MortuaryController.mortuaryList.add(mortuary);
                }
                else{
                    mortuaryController.updateMortuary(mortuary);
                    FXML_MortuaryController.mortuaryList.add(mortuary);
                    FXML_MortuaryController.update=false;
                    
                }
                mortuaryNoAddField.setText("");
                JOptionPane.showMessageDialog(null, "Added.");
                return;
            }
            catch (SQLException ex) {
                //Logger.getLogger(FXML_AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Already exist.");
                mortuaryNoAddField.setText("");
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
    private void cancelAddMortuary(ActionEvent event) {
        FXML_MortuaryController.stage.close();
    }
    
}
