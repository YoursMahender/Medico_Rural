package MainApplication;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    public void loginButtonClicked(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        
        if (username.equals("admin") && password.equals("1234")) {
            // Successful login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Set preferred size
            stage.setWidth(600);
            stage.setHeight(479);
            stage.setResizable(false);

            stage.show();
        }
        
        else {
            // Failed login
            System.out.println("Invalid credentials!");
            }
            }
        
        @FXML
     public void registerButtonClicked(ActionEvent event) throws IOException {
        	try {
        	    Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        	   
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
        	    stage.show();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
    
}
    }
