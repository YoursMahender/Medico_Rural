module Healthcare {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens MainApplication to javafx.graphics, javafx.fxml;
	exports MainApplication;
}
