/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.MortuaryController;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Formatter;
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
import model.Mortuary;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_MortuaryController implements Initializable {

    @FXML
    private JFXTextField searchMortuary;
    @FXML
    private TableView<Mortuary> mortuaryTable;
    @FXML
    private TableColumn<Mortuary,Integer> roomNo;
    @FXML
    private TableColumn<Mortuary, String> patientId;
    @FXML
    private TableColumn<Mortuary,String> patientName;
    @FXML
    private TableColumn<Mortuary,String> doctorName;
    @FXML
    private TableColumn<Mortuary,String> problem;
    @FXML
    private TableColumn<Mortuary,String> dateOfDead;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<Mortuary>mortuaryList= FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MortuaryController mortuaryController = new MortuaryController();
        mortuaryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            mortuaryList=(ObservableList<Mortuary>)mortuaryController.getAllMortuary();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_MortuaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mortuaryTable.setItems(mortuaryList);
        roomNo.setCellValueFactory(new PropertyValueFactory<Mortuary,Integer>("roomNo"));
        patientId.setCellValueFactory(new PropertyValueFactory<Mortuary,String>("patientId"));
        patientName.setCellValueFactory(new PropertyValueFactory<Mortuary,String>("patientName"));
        doctorName.setCellValueFactory(new PropertyValueFactory<Mortuary,String>("doctorName"));
        problem.setCellValueFactory(new PropertyValueFactory<Mortuary,String>("problem"));
        dateOfDead.setCellValueFactory(new PropertyValueFactory<Mortuary,String>("dateOfDead"));
        
    }    


    public static Mortuary selectMortuary=null;
    public static boolean updateButton = false;
    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectMortuary=mortuaryTable.getSelectionModel().getSelectedItem();
        if(selectMortuary==null){
            return;
        }
        updateButton=true;
        clicked=true;
    }

    @FXML
    private void searchMortuary(KeyEvent event) {
        if(!"".equals(searchMortuary.getText())){
            String s = searchMortuary.getText();
            ObservableList<Mortuary>temList=FXCollections.observableArrayList();
            for(Mortuary mortuary : mortuaryList){
                if(mortuary.getPatientId().contains(s) || Integer.toString(mortuary.getRoomNo()).contains(s) || mortuary.getPatientName().contains(s) || mortuary.getDoctorName().contains(s)){
                    temList.add(mortuary);
                }
            }
            mortuaryTable.setItems(temList);
        }
        else{
            mortuaryTable.setItems(mortuaryList);
        }
    }

    public static Stage stage;
    @FXML
    private void mortuaryAddAction(ActionEvent event) throws IOException {
        if(clicked == false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddMortuary.fxml"));   
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
    private void cancelMortuaryAddAction(ActionEvent event) {
        mortuaryTable.getSelectionModel().clearSelection();
        selectMortuary=null;
        clicked=false;
        updateButton=false;
    }

    public static boolean update=false;
    @FXML
    private void mortuaryUpdateAction(ActionEvent event) throws IOException {
        if(updateButton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddMortuary.fxml"));
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
    private void mortuaryDelete(ActionEvent event) {
        if(clicked==true){
            MortuaryController mortuaryController = new MortuaryController();
            try{
                ObservableList<Mortuary> selectItems = mortuaryTable.getSelectionModel().getSelectedItems();
                for(Mortuary mortuary : selectItems){
                    mortuaryController.deleteMortuary(mortuary.getRoomNo());
                }
                mortuaryList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
            }
            catch(SQLException exception){
                JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(exception);
            }
        }
        else{
            return;
        }
    }
    
}
