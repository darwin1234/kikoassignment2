package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import assignmentgui.LoadGui;
import assignmentgui.Session;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Events implements EventHandler<Event>{
	 
	private final String genericString; 
	private final String action;
	
	public Events(String action, String genericstr) {
		this.genericString = genericstr;
		this.action = action;
	}

	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		LoadGui ldGui = new LoadGui();
	    try {
	    	Crud d = new Crud();
	    	Session session = new Session();
	    	Stage single = new Stage();
			switch(this.action) {
				case "View": 
					session.writeToRandomAccessFile(100, "\n" + genericString);
					d.MemoryLocation(103,"genericString");
					ldGui.loadTemplateFXML("SinglePage.fxml",true,single);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					break;
				case "Delete":
				
					session.writeToRandomAccessFile(100, "\n" + genericString);
					d.MemoryLocation(103,"_id");
					System.out.println("Delete!");
					break;
				case "Update":
					session.writeToRandomAccessFile(100, "\n" + genericString);
					d.MemoryLocation(103,"_id");
					ldGui.loadTemplateFXML("Update.fxml",true,single);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					break;
				case "Search":
					session.writeToRandomAccessFile(100, "\n\n\n\n" + genericString );
					d.MemoryLocation(106, "search");
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					ldGui.loadTemplateFXML("Main.fxml",true,single);
					System.out.println("Search!!!");
					d.read();
					break;
				default:
					break;
			}
		
			
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  
		
	}

	
  

}
