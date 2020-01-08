package kikoassigment;

import java.io.IOException;


import assignmentgui.LoadGui;
import assignmentgui.Session;
import javafx.application.Application;
import javafx.stage.Stage;


public class KikoAssignment extends Application {
	/*
	 * MemoryAccessFile
	 * location: 0   = User Information like username, firstname, lastname
	 * location: 103 = ObjectId for view,update,delete
	 * 
	 * 
	 */

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
