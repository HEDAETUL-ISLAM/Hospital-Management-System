/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.BillController;
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
import model.Bill;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_BillController implements Initializable {

    @FXML
    private JFXTextField searchBill;
    
    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill, String> billNo;
    @FXML
    private TableColumn<Bill, String> patientId;
    @FXML
    private TableColumn<Bill, Double> doctorCharge;
    @FXML
    private TableColumn<Bill, Double> medicineCharge;
    @FXML
    private TableColumn<Bill, Double> roomCharge;
    @FXML
    private TableColumn<Bill, Double> otCharge;
    @FXML
    private TableColumn<Bill, Integer> noOfDays;
    @FXML
    private TableColumn<Bill, Double> healthCard;
    @FXML
    private TableColumn<Bill, Double> labCharge;
    @FXML
    private TableColumn<Bill, Double> advancePay;
    @FXML
    private TableColumn<Bill, Double> totalBill;
    @FXML
    private TableColumn<Bill, Double> due;
    @FXML
    private JFXButton getBill;
    @FXML
    private JFXButton cancelBill;
    @FXML
    private JFXButton updateBill;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<Bill>billList = FXCollections.observableArrayList();
    @FXML
    private JFXButton printButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            BillController billController = new BillController();
            billTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            
            billList=(ObservableList<Bill>)billController.getAllBill();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        billTable.setItems(billList);
        billNo.setCellValueFactory(new PropertyValueFactory<Bill,String>("billNo"));
        patientId.setCellValueFactory(new PropertyValueFactory<Bill,String>("patientId"));
        doctorCharge.setCellValueFactory(new PropertyValueFactory<Bill,Double>("doctorCharge"));
        medicineCharge.setCellValueFactory(new PropertyValueFactory<Bill,Double>("medicineCharge"));
        roomCharge.setCellValueFactory(new PropertyValueFactory<Bill,Double>("roomCharge"));
        otCharge.setCellValueFactory(new PropertyValueFactory<Bill,Double>("operationCharge"));
        noOfDays.setCellValueFactory(new PropertyValueFactory<Bill,Integer>("noOfDays"));
        healthCard.setCellValueFactory(new PropertyValueFactory<Bill,Double>("healthCard"));
        labCharge.setCellValueFactory(new PropertyValueFactory<Bill,Double>("labCharge"));
        advancePay.setCellValueFactory(new PropertyValueFactory<Bill,Double>("advancePay"));
        totalBill.setCellValueFactory(new PropertyValueFactory<Bill,Double>("totalBill"));
        due.setCellValueFactory(new PropertyValueFactory<Bill,Double>("due"));
    }    

    @FXML
    private void searchBill(KeyEvent event) {
        if(!"".equals(searchBill.getText())){
            String s = searchBill.getText();
            ObservableList<Bill>tempList=FXCollections.observableArrayList();
            for(Bill bill:billList){
                if(bill.getBillNo().toString().contains(s) || bill.getPatientId().toString().contains(s) || (bill.getDoctorCharge()+"").contains(s) || (bill.getMedicineCharge()+"").contains(s) || (bill.getRoomCharge()+"").contains(s) || (bill.getOperationCharge()+"").contains(s)|| (bill.getNoOfDays()+"").contains(s) || (bill.getHealthCard()+"").contains(s) || (bill.getLabCharge()+"").contains(s) || (bill.getAdvancePay()+"").contains(s) ||(bill.getTotalBill()+"").contains(s)){
                    tempList.add(bill);
                }
            }
           billTable.setItems(tempList);
        }
        else{
            billTable.setItems(billList);
        }
    }

    public static Bill selectBill = null;
    public static boolean updatebutton = false;
    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        Bill selectBill = billTable.getSelectionModel().getSelectedItem();
        if(selectBill == null){
            return;
        }
        updatebutton=true;
        clicked = true;
    }
    public static Stage stage;
    @FXML
    private void billAddAction(ActionEvent event) throws IOException {
        if(clicked == false){   
             Parent root = FXMLLoader.load(getClass().getResource("FXML_AddBill.fxml"));  
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
    private void cancelBillAddAction(ActionEvent event) {
        billTable.getSelectionModel().clearSelection();
        selectBill = null;
        clicked=false;
        updatebutton=false;
    }

    public static boolean update=false;
    @FXML
    private void billUpdateAction(ActionEvent event) throws IOException {
        if(updatebutton == true){
            selectBill=billTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddBill.fxml"));
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
    private void billDelete(ActionEvent event) {
        if(clicked == true){
            BillController billController = new BillController();

             try{

                ObservableList<Bill> selectItems = billTable.getSelectionModel().getSelectedItems();
                for(Bill appointment : selectItems){
                    billController.deleteBill(appointment.getBillNo());
                }
                billList.removeAll(selectItems);
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

    @FXML
    private void printButtonAction(ActionEvent event) throws IOException {
        if(updatebutton == true){
            selectBill=billTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_BillVoucher.fxml"));
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
