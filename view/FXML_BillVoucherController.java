/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.HospitalController;
import com.jfoenix.controls.JFXButton;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javax.swing.text.Document;
import model.Bill;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_BillVoucherController implements Initializable{

    @FXML
    private Label hospitalIDField;
    @FXML
    private Label hospitalNameField;
    @FXML
    private Label hospitalAddressField;
    @FXML
    private Label patientIdField;
    @FXML
    private Label billNoAddFiels;
    @FXML
    private Label doctorCharge;
    @FXML
    private Label medicineCharge;
    @FXML
    private Label roomCharge;
    @FXML
    private Label otCharge;
    @FXML
    private Label labCharge;
    @FXML
    private Label noOfDays;
    @FXML
    private Label healthCard;
    @FXML
    private Label advancePay;
    @FXML
    private Label totalBill;
    @FXML
    private Label due;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton printButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HospitalController hospitalController = new HospitalController();
        Bill bill = FXML_BillController.selectBill;
            billNoAddFiels.setText(bill.getBillNo());
            patientIdField.setText(bill.getPatientId());

            doctorCharge.setText(Double.toString(bill.getDoctorCharge()));
            medicineCharge.setText(Double.toString(bill.getMedicineCharge()));
            roomCharge.setText(Double.toString(bill.getRoomCharge()));
            otCharge.setText(Double.toString(bill.getOperationCharge()));
            labCharge.setText(Double.toString(bill.getLabCharge()));
            noOfDays.setText(Integer.toString(bill.getNoOfDays()));
            healthCard.setText(Double.toString(bill.getHealthCard()));
            advancePay.setText(Double.toString(bill.getAdvancePay()));
            totalBill.setText(Double.toString(bill.getTotalBill()));
            due.setText(Double.toString(bill.getDue()));
            FXML_BillController.selectBill=null;


        try {
            Object array[][]=hospitalController.gethospital();
            hospitalIDField.setText((String) array[0][0]);
            hospitalNameField.setText((String) array[0][1]);
            hospitalAddressField.setText((String) array[0][2]);

        } catch (SQLException ex) {
            Logger.getLogger(HospitalDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private FileChooser fileChooser = new FileChooser();
    @FXML
    private void printButtonAction(ActionEvent event) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        fileChooser.setTitle("save to pdf");
        fileChooser.setInitialFileName("demp.pdf");
        
        printButton.setOnAction((ActionEvent event1) -> {
            File file =fileChooser.showSaveDialog(FXML_BillController.stage);
            if(file!=null){
                String string = file.getAbsolutePath();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(string);
                    PDF pdf = new PDF(fileOutputStream);
                    Page page = new Page(pdf, Letter.PORTRAIT);
                    
                    pdf.close();
                    fileOutputStream.flush();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXML_BillVoucherController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FXML_BillVoucherController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        FXML_BillController.stage.close();
    }



}
