package MainApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM patients WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // if stored as hashed, hash before comparing

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // returns true if a match is found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        try {
    	    Parent root = FXMLLoader.load(getClass().getResource("patientPage.fxml"));
    	   
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
    	    stage.show();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}

        boolean valid = validateLogin(username, password);
        if (valid) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
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
