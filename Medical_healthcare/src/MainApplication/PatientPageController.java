package MainApplication;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PatientPageController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private DatePicker appointmentDate;

    @FXML
    private TextField doctorName;

    @FXML
    private TextField problemField;

    @FXML
    private Button bookingMessage;

    @FXML
    private TableView<MedicalHistory> historyTable;

    @FXML
    private TableColumn<MedicalHistory, LocalDate> dateColumn;

    @FXML
    private TableColumn<MedicalHistory, String> descriptionColumn;

    private ObservableList<MedicalHistory> historyList = FXCollections.observableArrayList();

   
   

//        // Load dummy data
//        historyList.add(new MedicalHistory(LocalDate.of(2024, 4, 12), "Routine Check-up"));
//        historyList.add(new MedicalHistory(LocalDate.of(2024, 5, 10), "Blood Test"));
//        historyTable.setItems(historyList);
    
    @FXML
    private String loggedInUsername;
    
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        welcomeLabel.setText("Welcome, " + username + "!");
        loadMedicalHistory(username);
    }

    @FXML
    private void handleBookAppointment() {
        LocalDate date = appointmentDate.getValue();
        String doctor = doctorName.getText();
        String problem = problemField.getText();
//        String username = "your_logged_in_username"; // Replaces this with actual logic

        if (date == null || doctor.isEmpty() || problem.isEmpty()) {
            bookingMessage.setText("Please fill in all fields.");
            bookingMessage.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO appointments (patient_username, doctor_name, appointment_date, problem) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, loggedInUsername);
            stmt.setString(2, doctor);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setString(4, problem);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                bookingMessage.setText("Appointment booked successfully!");
                bookingMessage.setTextFill(javafx.scene.paint.Color.GREEN);

            } 

        } catch (SQLException e) {
            bookingMessage.setText("Failed to book appointment.");
            bookingMessage.setTextFill(javafx.scene.paint.Color.RED);
//            e.printStackTrace();
        }
        
        doctorName.clear();
        problemField.clear();
        appointmentDate.setValue(null);
    }

    @FXML
    public void initialize() {
        // Configure table columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
   
    }

//    historyList.add(new MedicalHistory(LocalDate.of(2024, 4, 12), "Routine Check-up"));
//  historyList.add(new MedicalHistory(LocalDate.of(2024, 5, 10), "Blood Test"));
//  historyTable.setItems(historyList);
    @FXML
    private void loadMedicalHistory(String patientusername) {
        ObservableList<MedicalHistory> data = FXCollections.observableArrayList();

        
       
            try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT visit_date, description FROM medical_history WHERE patient_username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, patientusername);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalDate date = rs.getDate("visit_date").toLocalDate();
                String desc = rs.getString("description");
                data.add(new MedicalHistory(date, desc));
            }

            historyTable.setItems(data);

        } catch (SQLException e) {
            showAlert("Error", "Could not load medical history.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }

	public void setWelcomeMessage(String string) {
		// TODO Auto-generated method stub
		
	}
}
