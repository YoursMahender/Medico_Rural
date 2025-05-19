package MainApplication;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MedicalHistory {

	private final SimpleObjectProperty<LocalDate> date;
    private final SimpleStringProperty description;

    public MedicalHistory(LocalDate date, String description) {
        this.date = new SimpleObjectProperty<>(date);
        this.description = new SimpleStringProperty(description);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    @SuppressWarnings("exports")
	public SimpleStringProperty descriptionProperty() {
        return description;
    }
}
