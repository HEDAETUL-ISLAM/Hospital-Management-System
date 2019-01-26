/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.OperationTheaterController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
import model.OperationTheater;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_OperationTheaterController implements Initializable {

    @FXML
    private JFXTextField searchOT;
    @FXML
    private TableView<OperationTheater> otTable;
    @FXML
    private TableColumn<OperationTheater,Integer> roomNo;
    @FXML
    private TableColumn<OperationTheater,String> patientName;
    @FXML
    private TableColumn<OperationTheater,String> doctorName;
    @FXML
    private JFXTextField getRoomNo;
    @FXML
    private JFXTextField getPatientName;
    @FXML
    private JFXTextField getDoctorName;
    @FXML
    private JFXButton saveOperationTheater;

    /**
     * Initializes the controller class.
     */
    ObservableList<OperationTheater> otList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OperationTheaterController operationTheaterController = new OperationTheaterController();
        otTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            otList=(ObservableList<OperationTheater>)operationTheaterController.getAllOperationTheater();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_OperationTheaterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        otTable.setItems(otList);
        roomNo.setCellValueFactory(new PropertyValueFactory<OperationTheater,Integer>("roomNo"));
        patientName.setCellValueFactory(new PropertyValueFactory<OperationTheater,String>("patientName"));
        doctorName.setCellValueFactory(new PropertyValueFactory<OperationTheater,String>("doctorName"));
    }    

    @FXML
    private void searchOT(KeyEvent event) {
        if(!"".equals(searchOT.getText())){
            String string = searchOT.getText();
            ObservableList<OperationTheater> tempList = FXCollections.observableArrayList();
            for(OperationTheater operationTheater : otList){
                if(operationTheater.getDoctorName().contains(string) || operationTheater.getPatientName().contains(string) || Integer.toString(operationTheater.getRoomNo()).contains(string)){
                    tempList.add(operationTheater);
                }
            }
            otTable.setItems(tempList);
        }
        else{
            otTable.setItems(otList);
        }
    }

    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        OperationTheater selectedOperationTheater = otTable.getSelectionModel().getSelectedItem();
        if(selectedOperationTheater==null){
            return;
        }
        getRoomNo.setText(Integer.toString(selectedOperationTheater.getRoomNo()));
        getPatientName.setText(selectedOperationTheater.getPatientName());
        getDoctorName.setText(selectedOperationTheater.getDoctorName());
        updateOperationTheater=selectedOperationTheater;
        saveOperationTheater.setText("Update");
        clicked=true;
    }

    OperationTheater updateOperationTheater = new OperationTheater();
    @FXML
    private void otAdd(ActionEvent event) throws SQLException {
        OperationTheaterController operationTheaterController = new OperationTheaterController();
        if(saveOperationTheater.getText().equals("Update")){
            OperationTheater operationTheater = new OperationTheater(Integer.parseInt(getRoomNo.getText()), getPatientName.getText(), getDoctorName.getText());
            operationTheaterController.updateOperationController(operationTheater);
            otList.remove(updateOperationTheater);
            otList.add(operationTheater);
            saveOperationTheater.setText("Save");
            return;
        }
        if(roomNo.getText().length()==0 || patientName.getText().length()==0 || doctorName.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                OperationTheater operationTheater = new OperationTheater(Integer.parseInt(getRoomNo.getText()), getPatientName.getText(), getDoctorName.getText());
                operationTheaterController.insertOperationTheater(operationTheater);
                otList.add(operationTheater);
                JOptionPane.showMessageDialog(null, "Added");
                return;
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Already exist.");
                roomNo.setText("");
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
    private void resetOT(ActionEvent event) {
        if(saveOperationTheater.getText()=="Update"){
            saveOperationTheater.setText("Save");
            getRoomNo.setText("");
            getPatientName.setText("");
            getDoctorName.setText("");
            otTable.getSelectionModel().clearSelection();
            clicked=false;
        }
    }

    @FXML
    private void deleteOT(ActionEvent event) throws SQLException {
        if(clicked==true){
            OperationTheaterController operationTheaterController = new OperationTheaterController();
            try{
                 ObservableList<OperationTheater> selectItems = otTable.getSelectionModel().getSelectedItems();
                 for(OperationTheater operationTheater : selectItems){
                     operationTheaterController.deleteOperationTheater(operationTheater.getRoomNo());
                 }
                 otList.removeAll(selectItems);
                  JOptionPane.showMessageDialog(null, "Deleted ");
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
