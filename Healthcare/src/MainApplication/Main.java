package MainApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	@SuppressWarnings("exports")
	public void start( Stage stage) throws Exception
	{
	
		Parent parent = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
		
		Scene scene= new Scene(parent);
		
		stage.setTitle("Medico");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         launch(args);
	}

}
