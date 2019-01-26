/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.DepartmentController;
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
import model.Department;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_DepartmentController implements Initializable {

    @FXML
    private JFXTextField searchDepartment;
    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableColumn<Department,String > departmentId;
    @FXML
    private TableColumn<Department,String> departmentName;
    @FXML
    private TableColumn<Department, String> departmentHead;
    @FXML
    private TableColumn<Department, String> consultant;
    @FXML
    private JFXButton getDepartment;
    @FXML
    private JFXButton cancelDepartment;
    @FXML
    private JFXButton updateDepartment;

    /**
     * Initializes the controller class.
     */
    public static ObservableList<Department>departmentList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DepartmentController departmentController = new DepartmentController();
        departmentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            departmentList= (ObservableList<Department>)departmentController.getAllDepartment();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_DepartmentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ex");
        }
        departmentTable.setItems(departmentList);
        departmentId.setCellValueFactory(new PropertyValueFactory<Department,String>("departmentId"));
        departmentHead.setCellValueFactory(new PropertyValueFactory<Department,String>("departmentHead"));
        consultant.setCellValueFactory(new PropertyValueFactory<Department,String>("consultant"));
        departmentName.setCellValueFactory(new PropertyValueFactory<Department , String>("departmentName"));
    }


    public static Department selectDepartment = null;
    public static boolean updateButton = false;
    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {

        selectDepartment=departmentTable.getSelectionModel().getSelectedItem();
        if(selectDepartment == null){
            return;
        }
        updateButton=true;
        clicked=true;
    }

    public static Stage stage;
    @FXML
    private void departmentAddAction(ActionEvent event) throws IOException {
        if(clicked==false){
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddDepartment.fxml"));
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
    private void cancelDepartmentAddAction(ActionEvent event) {
        departmentTable.getSelectionModel().clearSelection();
        selectDepartment = null;
        clicked=false;
        updateButton=false;
    }
    public static boolean update = false;
    @FXML
    private void departmentUpdateAction(ActionEvent event) throws IOException {
        if(updateButton == true){
            selectDepartment=departmentTable.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AddDepartment.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            updateButton = true;
            update = true;
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        }
        else{
            return;
        }
    }

    @FXML
    private void departmentDelete(ActionEvent event) {

        if(clicked == true){
            DepartmentController departmentController = new DepartmentController();
            try{
                ObservableList<Department>selectItems=departmentTable.getSelectionModel().getSelectedItems();
                for(Department department : selectItems){
                    departmentController.deleteDepartment(department.getDepartmentId());
                }
                departmentList.removeAll(selectItems);
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

    @FXML
    private void searchDepartment(KeyEvent event) {

        if(!"".equals(searchDepartment.getText())){
            String s = searchDepartment.getText();
            ObservableList<Department>tempList=FXCollections.observableArrayList();
            for(Department department:departmentList){
                if(department.getDepartmentId().contains(s) || department.getDepartmentHead().contains(s) || department.getDepartmentName().contains(s) || department.getConsultant().contains(s)){
                    tempList.add(department);
                }
            }
           departmentTable.setItems(tempList);
        }
        else{
            departmentTable.setItems(departmentList);
        }
    }

}
