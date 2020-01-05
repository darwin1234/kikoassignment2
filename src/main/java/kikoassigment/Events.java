package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import assignmentgui.LoadGui;
import assignmentgui.Session;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Events implements EventHandler<Event>{
	 
	private final String id; 
	private final String foreignkey;
	
	public Events(String _id,String _fkey) {
		this.id = _id;
		this.foreignkey = _fkey;

	}

	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		LoadGui ldGui = new LoadGui();
	    try {
	    	Crud d = new Crud();
			//d.putObjectID(id);
			//d.putForeignKey(foreignkey);
			
	    	Session s = new Session();
			s.writeToRandomAccessFile(100, id);
			//s.writeToRandomAccessFile(200, foreignkey);
	    	
			Stage single = new Stage();
			ldGui.loadTemplateFXML("SinglePage.fxml",true,single);
			//System.out.println(id);
			  ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		
	}

	
  

}
