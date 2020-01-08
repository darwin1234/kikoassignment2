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
	 
	private final String id; 
	private final String action;
	
	public Events(String action, String _id) {
		this.id = _id;
		this.action = action;
	}

	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		LoadGui ldGui = new LoadGui();
	    try {
	    	Crud d = new Crud();
	    	Session s = new Session();
	    	Stage single = new Stage();
			switch(this.action) {
				case "View": //Single BackSlash "\n" means View
					s.writeToRandomAccessFile(100, "\n" + id);
					d.MemoryLocation(103,"_id");
					ldGui.loadTemplateFXML("SinglePage.fxml",true,single);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					
					break;
				case "Delete":
					//Double BackSlash "\n\n" means delete 
					s.writeToRandomAccessFile(100, "\n\n"+id);
					d.MemoryLocation(103,"_id");
					System.out.println("Delete!");
					break;
				case "Update":
					s.writeToRandomAccessFile(100, "\n\n\n"+id);
					d.MemoryLocation(103,"_id");
					ldGui.loadTemplateFXML("Update.fxml",true,single);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					break;
				case "Search":
					/*Session s = new Session();
					String searchType = searchbytype.getValue().toString();
					String search =  SearchTxt.getText();
					s.writeToRandomAccessFile(100, "\n\n\n\n" + searchType +"///"+search );
					d.MemoryLocation(106, "search");
					d.read();*/
					System.out.println("Search!!!");
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
