/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.BillController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import model.Bill;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AddBillController implements Initializable {

    @FXML
    private JFXTextField billNoAddField;
    @FXML
    private JFXTextField patientIdAddField;
    @FXML
    private JFXTextField drChargeAddfield;
    @FXML
    private JFXTextField mediChargeAddField;
    @FXML
    private JFXTextField roomChargeAddField;
    @FXML
    private JFXTextField otChargeAddfield;
    @FXML
    private JFXTextField noOfDaysAddField;
    @FXML
    private JFXTextField healthCardAddField;
    @FXML
    private JFXTextField labChargeAddField;
    @FXML
    private JFXTextField advancePayAddField;
    @FXML
    private BorderPane addBill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(FXML_BillController.selectBill != null){
            
            Bill bill = FXML_BillController.selectBill;
            billNoAddField.setText(bill.getBillNo());
            
            FXML_BillController.selectBill=null;
        }
        
    }    


    @FXML
    private void cancelAddBill(ActionEvent event) {
        FXML_BillController.stage.close();
    }

    public static FXML_BillController fXML_BillController = new FXML_BillController();
    @FXML
    private void saveBillAction(ActionEvent event) throws Exception{
        BillController billController = new BillController();
        
        if(billNoAddField.getText().length() == 0 || patientIdAddField.getText().length()==0 || drChargeAddfield.getText().length() == 0 || mediChargeAddField.getText().length()==0 || roomChargeAddField.getText().length() ==0 || otChargeAddfield.getText().length() == 0 || noOfDaysAddField.getText().length() ==0 || healthCardAddField.getText().length()==0||labChargeAddField.getText().length()==0 ||advancePayAddField.getText().length()==0){
             JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{

            try{
                Bill bill = new Bill(billNoAddField.getText(), patientIdAddField.getText(), Double.parseDouble(drChargeAddfield.getText()), Double.parseDouble(mediChargeAddField.getText()), Double.parseDouble(roomChargeAddField.getText()), Double.parseDouble(otChargeAddfield.getText()), Integer.parseInt(noOfDaysAddField.getText()), Double.parseDouble(healthCardAddField.getText()), Double.parseDouble(labChargeAddField.getText()),Double.parseDouble(advancePayAddField.getText()));
                if(FXML_BillController.update == false){
                    billController.insertBill(bill);
                }
                else{
                    billController.updateBill(bill);
                    fXML_BillController.update=false;
                }
                fXML_BillController.billList.add(bill);
                JOptionPane.showMessageDialog(null, "Added.");
                billNoAddField.setText("");
                patientIdAddField.setText("");
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Already exist.");
                billNoAddField.setText("");
                patientIdAddField.setText("");
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
    
}
