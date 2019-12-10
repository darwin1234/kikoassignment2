package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import assignmentgui.LoadGui;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Events implements EventHandler<Event>{
	 
	private final String id; 
	
	public Events(String string){
		this.id = string;
	}

	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		LoadGui ldGui = new LoadGui();
	    try {
	    	Crud d = new Crud();
			d.putObjectID(id);
	    	Stage single = new Stage();
			ldGui.loadTemplateFXML("SinglePage.fxml",true,single);
			System.out.println(id);
			  ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		
	}
	public String getID() {
		 return id;
	}
  

}
