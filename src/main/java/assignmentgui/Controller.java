package assignmentgui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import MongoDBConnection.Crud;
import MongoDBConnection.Login;
import MongoDBConnection.Row;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class Controller extends BaseController {
	//Greetings Variable String
    private String WelcomeStr;
	
	
    public Controller() throws IOException {
		
		//load session class
		//Simple selection (if–else) 
		Session s = new Session();
		if(s.getSessionLength() != 0) {
			WelcomeStr =  s.getUsername();
			author = WelcomeStr;
		}
		//106 memory location for search
		
		
	}
	

	
	public void initialize(URL url, ResourceBundle rb) 
	{
		//implement polymorphism
		
		Image bgImage = new Image("file:/kikoassignment/src/resource/images/bg.jpg",true);
		backgroundImage.setImage(bgImage);
		String PAGE =  Page.getText(); //this is found fxml files. visibility is equal to false
		
		//__main__ 			= Main.fxml window
		//__singlepage__ 	= SinglePage.fxml
		//__login__			= Login.fxml
		//__SignUp__		= SignUp.fxml
			
		
		switch(PAGE) {
			case "__SignUp__":
				//clear popup
				popup.setText("");
				break;
			case "__login__":
				//clear popup
				popup.setText("");
				break;
			case "__main__": 
				if(!WelcomeStr.isEmpty()) {
					//Clear all fx:id's
					clearAll(); 
					//greetings when successfully login!
					greetings.setText("Hi, " +  WelcomeStr);
					//these function is next and previous
					pageBtn();
					//load content
					MainContent();
					//Search Type
					try {
						SearchByType();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//need to display
					needtodisplay();
					
					leftSide();
				}	
				
				break;
			case "__singlepage__": 
				TableViewDataList();
				SingleContent();
				break; 
		
			case "__createticket__":
				popup.setText("");
				TableViewDataList();
				Categories();
				
				break;
			case "__update__":	
				popup.setText("");
				TableViewDataList();
				Categories();
				EditableTableView();
				try {
					UpdateContent();
				} catch (ParseException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			break;
			default:
				System.out.println("No Page found!");
	 		break;
		}		
 
		
	
	}
	
	
	

}
