package assignmentgui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadGui {

	public LoadGui() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void loadTemplateFXML(String fxmlFile,boolean __show,Stage primaryStage) throws IOException {
		AnchorPane MainPage;
		
		MainPage = (AnchorPane)FXMLLoader.load(getClass().getResource(fxmlFile));
		Scene MainPageScene = new Scene(MainPage,1365,700);
		MainPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(MainPageScene);
		primaryStage.setResizable(false);
		
		if(__show == true)
		{
			primaryStage.show();
		}else {
			primaryStage.hide();
			primaryStage.close();
			//primaryStage = (Stage) primaryStage.getScene().getWindow();
			//primaryStage.close();
			//Platform.exit();
		}
	}

	public void closeTemplate(String fxmlFile) throws IOException {
//		//AnchorPane MainPage;
//		Stage primaryStage = new Stage();
//		MainPage = (AnchorPane)FXMLLoader.load(getClass().getResource(fxmlFile));
//		Scene MainPageScene = new Scene(MainPage,1100,800);
//		MainPageScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(MainPageScene);
//		primaryStage.setResizable(false);
//		primaryStage.hide();
//		primaryStage.close();
	}
}
