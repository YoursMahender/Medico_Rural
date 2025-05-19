module Medical_healthcare {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires jakarta.mail;
	requires jakarta.activation;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens MainApplication to javafx.graphics, javafx.fxml;
	exports MainApplication;
	opens utils to javafx.graphics, javafx.fxml;
	exports utils;
}
