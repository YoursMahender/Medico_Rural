package MainApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;



public class PatientRegistrationController {

    @FXML
    private TextField addressField;

    @FXML
    private TextField ageField;

    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;
    
    @FXML
    private TextField passwordfield;

    @FXML
    private TextField usernamefield;

    @FXML
    private Button registerbutton;
    
    public void initialize() {
        genderCombo.getItems().addAll("Male", "Female", "Other");
    }


    
        
        public boolean registerPatient(String name, String username, String password, int age ,String gender, String phone, String address) {
            String sql = "INSERT INTO patients (name, username, password,age, gender, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, name);
                stmt.setString(2, username);
                stmt.setString(3, password); // you can hash this before storing
                stmt.setInt(4, age);
                stmt.setString(5, gender);
                stmt.setString(6, phone);
                stmt.setString(7, address);
                
                

                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        @FXML
        private void handleRegister() {
            String name = nameField.getText();
            String username = usernamefield.getText();
            String password = passwordfield.getText();
            String gender = genderCombo.getValue();
            String phone = phoneField.getText();
            String address = addressField.getText();
            int age = Integer.parseInt(ageField.getText());

            boolean success = registerPatient(name, username, password, age,  gender, phone, address);
            if (success) {
                System.out.println("Patient registered successfully.");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient registered successfully!");
                alert.show();
            } else {
                System.out.println("Registration failed.");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Registration failed. Try again.");
                alert.show();
            }
        } 
}
        



//        // Save to database logic here
//        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient registered successfully!");
//        alert.show();
//        
        
 
  

