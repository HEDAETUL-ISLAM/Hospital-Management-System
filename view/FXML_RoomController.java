/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.RoomController;
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
import model.Room;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_RoomController implements Initializable {

    @FXML
    private JFXTextField searchRoom;
    @FXML
    private TableView<Room> roomTable;
    @FXML
    private TableColumn<Room,Integer> roomNo;
    @FXML
    private TableColumn<Room,String> status;
    @FXML
    private TableColumn<Room,String> roomType;
    @FXML
    private JFXTextField getRoomNo;
    @FXML
    private JFXTextField getStatus;
    @FXML
    private JFXTextField getRoomType;
    @FXML
    private JFXButton saveRoomNo;

    /**
     * Initializes the controller class.
     */
    ObservableList<Room> roomList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RoomController roomController = new RoomController() ;
        roomTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try{
            roomList=(ObservableList<Room>)roomController.getAllRoom();
        }
        catch(SQLException ex){
            Logger.getLogger(FXML_RoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        roomTable.setItems(roomList);
        roomNo.setCellValueFactory(new PropertyValueFactory<Room,Integer>("roomNo"));
        status.setCellValueFactory(new PropertyValueFactory<Room,String>("status"));
        roomType.setCellValueFactory(new PropertyValueFactory<Room,String>("roomType"));
    }    

    @FXML
    private void searchRoom(KeyEvent event) {
        if(!"".equals(searchRoom.getText())){
            String string = searchRoom.getText();
            ObservableList<Room> tempList = FXCollections.observableArrayList();
            for(Room room : roomList){
                if(Integer.toString(room.getRoomNo()).contains(string) || room.getRoomType().contains(string) || room.getStatus().contains(string)){
                    tempList.add(room);
                }
            }
            roomTable.setItems(tempList);
        }
        else{
            roomTable.setItems(roomList);
        }
    }

    public static boolean clicked = false;
    @FXML
    private void tableItemClicked(MouseEvent event) {
        Room selectRoom = roomTable.getSelectionModel().getSelectedItem();
        if(selectRoom==null){
            return;
        }
        getRoomNo.setText(Integer.toString(selectRoom.getRoomNo()));
        getStatus.setText(selectRoom.getStatus());
        getRoomType.setText(selectRoom.getRoomType());
        updateRoom=selectRoom;
        saveRoomNo.setText("Update");
        clicked=true;
    }

    Room updateRoom = new Room();
    @FXML
    private void roomAdd(ActionEvent event) throws SQLException {
        RoomController roomController = new RoomController();
        if(saveRoomNo.getText().equals("Update")){
            Room room = new Room(Integer.parseInt(getRoomNo.getText()),getStatus.getText(),getRoomType.getText());
            roomController.updateRoom(room);
            roomList.remove(updateRoom);
            roomList.add(room);
            saveRoomNo.setText("Save");
            return;
        }
        if(roomNo.getText().length()==0 || getStatus.getText().length()==0 || getRoomType.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Please Insert");
            return;
        }
        else{
            try{
                Room room = new Room(Integer.parseInt(getRoomNo.getText()),getStatus.getText(),getRoomType.getText());
                roomController.insertRoom(room);
                roomList.add(room);
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
    private void resetRoom(ActionEvent event) {
        if(saveRoomNo.getText()=="Update"){
            saveRoomNo.setText("Save");
            getRoomNo.setText("");
            getRoomType.setText("");
            getStatus.setText("");
            roomTable.getSelectionModel().clearSelection();
            clicked=false;
        }
    }

    @FXML
    private void deleteRoom(ActionEvent event) {
        if(clicked==true){
            RoomController roomController = new RoomController();
            try{
                 ObservableList<Room> selectItems = roomTable.getSelectionModel().getSelectedItems();
                 for(Room room : selectItems){
                     roomController.deleteRoom(room.getRoomNo());
                 }
                 roomList.removeAll(selectItems);
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
