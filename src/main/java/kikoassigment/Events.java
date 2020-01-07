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
	private final String foreignkey;
	private final String action;
	public Events(String action, String _id,String _fkey) {
		this.id = _id;
		this.foreignkey = _fkey;
		this.action = action;

	}

	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		LoadGui ldGui = new LoadGui();
	    try {
	    	Crud d = new Crud();
			
			
	    	Session s = new Session();
			
			if(this.action =="View")
			{	
				//Single BackSlash "\n" means view
				
				s.writeToRandomAccessFile(100, "\n" + id);
				d.MemoryLocation(103,"_id");
				Stage single = new Stage();
				ldGui.loadTemplateFXML("SinglePage.fxml",true,single);
				((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			}
			
			if(this.action =="Delete")
			{
				//Double BackSlash "\n\n" means delete 
				
				s.writeToRandomAccessFile(100, "\n\n"+id);
				d.MemoryLocation(104,"_id");
				System.out.println("Delete!");
				//Stage single = new Stage();
				//ldGui.loadTemplateFXML("SinglePage.fxml",true,single);
				
			}	
			
			
			if(this.action =="Update")
			{
				//Update
				
				s.writeToRandomAccessFile(100, "\n\n\n"+id);
				d.MemoryLocation(103,"_id");
				Stage single = new Stage();
				ldGui.loadTemplateFXML("CreateTicket.fxml",true,single);
				((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
			}	
			
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  
		
	}

	
  

}
