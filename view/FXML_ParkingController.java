/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.ParkingController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import model.Parking;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_ParkingController implements Initializable {

    @FXML
    private JFXTextField searchParking;
    @FXML
    private TableView<Parking> parkingTable;
    @FXML
    private JFXTextField getVehicleNo;
    @FXML
    private JFXTextField getInTime;
    @FXML
    private JFXTextField getOutTime;
    @FXML
    private JFXButton saveParking;
    @FXML
    private TableColumn<Parking,Integer> vehicleNo;
    @FXML
    private TableColumn<Parking,String> inTime;
    @FXML
    private TableColumn<Parking,String> outTime;

    /**
     * Initializes the controller class.
     */
    ObservableList<Parking> parkingList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ParkingController parkingController = new ParkingController() ;
        parkingTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            parkingList=(ObservableList<Parking>)parkingController.getAllParking();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_ParkingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        parkingTable.setItems(parkingList);
        vehicleNo.setCellValueFactory(new PropertyValueFactory<Parking,Integer>("carNo"));
        inTime.setCellValueFactory(new PropertyValueFactory<Parking,String>("inTime"));
        outTime.setCellValueFactory(new PropertyValueFactory<Parking,String>("outTime"));
    }    

    @FXML
    private void searchParking(KeyEvent event) {
        if(!"".equals(searchParking.getText())){
            String s = searchParking.getText();
            ObservableList<Parking> tempList = FXCollections.observableArrayList();
            for(Parking parking : parkingList){
                if(parking.getCarNo().contains(s) ){
                    tempList.add(parking);
                }
            }parkingTable.setItems(tempList);
        }
        else{
            parkingTable.setItems(parkingList);
        }
    }

    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        Parking selectParking = parkingTable.getSelectionModel().getSelectedItem();
        if(selectParking == null){
            return;
        }
        getVehicleNo.setText(selectParking.getCarNo());
        getInTime.setText(selectParking.getInTime().toString());
        getOutTime.setText(selectParking.getOutTime().toString());
        updateParking=selectParking;
        saveParking.setText("Update");
        clicked=true;
        
    }

    Parking updateParking= new Parking();
    @FXML
    private void parkingAdd(ActionEvent event) throws SQLException {
        ParkingController parkingController = new ParkingController();
        if(saveParking.getText().equals("Update")){
            Parking parking = new Parking(getVehicleNo.getText(),Timestamp.valueOf(getInTime.getText()),Timestamp.valueOf(getOutTime.getText()));
            parkingController.updateParking(parking);
            parkingList.remove(updateParking);
            parkingList.add(parking);
            saveParking.setText("Save");
            return;
        }
        if(vehicleNo.getText().length()==0 || inTime.getText().length()==0 || outTime.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                Parking parking = new Parking(getVehicleNo.getText(),Timestamp.valueOf(getInTime.getText()),Timestamp.valueOf(getOutTime.getText()));
                parkingController.insertRoom(parking);
                parkingList.add(parking);
                getVehicleNo.setText("");
                JOptionPane.showMessageDialog(null, "Added");
                return;
            }
            catch(SQLException ex){
                getVehicleNo.setText("");
                JOptionPane.showMessageDialog(null, "Already exist.");
                vehicleNo.setText("");
            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Please valid Input");
            }
            catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Please valid input");
            }
        }
    }

    @FXML
    private void resetParking(ActionEvent event) {
        if(saveParking.getText()=="Update"){
            saveParking.setText("Save");
            getVehicleNo.setText("");
            getInTime.setText("");
            getOutTime.setText("");
            parkingTable.getSelectionModel().clearSelection();
            clicked = false;
        }
    }

    @FXML
    private void deleteParking(ActionEvent event) throws SQLException {
        if(clicked==true){
            ParkingController parkingController = new ParkingController();
            try{
                ObservableList<Parking> selectList=parkingTable.getSelectionModel().getSelectedItems();
                for(Parking parking : selectList){
                    parkingController.deleteParking(parking.getCarNo());
                }
                parkingList.removeAll(selectList);
                 JOptionPane.showMessageDialog(null, "Deleted");
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
    
}
