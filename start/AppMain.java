package start;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static view.FXML_AppointmentController.stage;
public class AppMain extends Application{

    public static Stage mainStage = new Stage();
    
    public void start(Stage stage) throws Exception {
        this.mainStage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/FXML_Login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
