package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import MongoDBConnection.Login;
import MongoDBConnection.MongoConnection;
import assignmentgui.LoadGui;
import assignmentgui.Session;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class KikoAssignment extends Application {
	

	public static void main(String[] args) throws IOException {
		Application.launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoadGui ldGui = new LoadGui();
		Session s = new Session();
		//when session userinfo.txt is not empty
		//Simple selection (if–else) 
		if(s.getSessionLength() != 0) {
			ldGui.loadTemplateFXML("Main.fxml",true,primaryStage);
		}else {
			//load login when session userinfo.txt is empty
			ldGui.loadTemplateFXML("Login.fxml",true,primaryStage);
		}

	}

}
