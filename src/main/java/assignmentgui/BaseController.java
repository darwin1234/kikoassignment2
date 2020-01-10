package assignmentgui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import org.json.JSONObject;

import MongoDBConnection.Row;
import MongoDBConnection.Crud;
import MongoDBConnection.Login;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kikoassigment.Events;
import javafx.scene.paint.*;
import javafx.scene.Node;
import javafx.scene.canvas.*;


public class BaseController  implements Initializable  {
	public AnchorPane anchorPane;
	// Main.fxml and SinglePage.fxml* fx:id's
	public Text title_row1,content_1,price_1,location_1,date_1, createdby1,genre1;
	public Text title_row2,content_2,price_2,location_2,date_2, createdby2,genre2;
	public Text title_row3,content_3,price_3,location_3,date_3, createdby3,genre3;
	public Text title_row4,content_4,price_4,location_4,date_4, createdby4,genre4,popup,singleDate;
	public Text greetings;
	public ImageView photo1,photo2,photo3,photo4;
	public Button logoutbtn,searchbtn,createTicketBtn,yourfeed,feeds,reset,Remove1,Remove2,Remove3,Remove4,Update1,Update2,Update3,Update4;
	public TextField SearchTxt;
	
	public Button view_1,view_2,view_3,view_4,page1,page2,page3,page4,page5,page6, Next, Previous;
	public ComboBox searchbytype;
				
	//Found everywhere in any fxml file.
	public TextField Page;
			
	//found in SignUp and SignIn fxml 
	public TextField usernamefield,firstnamefield,lastnamefield,emailaddress;
	public PasswordField passwordfield;
				
				
	//CreateTicket fx:id,s
	public TextField title, location, price,timesong,musictitle,artistname,albumcoverphoto;
	public DatePicker date;
	public TextArea description;
	public ComboBox category;
			
	//Found in CreateTicket and SinglePage
	public TableView datalist;
		
	public 	Crud db = new Crud();
	
	//background Image
	public ImageView backgroundImage;
	
	//encapsulation
	private int counter = 0;
	private String[][] tracks = new String[10][3];
	
	//foreign key used both Artist and Tracks Table
	private String foreignkey;
	
	//Who created ticket
	public String author;
	
	//Canvas for graphics
	public Canvas Graphics;
	
	
	//
	private int setPage;
	
	
	public void initialize(URL url, ResourceBundle rb) 
	{
	
		String PAGE =  Page.getText(); //this is found fxml files. visibility is equal to false
	}
	
	
	public void TableViewDataList() {
		TableColumn __title = new TableColumn("Title");
		__title.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn __artist = new TableColumn("Artist");
		__artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
		
		TableColumn __time = new TableColumn("Time");
		__time.setCellValueFactory(new PropertyValueFactory<>("time"));
		//added all the colums
		datalist.getColumns().addAll(__title, __artist,__time);
		
	}
	
	public void MainContent() {
		//load crud class

		int i = 0;
		//display all artist!
		ArrayList<Row> ticket = db.read();
		
	
		for (Row printRow : ticket)
        {
			
			i++; //increment i by 4 only
			
			if(i == 1)
			{	
			   //uncomment to see the output
			  // System.out.println(printRow.getImage());
			   Image img1 = new Image("file:/kikoassignment/src/" + printRow.getImage(),true);
			   photo1.setImage(img1);
			   title_row1.setText(printRow.getTitle());
			   content_1.setText(printRow.getDescription().substring(0, 350));
			   price_1.setText("Price: " + printRow.getPrice() + " USD");
			   createdby1.setText("Published By: " +  printRow.getAuthor());
			   location_1.setText("Location: " + printRow.getLocation());
			   view_1.setVisible(true);
			   view_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("View",printRow.getID()));
			   if(author.equals(printRow.getAuthor())) {
				   Remove1.setVisible(true);
				   Remove1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Delete", printRow.getID()));
				   Update1.setVisible(true);
				   Update1.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Update", printRow.getID()));
			   }
			}else if(i == 2) 
		   {

			   //System.out.println(printRow.getImage());
			   Image img2 = new Image("file:/kikoassignment/src/" + printRow.getImage(),true);
			   photo2.setImage(img2);
			   title_row2.setText(printRow.getTitle());
			   content_2.setText(printRow.getDescription().substring(0, 350));
			   price_2.setText("Price: " + printRow.getPrice() + " USD");
			   createdby2.setText("Published By: " +  printRow.getAuthor());
			   location_2.setText("Location: " + printRow.getLocation());
			   view_2.setVisible(true);
			   view_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("View", printRow.getID()));
			   if(author.equals(printRow.getAuthor())) {
			   Remove2.setVisible(true);
			   Remove2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Delete", printRow.getID()));
			   Update2.setVisible(true);
			   Update2.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Update", printRow.getID()));
			   }
			   }else if(i == 3)
		   {
			   Image img3 =  new Image("file:/kikoassignment/src/" + printRow.getImage(),true);
			   photo3.setImage(img3);
			   title_row3.setText(printRow.getTitle());
			   content_3.setText(printRow.getDescription().substring(0, 350));
			   price_3.setText("Price: " + printRow.getPrice() + " USD");
			   createdby3.setText("Published By: " +  printRow.getAuthor());
			   location_3.setText("Location: " + printRow.getLocation());
			   view_3.setVisible(true);
			   view_3.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("View",printRow.getID()));
			   if(author.equals(printRow.getAuthor())) {
			   Remove3.setVisible(true);
			   Remove3.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Delete", printRow.getID()));
			   Update3.setVisible(true);
			   Update3.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Update", printRow.getID()));
			   } 
			  }else if(i == 4) 
		   {
			   Image img4 = new Image("file:/kikoassignment/src/" + printRow.getImage(),true);
			   photo4.setImage(img4);
			   title_row4.setText(printRow.getTitle());
			   content_4.setText(printRow.getDescription().substring(0, 350));
			   price_4.setText("Price: " + printRow.getPrice() + " USD");
			   createdby4.setText("Published By: " +  printRow.getAuthor());
			   location_4.setText("Location: " + printRow.getLocation());
			   view_4.setVisible(true);
			   view_4.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("View",printRow.getID()));
			   if(author.equals(printRow.getAuthor())) {
			   Remove4.setVisible(true);
			   Remove4.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Delete", printRow.getID()));
			   Update4.setVisible(true);
			   Update4.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Update", printRow.getID()));
			   }
		  }else 
		   {
			   i = 0; //When reach to 4 it will reset to 0
		   }
        }
	}
	
	public void SingleContent() {
		
	
		ArrayList<Row> ticket = db.read();
	
		
		for (Row doc : ticket)
        {
			
			Image img1 = new Image("file:/kikoassignment/src/" + doc.getImage(),true);
			photo1.setImage(img1);
			title_row1.setText(doc.getTitle());	
			content_1.setText(doc.getDescription().substring(0, 550));
			title_row2.setText(doc.getTitle());
			location_1.setText(doc.getLocation());
			price_1.setText(""+ doc.getPrice());
			singleDate.setText(doc.getDate());
			System.out.println(doc.getTracks().length());
			
			for(int i = 0; i<doc.getTracks().length();  i++) {
				 JSONObject obj1 = doc.getTracks().getJSONObject(i);
				 //System.out.println(obj1.getString("artist"));
				 String Artist = obj1.getString("artist");
				 String Title = obj1.getString("title");
				 String time = obj1.getString("duration");
				 String TrackID = obj1.getString("fkey");
				 Track track = new Track(Title,Artist,time);
				 datalist.getItems().add(track);
			}
			
        }
		
	}
	
	
	public void Update() {
		
		
		Crud c = new Crud();
		JSONObject content = new JSONObject();
	  	
		content.put("title", title.getText());
	  	content.put("description", description.getText());
	  	content.put("location", location.getText());
	  	content.put("photos", "photos/" + albumcoverphoto.getText());
	  	content.put("date", date.getValue());
	  	content.put("genre", category.getValue().toString());
	  	content.put("price", price.getText());

		//c.update(content, "artist", ObjectId);
	}
	
	public void UpdateContent() {
		
		
		ArrayList<Row> ticket = db.read();
	
		
		for (Row doc : ticket)
        {
			title.setText(doc.getTitle());
			description.setText(doc.getDescription());
			price.setText(""+ doc.getPrice());
			location.setText(""+ doc.getLocation());
			category.setValue(doc.getGenre());
			String dateStr = doc.getDate().toString();
			String[] dateStrV = dateStr.split("-");
			date.setValue(LocalDate.of(Integer.parseInt(dateStrV[0]), Integer.parseInt(dateStrV[1]),Integer.parseInt(dateStrV[2]))); 
			//System.out.println(doc.getTracks().length());
			
			for(int i = 0; i<doc.getTracks().length();  i++) {
				 JSONObject obj1 = doc.getTracks().getJSONObject(i);
				 //System.out.println(obj1.getString("artist"));
				 String Artist = obj1.getString("artist");
				 String Title = obj1.getString("title");
				 String time = obj1.getString("duration");
				 String TrackID = obj1.getString("fkey");
				 Track track = new Track(Title,Artist,time);
				 datalist.getItems().add(track);
			}
			
        }
		
	}
	
	
	public void Categories() {
		//categories added 
		category.setValue("Category");
		category.getItems().addAll("Rock","Fusion","RNB","JAZZ");
	}
	public void SearchByType() {
		searchbytype.setValue("Search Type");
		searchbytype.getItems().addAll("Title","Author","Genre");
		
		
		//searchbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new Events("Search", searchType + "@" + search));
		searchbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent event) 
	        {
	        	Stage single = new Stage();
	        	LoadGui ldGui = new LoadGui();
	        	Crud d = new Crud();
	        	String searchType = searchbytype.getValue().toString();
	    		String search =  SearchTxt.getText();
	    		
	        	Session session;
				try {
					session = new Session();
					session.writeToRandomAccessFile(100, "\n\n\n\n" +  searchType+"@"+search );
		    		d.MemoryLocation(106, "Search");
		    		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		    		ldGui.loadTemplateFXML("Main.fxml",true,single);
		    		d.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        });
		
		reset.addEventFilter(MouseEvent.MOUSE_CLICKED, new Events("reset", ""));
		
		
	}
	
	public void clearAll() {
		
		for (Node node : anchorPane.getChildren()) {
		    //System.out.println("Id: " + node.getId());
		    if (node instanceof Text) {
		        // clear
		    	((Text)node).setText("");
	    
		    }
		
		    if(node instanceof Button) {
		    	//hide
		    	((Button)node).setVisible(false);
		    }
		    
		}
	}

	public void leftSide() {
		feeds.addEventFilter(MouseEvent.MOUSE_CLICKED, new Events("reset", ""));
		yourfeed.addEventFilter(MouseEvent.MOUSE_CLICKED, new Events("yourfeed", author));
	}
	
	public void needtodisplay() {
		logoutbtn.setVisible(true);
		Previous.setVisible(true);
		Next.setVisible(true);
		searchbtn.setVisible(true);
		createTicketBtn.setVisible(true);
		yourfeed.setVisible(true);
		feeds.setVisible(true);
		reset.setVisible(true);	
		
	}

	
	public void pageBtn() {
		//Next.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(1));
		//Previous.addEventHandler(MouseEvent.MOUSE_CLICKED, new pagination(2));
		Next.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			
	        @Override public void handle(MouseEvent event) 
	        {
	        	Stage single = new Stage();
	        	LoadGui ldGui = new LoadGui();
	        	Crud d = new Crud();
	    		String search =  SearchTxt.getText();
	    		System.out.println(setPage++);
				
	        	Session session;
				try {
					session = new Session();
					
					session.writeToRandomAccessFile(100, "\n\n\n\n\n"+ setPage++);
					d.MemoryLocation(106, "Search");
		    		//((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		    		//ldGui.loadTemplateFXML("Main.fxml",true,single);
		    		d.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        });
		
		Previous.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		
	        @Override public void handle(MouseEvent event) 
	        {
	        	Stage single = new Stage();
	        	LoadGui ldGui = new LoadGui();
	        	Crud d = new Crud();
	        	
	    		
	    		System.out.println(setPage--);
	        	Session session;
				try {
					session = new Session();
					
					session.writeToRandomAccessFile(100, "\n\n\n\n\n"+ setPage--);
					d.MemoryLocation(106, "Search");
		    		//((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		    		//ldGui.loadTemplateFXML("Main.fxml",true,single);
		    		d.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
	        });
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
		//System.out.println("Cover Add Function");
		if (file != null) {
			 int rand_int1 = rand.nextInt(100000000); 
			 int rand_int2 = rand.nextInt(100000000); 
			 String PhotoName = "image-file-"+rand_int1+"-"+ rand_int2 + ".png";
			 albumcoverphoto.setText(PhotoName);
			 //System.out.println(PhotoName);
			 String ImagePath = new File("src\\photos\\" + PhotoName).getAbsolutePath();
			 File destination = new File(ImagePath); // new File("kikoassignment\\src\\photos\\",PhotoName);
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
		
		//this will provide foreign key
		Random rand = new Random();
		int rand_int1 = rand.nextInt(100000000); 
	    int rand_int2 = rand.nextInt(100000000); 
	    foreignkey = author + "-" + rand_int1 + rand_int2;
	    
	    
	    String __title = title.getText();
		String __description = description.getText();
		String __category =  category.getValue().toString();
		String __price =   price.getText(); 
		String __photoName  = albumcoverphoto.getText();
		LocalDate __date	= date.getValue();
		
		if(!__title.isEmpty() && !__description.isEmpty()  &&  !__category.isEmpty() && !__price.isEmpty()  ) {
			
			if(__photoName.isEmpty()) {
				popup.setText("Please add Cover Photo!");
			}else {
				  	JSONObject content = new JSONObject();
				  	content.put("title", title.getText());
				  	content.put("description", description.getText());
				  	content.put("location", location.getText());
				  	content.put("photos", "photos/" + albumcoverphoto.getText());
				  	content.put("date", date.getValue());
				  	content.put("genre", category.getValue().toString());
				  	content.put("price", price.getText());
				  	content.put("author",author);
				  	content.put("foreignkey", foreignkey);
			    
					db.create(content, "artist");
				
				    
					String __duration  = timesong.getText();
					String __musictitle =  musictitle.getText();
					String __artistname = artistname.getText();
				    
				    tracks[0][0] = __musictitle;
					tracks[0][1] = __artistname;
					tracks[0][2] = __duration;
					
					//JSONObject for tracks
					JSONObject trackJson = new JSONObject();
					for(int i = 0; i< tracks.length; i++) {
						if(!(tracks[i][0] == null) && !(tracks[i][1] == null) && !(tracks[i][2] == null))
							trackJson.put("title", tracks[i][0]);
							trackJson.put("artist", tracks[i][1]);
							trackJson.put("duration", tracks[i][2]);
							trackJson.put("fkey", foreignkey);
							//crud add record
							db.create(trackJson,"tracks");
					}
			
					popup.setText("Added Successfully!");	
				
			}
		  
		}else {
			popup.setText("Failed Please Fill up all fields!");
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
			
			Track track = new Track(__musictitle, __artistname,__timesong);
			datalist.getItems().add(track);
			
			System.out.println("Song Added!!");
			 
			timesong.clear();	
			musictitle.clear();
			artistname.clear();
			
		} 
		
		System.out.println("Music Title: " + __musictitle);
		System.out.println("Artist Title: " + __artistname);
		System.out.println("Duration: " + __timesong);
		//arrays of two dimension
		tracks[counter][0] = __musictitle;
		tracks[counter][1] = __artistname;
		tracks[counter][2] = __timesong;
		counter++;

	}
	
	public void updateTrack() {

	}
	
	public void removeTrack() {

		datalist.getItems().removeAll(datalist.getSelectionModel().getSelectedItem());
	}
	
	
	public void backtohome(ActionEvent event) throws IOException {
		 Session s = new Session();
		 
		 s.delete(103);
		 
		 Stage main = new Stage();
		 LoadGui ldGui = new LoadGui();
		 //load main window
	     ldGui.loadTemplateFXML("Main.fxml",true,main);
	     //close current window
	     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
	
	public void logout(ActionEvent event) throws IOException  {
		 Stage main = new Stage();
		LoadGui ldGui = new LoadGui();
		 
		Session s = new Session();
		// will flush the userinfosession located at src/session/userinfo
		s.clearUserInfoTxt();
	
		 //close current window
	     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	     
	     ldGui.loadTemplateFXML("Login.fxml",true,main);
		    
	}
	
	
	@FXML
	public void ProceedLogin(ActionEvent event) throws IOException  {
		InputEvent e;
		String u = usernamefield.getText();
		String p = passwordfield.getText();

		Login lg = new Login();
		boolean Res = lg.verify(u,p);
		
		//Simple selection (if–else)  
		
		if(Res) {
			 Stage login = new Stage();
			 Stage main = new Stage();
			 LoadGui ldGui = new LoadGui();
			 //load main window when successfully login
		     ldGui.loadTemplateFXML("Main.fxml",true,main);
		     //close current window which is login window
		     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		   
		}else {
			System.out.print("Invalid Password!");
			popup.setText("Invalid Password!");
		}
	
	}

	@FXML
	public void ProceedRegister(ActionEvent event) throws IOException {
		/*String __usernamefield 	= usernamefield.getText();
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
		}*/
	
		
	}
	


}


