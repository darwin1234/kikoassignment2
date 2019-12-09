package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import MongoDBConnection.Login;
import MongoDBConnection.MongoConnection;
import assignmentgui.LoadGui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class KikoAssignment extends Application {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		LoadGui ldGui = new LoadGui();
		ldGui.loadTemplateFXML("Login.fxml",true,primaryStage);
		
		
	}

}
