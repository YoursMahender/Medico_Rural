package MainApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.NotificationUtil;

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

    @FXML
    public void initialize() {
        // Configure table columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Load dummy data
        historyList.add(new MedicalHistory(LocalDate.of(2024, 4, 12), "Routine Check-up"));
        historyList.add(new MedicalHistory(LocalDate.of(2024, 5, 10), "Blood Test"));
        historyTable.setItems(historyList);
    }
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
        	conn.setAutoCommit(false);
        	
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
            
            NotificationUtil.showSuccess("Your appointment has been booked successfully!");
           

            
            // Insert into medical_history table
            String historySql = "INSERT INTO medical_history (patient_username, visit_date, description) VALUES (?, ?, ?)";
            try (PreparedStatement stmt1 = conn.prepareStatement(historySql)) {
                stmt1.setString(1, loggedInUsername);
                stmt1.setDate(2, java.sql.Date.valueOf(date));
                stmt1.setString(3, "Appointment with Dr. " + doctor + " - " + problem);
                stmt1.executeUpdate();
            }

            conn.commit(); 
            NotificationUtil.showInfo("Medical History Updated", "A new record has been added to your medical history.");
            
            loadMedicalHistory(loggedInUsername);

            
        }
            catch (SQLException e) {
            bookingMessage.setText("Failed to book appointment.");
            bookingMessage.setTextFill(javafx.scene.paint.Color.RED);
//            e.printStackTrace();
        }
        
        doctorName.clear();
        problemField.clear();
        appointmentDate.setValue(null);
    }


    @FXML
    private void loadMedicalHistory(String patientUsername) {
        ObservableList<MedicalHistory> data = FXCollections.observableArrayList();

        String sql = "SELECT visit_date, description FROM medical_history WHERE patient_username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patientUsername);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalDate date = rs.getDate("visit_date").toLocalDate();
                String desc = rs.getString("description");
                data.add(new MedicalHistory(date, desc));
            }

            historyTable.setItems(data);

        } catch (SQLException e) {
            showAlert("Error", "Could not load medical history.\n" + e.getMessage());
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
