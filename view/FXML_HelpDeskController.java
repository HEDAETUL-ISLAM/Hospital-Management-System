/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.HelpDeskController;
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
import model.HelpDesk;
import static view.FXML_AppointmentController.stage;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_HelpDeskController implements Initializable {

    @FXML
    private JFXTextField searchhelpDesk;
    @FXML
    private TableView<HelpDesk> helpDeskTable;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskNo;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskName;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskAddress;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskPhone;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskGender;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskType;
    @FXML
    private TableColumn<HelpDesk,String> helpDeskPassword;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<HelpDesk>helpDeskList = FXCollections.observableArrayList();
    @FXML
    private JFXButton addHelpdeskButton;
    @FXML
    private JFXButton cancelHelpdeskButton;
    @FXML
    private JFXButton updateHelpdeskButton;
    @FXML
    private JFXButton deleteHelpdeskButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HelpDeskController helpDeskController = new HelpDeskController();
        helpDeskTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            helpDeskList=(ObservableList<HelpDesk>)helpDeskController.getAllHelpDesk();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_HelpDeskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        helpDeskTable.setItems(helpDeskList);
        helpDeskNo.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("personId"));
        helpDeskName.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("personName"));
        helpDeskAddress.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("personAddress"));
        helpDeskPhone.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("personPhone"));
        helpDeskGender.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("employeeGender"));
        helpDeskType.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("employeeType"));
        helpDeskPassword.setCellValueFactory(new PropertyValueFactory<HelpDesk,String>("helpDeskPassword"));
        if(FXML_LoginController.hdesk==true){
            addHelpdeskButton.setVisible(false);
            cancelHelpdeskButton.setVisible(false);
            updateHelpdeskButton.setVisible(false);
            deleteHelpdeskButton.setVisible(false);
        }
        else{
            addHelpdeskButton.setVisible(true);
            cancelHelpdeskButton.setVisible(true);
            updateHelpdeskButton.setVisible(true);
            deleteHelpdeskButton.setVisible(true);
        }
    }    

    @FXML
    private void searchhelpDesk(KeyEvent event) {
        if(!"".equals(searchhelpDesk.getText())){
            String s = searchhelpDesk.getText();
            ObservableList<HelpDesk>tempList=FXCollections.observableArrayList();
            for(HelpDesk helpDesk : helpDeskList){
                if(helpDesk.getPersonId().contains(s) || helpDesk.getPersonName().contains(s) || helpDesk.getPersonPhone().contains(s) ||helpDesk.getPersonAddress().contains(s) || helpDesk.getEmployeeGender().contains(s) || helpDesk.getHelpDeskPassword().contains(s) || helpDesk.getEmployeeType().contains(s)){
                    tempList.add(helpDesk);
                }
            }
           helpDeskTable.setItems(tempList);
        }
        else{
            helpDeskTable.setItems(helpDeskList);
        }
    }
    public static HelpDesk selectedHelpdesk = null;
    public static boolean updatebutton = false;
    public static boolean clicked = false;
    public static boolean update = false;
    public static Stage stage;

    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectedHelpdesk= helpDeskTable.getSelectionModel().getSelectedItem();
        if(selectedHelpdesk == null){
            return;
        }
        updatebutton=true;
        clicked=true;
    }

    @FXML
    private void helpDeskAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddHelpDesk.fxml"));   
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
    private void cancelhelpDeskAddAction(ActionEvent event) {
        helpDeskTable.getSelectionModel().clearSelection();
        selectedHelpdesk = null;
       clicked=false;
        updatebutton=false;
    }

    @FXML
    private void helpDeskUpdateAction(ActionEvent event) throws IOException {
        if(updatebutton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddHelpDesk.fxml"));
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
    private void helpDeskDelete(ActionEvent event) {
        if(clicked==true){
            HelpDeskController helpDeskController = new HelpDeskController();
            try{
                ObservableList<HelpDesk> selectItems = helpDeskTable.getSelectionModel().getSelectedItems();
                for(HelpDesk helpDesk  : selectItems){
                    helpDeskController.deleteHelpDesk(helpDesk.getPersonId());
                }
                helpDeskList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
                   return;
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
