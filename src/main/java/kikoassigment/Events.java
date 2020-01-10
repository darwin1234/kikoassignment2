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
		System.out.println("Event Class: " + genericString);
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
					System.out.println("Event Class: " + genericString);
					d.MemoryLocation(106, "search");
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					ldGui.loadTemplateFXML("Main.fxml",true,single);
					d.read();
					break;
				case "reset": 
					System.out.println("Feeds!");
					Session s;
					try {
						s = new Session();
						Stage main = new Stage();
						s.delete(100);
						s.delete(103);
						s.delete(106);
						  ldGui.loadTemplateFXML("Main.fxml",true,main);
						     //close current window which is login window
						     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "yourfeed":
					System.out.println("your feeds:  " + genericString);
					
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
