package MainApplication;

import javafx.event.ActionEvent;
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


    @FXML
   public  void handleRegister(ActionEvent event) {
    	nameField.getText();
        ageField.getText();
        genderCombo.getValue();
        phoneField.getText();
        addressField.getText();

        // Save to database logic here
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient registered successfully!");
        alert.show();
    }

}
