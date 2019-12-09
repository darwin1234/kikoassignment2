package kikoassigment;

import MongoDBConnection.Crud;
import javafx.event.Event;
import javafx.event.EventHandler;

public class pagination  implements EventHandler<Event>{
	private int page;
	
	public pagination(int __page) {
		// TODO Auto-generated constructor stub
		this.page = __page;
		
	}

	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		//System.out.println(page);
		Crud s = new Crud();
		s.SetPage(page);
	}
	
	

}
