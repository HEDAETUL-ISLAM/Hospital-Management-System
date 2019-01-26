/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.AdminController;
import DbController.HelpDeskController;
import com.jfoenix.controls.JFXButton;
import start.AppMain;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Admin;
import model.HelpDesk;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class FXML_LoginController implements Initializable {

    
    @FXML
    private TextField userId;
    @FXML
    private TextField password;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton loginButton;
    @FXML
    private BorderPane borderLigin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setImage();
        
    }    
    
    public void setImage(){
        Image image = new Image("file:hospital-icon.png");
        imageView.setImage(image);
    }
    public static Stage stage = new Stage();
    public static String userInfo=null;
    public static String adminUserInfo = null;
    public static boolean hdesk=false;
    public static boolean adminDesk=false;
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException, SQLException {

        if(userId.getText().length()==0 || password.getText().length()==0){
            JOptionPane.showMessageDialog(null, "please input");
            return;
        }
        else{
            String userName=userId.getText();
            String pass=password.getText();
            
            AdminController adminController = new AdminController();
            HelpDeskController helpDeskController = new HelpDeskController();

            Admin admin = new Admin();
            HelpDesk helpDesk = new HelpDesk();

            admin.setPersonId(userName);
            admin.setAdminPassword(pass);

            helpDesk.setPersonId(userName);
            helpDesk.setHelpDeskPassword(pass);

            int j = adminController.loginAction(admin);
            int i = helpDeskController.loginAction(helpDesk);

           try{
                if(i==1){
                    userInfo=userId.getText();
                    hdesk=true;
                    System.out.println("hi");
                    Parent root = FXMLLoader.load(getClass().getResource("FXML_Hospital.fxml"));
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.show();

                    AppMain.mainStage.close();
                }
                else if(j==1){
                    adminUserInfo=userId.getText();
                    adminDesk=true;
                    
                    Parent root = FXMLLoader.load(getClass().getResource("FXML_Hospital.fxml"));
                    Scene scene = new Scene(root);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                    AppMain.mainStage.close();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not valid");
                    return;
                }
            }
           catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Something error");
                return;
           }
        }
    }
    
    @FXML
    private void enterButtonUse(KeyEvent event) {
        loginButton.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
            }
        }); 
    }
    

   
    
}

