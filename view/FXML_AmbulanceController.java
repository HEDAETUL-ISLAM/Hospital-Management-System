/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.AmbulanceController;
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
import model.Ambulance;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_AmbulanceController implements Initializable {

    @FXML
    private TableView<Ambulance> ambulanceTable;
    @FXML
    private TableColumn<Ambulance, String> ambulanceno;
    @FXML
    private TableColumn<Ambulance, String> drivername;
    @FXML
    private JFXTextField getambulanceno;
    @FXML
    private JFXTextField getdriver;
    @FXML
    private JFXButton saveAmbulance;
    @FXML
    private JFXTextField searchAmbulance;

    

    /**
     * Initializes the controller class.
     */
 
    ObservableList<Ambulance> ambulanceList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        AmbulanceController ambulanceController = new AmbulanceController();
        ambulanceTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            ambulanceList= (ObservableList<Ambulance>)ambulanceController.getAllAmbulance();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_AmbulanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ambulanceTable.setItems(ambulanceList);
        ambulanceno.setCellValueFactory(new PropertyValueFactory<Ambulance,String>("ambulanceNo"));
        drivername.setCellValueFactory(new PropertyValueFactory<Ambulance,String>("driverName"));
        ambulanceTable.setEditable(false);
    }    
    
    Ambulance updatedAmbulance=new Ambulance();
    
    @FXML
    private void ambulanceAdd(ActionEvent event) throws SQLException  {
         AmbulanceController ambulanceController = new AmbulanceController();
        
        if(saveAmbulance.getText().equals("Update")){
            System.out.println("update");
              Ambulance ambulance= new Ambulance(getambulanceno.getText(), getdriver.getText());
              ambulanceController.updatAmbulance(ambulance);
              ambulanceList.remove(updatedAmbulance);
              ambulanceList.add(ambulance);
            
            saveAmbulance.setText("Save");
            return;
        }
        
        if(getambulanceno.getText().length()==0 || getdriver.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try {
                Ambulance ambulance= new Ambulance(getambulanceno.getText(), getdriver.getText());
                ambulanceController.insertAmbulance(ambulance);
                ambulanceList.add(ambulance);
            } 
            catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Already exist.");
                getambulanceno.setText("");
                getdriver.setText("");
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
    private void deleteAmbulance(ActionEvent event) throws SQLException {
        if(clicked==true){
            AmbulanceController ambulanceController = new AmbulanceController();


                try{

                   ObservableList<Ambulance> selectedItems = ambulanceTable.getSelectionModel().getSelectedItems();
                   for(Ambulance ambulance:selectedItems){
                        ambulanceController.deleteAmbulance(ambulance.getAmbulanceNo());
                   }
                   ambulanceList.removeAll(selectedItems);

                    System.out.println(selectedItems);

                   JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;

                }catch(Exception ex){
                     JOptionPane.showMessageDialog(null, "Wrong ");
                    System.out.println(ex);
                }
        }
        else{
            return;
        }
    }
    
    public static Ambulance ambulance = null;

    @FXML
    private void searchAmbulance(KeyEvent event) throws SQLException {
         if(!"".equals(searchAmbulance.getText())){
             String s=searchAmbulance.getText();
             
              ObservableList<Ambulance> tempList = FXCollections.observableArrayList();
              
              for(Ambulance ambulance:ambulanceList){
                  if(ambulance.getAmbulanceNo().contains(s)  || ambulance.getDriverName().contains(s) ){
                      tempList.add(ambulance);
                  }
              }
              ambulanceTable.setItems(tempList);
             
           
        }else{
             ambulanceTable.setItems(ambulanceList);
         }
    }

    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        
        Ambulance selectedAmbulance=ambulanceTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedAmbulance);
        if(selectedAmbulance == null) {
            //ambulanceTable.getSelectionModel().clearSelection();
            return;
        }
        getambulanceno.setText(selectedAmbulance.getAmbulanceNo());
        getdriver.setText(selectedAmbulance.getDriverName());
        updatedAmbulance=selectedAmbulance;
        saveAmbulance.setText("Update");
        clicked=true;
    }

    @FXML
    private void resetAmbulance(ActionEvent event) {
        if(saveAmbulance.getText()=="Update"){
            saveAmbulance.setText("Save");
            getambulanceno.setText("");
            getdriver.setText("");
            ambulanceTable.getSelectionModel().clearSelection();
            clicked=false;
        }
        
    }


    
}
