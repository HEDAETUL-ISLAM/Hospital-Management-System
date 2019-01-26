/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.MedicineController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Medicine;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddMedicineController implements Initializable {

    @FXML
    private JFXTextField medicineNoAddField;
    @FXML
    private JFXTextField medicineNameAddField;
    @FXML
    private JFXTextField medicineTypeAddfield;
    @FXML
    private JFXTextField medicineCompanyAddField;
    @FXML
    private JFXTextField medicineCostAddField;
    @FXML
    private BorderPane addMedicine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXML_MedicineController.selectMedicine != null){
            Medicine medicine = FXML_MedicineController.selectMedicine;
            medicineNoAddField.setText(medicine.getMedicineId());
            FXML_AppointmentController.selectAppointment=null;
        }
    }    

    public static FXML_MedicineController fXML_MedicineController = new FXML_MedicineController();
    @FXML
    private void saveMEDICINEAction(ActionEvent event) throws SQLException {
        MedicineController medicineController = new MedicineController();
        if(medicineNoAddField.getText().length()==0 || medicineNameAddField.getText().length()==0 || medicineTypeAddfield.getText().length()==0 || medicineCompanyAddField.getText().length()==0 || medicineCostAddField.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                Medicine medicine = new Medicine(medicineNoAddField.getText(), medicineNameAddField.getText(), medicineTypeAddfield.getText(), medicineCompanyAddField.getText(), Double.parseDouble(medicineCostAddField.getText()));
                if(FXML_MedicineController.update==false){
                    medicineController.insertMedicine(medicine);
                    FXML_MedicineController.medicineList.add(medicine);
                }
                else{
                    medicineController.updateMedicine(medicine);
                    FXML_MedicineController.medicineList.add(medicine);
                    FXML_MedicineController.update=false;
                }
                JOptionPane.showMessageDialog(null, "Added");
                medicineNoAddField.setText("");
                medicineNameAddField.setText("");
                medicineTypeAddfield.setText("");
                medicineCostAddField.setText("");
                medicineCompanyAddField.setText("");
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Already exist.");
                medicineNoAddField.setText("");
                medicineNameAddField.setText("");
                medicineTypeAddfield.setText("");
                medicineCostAddField.setText("");
                medicineCompanyAddField.setText("");
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
    private void cancelAddMedicine(ActionEvent event) {
        FXML_MedicineController.stage.close();
    }
    
}
