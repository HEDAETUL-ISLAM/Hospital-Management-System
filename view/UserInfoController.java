/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DbController.AdminController;
import DbController.HelpDeskController;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Admin;
import model.HelpDesk;
import static view.FXML_LoginController.adminUserInfo;
import static view.FXML_LoginController.userInfo;

/**
 * FXML Controller class
 *
 * @author hedaetul
 */
public class UserInfoController implements Initializable {

    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AdminController adminController = new AdminController();
        HelpDeskController helpDeskController = new HelpDeskController();
        FXML_LoginController fXML_LoginController = new FXML_LoginController();
        
        
        id.setEditable(false);
        name.setEditable(false);
        address.setEditable(false);
        phone.setEditable(false);
        password.setEditable(false);
        
        if(FXML_LoginController.hdesk==true){
            try {
                helpDesk=helpDeskController.getHelpDesk(userInfo);
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            id.setText(helpDesk.getPersonId());
            name.setText(helpDesk.getPersonName());
            address.setText(helpDesk.getPersonAddress());
            phone.setText(helpDesk.getPersonPhone());
            password.setText(helpDesk.getHelpDeskPassword());
        }
        else if(FXML_LoginController.adminDesk == true){
            try {
                admin=adminController.getAdmin(adminUserInfo);
            } catch (SQLException ex) {
                Logger.getLogger(UserInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            id.setText(admin.getPersonId());
            name.setText(admin.getPersonName());
            address.setText(admin.getPersonAddress());
            phone.setText(admin.getPersonPhone());
            password.setText(admin.getAdminPassword());
        }
        
        
        
        
        
        
    }
    public static HelpDesk helpDesk = null;    
    public static Admin admin = null;
    
}
