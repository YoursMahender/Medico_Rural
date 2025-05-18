module Medical_healthcare {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens MainApplication to javafx.graphics, javafx.fxml;
	exports MainApplication;
}
