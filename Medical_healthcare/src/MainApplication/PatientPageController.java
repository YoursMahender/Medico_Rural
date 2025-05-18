package MainApplication;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class PatientPageController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private DatePicker appointmentDate;

    @FXML
    private TextField doctorName;

    @FXML
    private Label bookingMessage;

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
    private void handleBookAppointment() {
        LocalDate date = appointmentDate.getValue();
        String doctor = doctorName.getText();

        if (date == null || doctor.isEmpty()) {
            bookingMessage.setText("Please fill in all fields.");
            bookingMessage.setTextFill(javafx.scene.paint.Color.RED);
            return;
        }

        // Simulate booking success
        bookingMessage.setText("Appointment booked with Dr. " + doctor + " on " + date + ".");
        bookingMessage.setTextFill(javafx.scene.paint.Color.GREEN);

        // Optionally clear fields
        doctorName.clear();
        appointmentDate.setValue(null);
    }

    // Optional: Set the username to customize welcome message
    public void setUsername(String username) {
        welcomeLabel.setText("Welcome, " + username + "!");
    }

    // Inner class for medical history
    public static class MedicalHistory {
        private LocalDate date;
        private String description;

        public MedicalHistory(LocalDate date, String description) {
            this.date = date;
            this.description = description;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }
    }
}
