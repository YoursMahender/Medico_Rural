package MainApplication;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class welcomePageController {

    @FXML
    private Button continuebutton;
    
    @FXML
    private void ContinuebuttonListener() throws IOException {
        
        Stage stage = (Stage) continuebutton.getScene().getWindow();

        // Load Main.fxml
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.show();

}
}
