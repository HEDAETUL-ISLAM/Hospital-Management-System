/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.HospitalController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class HospitalDetailsController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField address;
    @FXML
    private ImageView imageView;
    HospitalController  hospitalController = new HospitalController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setEditable(false);
        name.setEditable(false);
        address.setEditable(false);
        try {
            Object array[][]=hospitalController.gethospital();
            id.setText((String) array[0][0]);
            name.setText((String) array[0][1]);
            address.setText((String) array[0][2]);
            
        } catch (SQLException ex) {
            Logger.getLogger(HospitalDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setImage();
    }    
    public void setImage(){
        Image image = new Image("file:p2.jpg");
        imageView.setImage(image);
    } 
    
}
