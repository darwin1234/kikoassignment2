package assignmentgui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;

import MongoDBConnection.Crud;
import MongoDBConnection.Login;
import MongoDBConnection.Row;
import MongoDBConnection.trackRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kikoassigment.Events;
import kikoassigment.pagination;
import javafx.util.Callback;

public class Controller implements Initializable {
	
	public Text title_row1,content_1,price_1,location_1,date_1, createdby1,genre1;
	public Text title_row2,content_2,price_2,location_2,date_2, createdby2,genre2;
	public Text title_row3,content_3,price_3,location_3,date_3, createdby3,genre3;
	public Text title_row4,content_4,price_4,location_4,date_4, createdby4,genre4,popup,singleDate;
	public ImageView photo1,photo2,photo3,photo4;
	public Button view_1,view_2,view_3,view_4,page1,page2,page3,page4,page5,page6, Next, Previous;
	public TextField loadmain,usernamefield,firstnamefield,lastnamefield,emailaddress;
	public TextField title, location, price,timesong,musictitle,artistname,albumcoverphoto;
	public DatePicker date;
	public TextArea description;
	public ComboBox category;
	public PasswordField passwordfield;
	public TableView datalist;
	private int counter = 0;
	private  String[][] a = new String[10][3];

	
	public Controller() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void mainpage() {
		//System.out.print("asdadasdsad");
	}
	
	public void initialize(URL url, ResourceBundle rb) 
	{
		
		Crud db = new Crud();
		String __VIEW__ =  loadmain.getText();
		
		if(__VIEW__.equals("__main__")) 
		{
			
			category.getItems().addAll("Rock","Fusion","RNB","JAZZ");
			
			db.Show(true);
			ArrayList<Row> ticket = db.display();
			
				int i = 0;
				//Clear all text
				clearAll();
				pageBtn();
				for (Row printRow : ticket)
		        {
					
					i++;
					
				   if(i == 1)
				   {	
					   //System.out.println(printRow.getImage());
					   Image img1 = new Image(getClass().getResource(printRow.getImage()).toExternalForm(),true);
					   photo1.setImage(img1);
					    title_row1.setText(printRow.getTitle());
					    content_1.setText(printRow.getDescription().substring(0, 350));
					    price_1.setText("Price: " + printRow.getPrice() + " USD");
					    location_1.setText("Location: " + printRow.getLocation());
						view_1.setVisible(true);
						view_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events(printRow.getID()));
						
						
				   }else if(i == 2) 
				   {
					    Image img1 = new Image(getClass().getResource(printRow.getImage()).toExternalForm(),true);
					    photo2.setImage(img1);
						title_row2.setText(printRow.getTitle());
						content_2.setText(printRow.getDescription().substring(0, 350));
						price_2.setText("Price: " + printRow.getPrice() + " USD");
						location_2.setText("Location: " + printRow.getLocation());
						view_2.setVisible(true);
						view_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events(printRow.getID()));
				   }else if(i == 3)
				   {
					   Image img1 = new Image(getClass().getResource(printRow.getImage()).toExternalForm(),true);
					   photo3.setImage(img1);
						title_row3.setText(printRow.getTitle());
						content_3.setText(printRow.getDescription().substring(0, 350));
						price_3.setText("Price: " + printRow.getPrice() + " USD");
						location_3.setText("Location: " + printRow.getLocation());
						view_3.setVisible(true);
						view_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events(printRow.getID()));
				   }else if(i == 4) 
				   {
					    Image img1 = new Image(getClass().getResource(printRow.getImage()).toExternalForm(),true);
					    photo4.setImage(img1);
						title_row4.setText(printRow.getTitle());
						content_4.setText(printRow.getDescription().substring(0, 350));
						price_4.setText("Price: " + printRow.getPrice() + " USD");
						location_4.setText("Location: " + printRow.getLocation());
						view_4.setVisible(true);
						view_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events(printRow.getID()));
				   }else 
				   {
					   i = 0;
				   }
		        }
				
		}
		
		if(__VIEW__.equals("__singlepage__")) {
			db.Show(false);
			ArrayList<Row> ticket = db.display();
		
			
			for (Row printRow : ticket)
	        {
				Image img1 = new Image(getClass().getResource(printRow.getImage()).toExternalForm(),true);
			    photo1.setImage(img1);
				title_row1.setText(printRow.getTitle());	
				content_1.setText(printRow.getDescription().substring(0, 550));
				title_row2.setText(printRow.getTitle());
				location_1.setText(printRow.getLocation());
				price_1.setText(printRow.getPrice());
				singleDate.setText(printRow.getDate());
				
	        }
			
			TableColumn __title = new TableColumn("Title");
			
			__title.setCellValueFactory(new PropertyValueFactory<>("title"));

			TableColumn __artist = new TableColumn("Artist");
			__artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
			
			TableColumn __time = new TableColumn("Time");
			__time.setCellValueFactory(new PropertyValueFactory<>("time"));
//			
			datalist.getColumns().addAll(__title, __artist,__time);
			
			ArrayList<trackRow> trackrow = db.tracklist();
			
			for (trackRow printRow : trackrow) {
				String Artist = printRow.getArtist();
				String Title = printRow.getTitle();
				String time = printRow.getTime();
				String TrackID = printRow.getTrackID();
				Track track = new Track(Title, Artist,time);
				datalist.getItems().add(track);
			}
			

//			
//			
//			Track track1 = new Track("John", "Doe","3:50");
//			datalist.getItems().add(track1);
//			Track track2 = new Track("John", "Doe","3:50");
//			datalist.getItems().add(track2);
			
		}
		
		if(__VIEW__.equals("__login__")) {
			popup.setText("");
		}
		
		if(__VIEW__.equals("__createticket__")) {
			popup.setText("");
			TableColumn __title = new TableColumn("Title");
			
			__title.setCellValueFactory(new PropertyValueFactory<>("title"));

			TableColumn __artist = new TableColumn("Artist");
			__artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
			
			TableColumn __time = new TableColumn("Time");
			__time.setCellValueFactory(new PropertyValueFactory<>("time"));
		
	
		
			
			datalist.getColumns().addAll(__title, __artist,__time);
			

			category.getItems().addAll("Rock","Fusion","RNB","JAZZ");
		}
		if(__VIEW__.equals("__SignUp__")) {
			
			popup.setText("");
		}
	
	}
	
	private void pageBtn() {
		// TODO Auto-generated method stub
//		page1.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(1));
//		page2.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(2));
//		page3.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(3));
//		page4.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(4));
//		page5.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(5));
//		page6.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(6));
		
		Next.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(1));
		Previous.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(2));
	}

	private void clearAll() {
		// TODO Auto-generated method stub
		title_row1.setText(""); title_row2.setText(""); title_row3.setText(""); title_row4.setText("");
		content_1.setText(""); content_2.setText(""); content_3.setText(""); content_4.setText("");
		price_1.setText(""); price_2.setText(""); price_3.setText(""); price_4.setText("");
		location_1.setText(""); location_2.setText(""); location_3.setText(""); location_4.setText("");
		date_1.setText(""); date_2.setText(""); date_3.setText(""); date_4.setText(""); 
		view_1.setVisible(false); view_2.setVisible(false); view_3.setVisible(false); view_4.setVisible(false);
		createdby1.setText(""); createdby2.setText(""); createdby3.setText(""); createdby4.setText("");
		genre1.setText(""); genre2.setText(""); genre3.setText(""); genre4.setText("");
		
	}

	@FXML
	public void ProceedLogin(ActionEvent event) throws IOException  {
		InputEvent e;
		String u = usernamefield.getText();
		String p = passwordfield.getText();

		Login lg = new Login();
		boolean Res = lg.verify(u,p);
		
		if(Res) {
			 Stage login = new Stage();
			 Stage main = new Stage();
			 LoadGui ldGui = new LoadGui();
		     ldGui.loadTemplateFXML("Main.fxml",true,main);
		     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		   
		}else {
			System.out.print("Invalid Password!");
			popup.setText("Invalid Password!");
		}
	
	}
	
	@FXML
	public void ProceedRegister(ActionEvent event) throws IOException {
		String __usernamefield 	= usernamefield.getText();
		String __passwordfield 	= passwordfield.getText();
		String __firstnamefield = firstnamefield.getText();
		String __lastnamefield	= lastnamefield.getText();
		String __emailaddress	= emailaddress.getText();
		Crud d = new Crud();
		
	
		if(__usernamefield.isEmpty() && __passwordfield.isEmpty() && __firstnamefield.isEmpty() && __lastnamefield.isEmpty() && __emailaddress.isEmpty()) {
			popup.setText("Failed Please Fill up all fields!");
		}else {
			if(d.AddRecord(__firstnamefield, __lastnamefield, __emailaddress,__usernamefield, __passwordfield)) {
				popup.setText("Successfully Registered!");
			}else {
				popup.setText("User already Exist!");
			}
		}
		
	
		
	}
	
	
	
	public void createTicket(ActionEvent event) throws IOException {
		LoadGui ldGui = new LoadGui();
		Stage primaryStage = new Stage();
		ldGui.loadTemplateFXML("CreateTicket.fxml",true,primaryStage);
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	public void addCover() {
		Stage primaryStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(primaryStage);
		Random rand = new Random(); 
		if (file != null) {
			String url = getClass().getResource("photos").toString();
			String __url__ = url.replace("/", "\\\\");
			System.out.println(__url__.replace("file:\\\\", ""));
			 // Generate random integers in range 0 to 999 
	        int rand_int1 = rand.nextInt(100000000); 
	        int rand_int2 = rand.nextInt(100000000); 
	        
			String PhotoName = "image-file-"+rand_int1+"-"+ rand_int2 + ".png";
			albumcoverphoto.setText(PhotoName);
	       
			File destination = new File(__url__.replace("file:\\\\", ""),PhotoName);
			boolean success = file.renameTo(destination);
			System.out.println(success);
		}
	}
	
	public void backtologin(ActionEvent event) throws IOException {
		LoadGui ldGui = new LoadGui();
		Stage primaryStage = new Stage();
		ldGui.loadTemplateFXML("Login.fxml",true,primaryStage);
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	public void Signup(ActionEvent event) throws IOException {
		LoadGui ldGui = new LoadGui();
		Stage primaryStage = new Stage();
		ldGui.loadTemplateFXML("Signup.fxml",true,primaryStage);
	    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	
	public void publish(ActionEvent event) throws IOException {
		Crud d = new Crud();
		String __title = title.getText();
		String __description = description.getText();
		String __address = location.getText();
		LocalDate __date	= date.getValue();
		String __category = (String) category.getValue();
		String __price =   price.getText(); 
		String __timesong = timesong.getText();
		String __musictitle =  musictitle.getText();
		String __artistname = artistname.getText();
		String __photoName  = albumcoverphoto.getText();
		
		if(__title.isEmpty() && __description.isEmpty() 
				&& __address.isEmpty() /*&& __category.isBlank()*/ 
				&& __price.isEmpty() && __timesong.isEmpty() &&  __musictitle.isEmpty() && __artistname.isEmpty()) {
				popup.setText("Failed Please Fill up all fields!");
		}else if(__photoName.isEmpty()) {
				popup.setText("You forgot to add cover photo!");
		}else {
			d.createticket(__title, __description, __address, __date, __category, __price, __photoName);
			popup.setText("Added Successfully!");
		}
		
	
		
		
	}
	
	public void addTrack() {
		System.out.println("Add Track");
		String __timesong = timesong.getText();
		String __musictitle =  musictitle.getText();
		String __artistname = artistname.getText();
		

		
		if(__timesong.isEmpty() && __musictitle.isEmpty() && __artistname.isEmpty()) {
			//popup.setText("Failed Please Fill up all fields!");
			System.out.println("Failed Please Fill up all fields!");
		}else {
			Track track = new Track(__artistname, __musictitle,__timesong);
			datalist.getItems().add(track);
			
			 System.out.println("Song Added!!");
			 timesong.clear();	
			 musictitle.clear();
			 artistname.clear();
			
		} 
		
	  
		 a[counter][0] = __artistname;
		 a[counter][1] = __musictitle;
		 a[counter][2] = __timesong;
		  counter++;
	}
	
	public void updateTrack() {
		for (int i = 0; i < a.length; ++i) {
	        for(int j = 0; j < a[i].length; ++j) {
	           System.out.println(a[i][j]);
	        }
	     }
	}
	
	public void removeTrack() {

		datalist.getItems().removeAll(datalist.getSelectionModel().getSelectedItem());
	}
	
	
	public void backtohome(ActionEvent event) throws IOException {
		 Stage main = new Stage();
		 LoadGui ldGui = new LoadGui();
	     ldGui.loadTemplateFXML("Main.fxml",true,main);
	     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	

}
