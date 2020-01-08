package kikoassigment;

import java.io.IOException;

import MongoDBConnection.Crud;
import assignmentgui.LoadGui;
import assignmentgui.Session;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class pagination  implements EventHandler<Event>{
	private int page;
	private int setPage;
	public pagination(int __page) {
		
		this.page  = __page;
		
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		//System.out.println(page);
	
		Session ss;
		try {
			if(page == 1) {
				setPage++;
			}else 
			{
				setPage--;
				
			}
			
			ss = new Session();
			ss.writeToRandomAccessFile(100, "\n\n\n\n\n"+ setPage);
			
			
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Crud s = new Crud(); 
		s.read();
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
