/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.MedicineController;
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
import model.Medicine;
import static view.FXML_AppointmentController.stage;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_MedicineController implements Initializable {

    @FXML
    private JFXTextField searchMedicine;
    @FXML
    private TableView<Medicine> medicineTable;
    @FXML
    private TableColumn<Medicine,String> medicineNo;
    @FXML
    private TableColumn<Medicine,String> medicineName;
    @FXML
    private TableColumn<Medicine,String> medicineType;
    @FXML
    private TableColumn<Medicine,String> medicineCompany;
    @FXML
    private TableColumn<Medicine,Double> medicineCost;

    /**
     * Initializes the controller class.
     *    private String medicineId;
    private String medicineName;
    private String medicineType;
    private String medicineCompany;
    private Double medicineCost;
     */
    public static ObservableList<Medicine>medicineList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MedicineController medicineController = new MedicineController();
        medicineTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            medicineList=(ObservableList<Medicine>)medicineController.getAllMedicine();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        medicineTable.setItems(medicineList);
        medicineNo.setCellValueFactory(new PropertyValueFactory<Medicine,String>("medicineId"));
        medicineName.setCellValueFactory(new PropertyValueFactory<Medicine,String>("medicineName"));
        medicineType.setCellValueFactory(new PropertyValueFactory<Medicine,String>("medicineType"));
        medicineCompany.setCellValueFactory(new PropertyValueFactory<Medicine,String>("medicineCompany"));
        medicineCost.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("medicineCost"));
    }    


    public static Medicine selectMedicine=null;
    public static boolean updateButton=false;
    public static boolean clicked=false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        selectMedicine=medicineTable.getSelectionModel().getSelectedItem();
        if(selectMedicine == null){
            return;
        }
        updateButton = true;
        clicked = true;
    }

    @FXML
    private void searchMedicine(KeyEvent event) {
        if(!"".equals(searchMedicine.getText())){
            String s = searchMedicine.getText();
            ObservableList<Medicine>tempList=FXCollections.observableArrayList();
            for(Medicine medicine : medicineList){
                if(medicine.getMedicineId().contains(s) || medicine.getMedicineName().contains(s) || medicine.getMedicineCompany().contains(s) || medicine.getMedicineType().contains(s) ){
                    tempList.add(medicine);
                }
            }
            medicineTable.setItems(tempList);
        }
        else{
            medicineTable.setItems(medicineList);
        }
    }

    @FXML
    private void medicineAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddMedicine.fxml"));   
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

    public static Stage stage;
    @FXML
    private void cancelMedicineAddAction(ActionEvent event) {
        medicineTable.getSelectionModel().clearSelection();
        selectMedicine=null;
        clicked=false;
        updateButton=false;
    }

   public static boolean update=false;
    @FXML
    private void medicineUpdateAction(ActionEvent event) throws IOException {
        if(updateButton == true){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddMedicine.fxml"));
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
    private void medicineDelete(ActionEvent event) throws SQLException {
        if(clicked==true){
            MedicineController medicineController = new MedicineController();
            try{
                ObservableList<Medicine> selectItems = medicineTable.getSelectionModel().getSelectedItems();
                for(Medicine medicine : selectItems){
                    medicineController.deleteMedicine(medicine.getMedicineId());
                }
                medicineList.removeAll(selectItems);
                JOptionPane.showMessageDialog(null, "Delete Successfully");
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
