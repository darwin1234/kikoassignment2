package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import assignmentgui.LoadGui;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class pagination  implements EventHandler<Event>{
	private int page;
	
	public pagination(int __page) {
		// TODO Auto-generated constructor stub
		this.page  = __page;
		
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		//System.out.println(page);
		Crud s = new Crud();
		s.SetPage(page);
		s.Show(true);
		s.display();
		 Stage main = new Stage();
		 LoadGui ldGui = new LoadGui();
	     try {
			ldGui.loadTemplateFXML("Main.fxml",true,main);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	

}
