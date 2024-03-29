package assignmentgui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;

import MongoDBConnection.Row;
import MongoDBConnection.User;
import MongoDBConnection.Crud;
import MongoDBConnection.Login;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
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
import java.util.Date;
import java.util.Iterator;

import javafx.scene.Node;
import javafx.scene.canvas.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Updates.combine;

import static com.mongodb.client.model.Updates.set;


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
	@FXML
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
	public TableView<Track> datalist;
	public TableView genre;	
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
	
	private String SingleID = null;
	
	private String idStr = null;
	
	@FXML
	public BarChart<?, ?> visitors;
	
	public void initialize(URL url, ResourceBundle rb) 
	{
	
		String PAGE =  Page.getText(); //this is found every fxml files. visibility is equal to false
	}
	
	
	public void TableViewDataList()
	{
		
		datalist.getItems().clear();
		TableColumn<Track, String> __title = new TableColumn("Title");
		__title.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn<Track, String> __artist = new TableColumn("Artist");
		__artist.setCellValueFactory(new PropertyValueFactory<>("artist"));
		
		TableColumn<Track, String> __time = new TableColumn("Time");
		__time.setCellValueFactory(new PropertyValueFactory<>("time"));
		//added all the colums
		datalist.getColumns().addAll(__title, __artist,__time);
		
		
		
		
	}
	
	public void MainContent() {
		//load crud class
		
		try {
			content();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void content() throws IOException {
		int i = 0;
		
		//display all artist!
		AggregateIterable<Document> ticket  = db.read("artist");
		
		 
		for (Document document : ticket)
 	    {
			
				i++; //increment i by 4 only
 	    		
 	    		var objID = new ArrayList<>(document.values());
 		    	JSONObject obj = new JSONObject(document);

		    	//org.json.JSONArray c = obj.getJSONArray("tracks");
 		    
 		    	if(i == 1)
				{	
 		    		Image img1 = new Image("file:/kikoassignment/src/" + obj.getString("photos"),true);
 		    		photo1.setVisible(true);
 					title_row1.setVisible(true);
 					content_1.setVisible(true);
 					price_1.setVisible(true);
 					createdby1.setVisible(true);
 					location_1.setVisible(true);
 					photo1.setImage(img1);
 					title_row1.setText(obj.getString("title"));
 					content_1.setText(obj.getString("description").substring(0, 350));
 					price_1.setText("Price: " + obj.getDouble("price") + " USD");
 					createdby1.setText("Published By: " +  obj.getString("author"));
 					location_1.setText("Location: " + obj.getString("location"));
 					view_1.setVisible(true);
 					view_1.setOnAction(e -> FxEvents("View", objID.get(0).toString()) );
 					if(author.equals(obj.getString("author"))) {
 						Remove1.setVisible(true); 
 						Remove1.setOnAction(e ->  FxEvents("Delete", objID.get(0).toString()));
 						Update1.setVisible(true); 
 						Update1.setOnAction(e -> FxEvents("UpdatePage", objID.get(0).toString()));
 					}
				}else if(i == 2) 
 				{
					Image img2 = new Image("file:/kikoassignment/src/" + obj.getString("photos"),true);
					photo2.setVisible(true);
					title_row2.setVisible(true);
					content_2.setVisible(true);
					price_2.setVisible(true);
					createdby2.setVisible(true);
					location_2.setVisible(true);
					photo2.setImage(img2);
					title_row2.setText(obj.getString("title"));
					content_2.setText(obj.getString("description").substring(0, 350));
					price_2.setText("Price: " + obj.getDouble("price") + " USD");
					createdby2.setText("Published By: " +  obj.getString("author"));
					location_2.setText("Location: " + obj.getString("location"));
					view_2.setVisible(true);
					view_2.setOnAction(e -> FxEvents("View", objID.get(0).toString()) );
					if(author.equals(obj.getString("author"))) {
						Remove2.setVisible(true); 
						Remove2.setOnAction(e ->  FxEvents("Delete", objID.get(0).toString()));
						Update2.setVisible(true); 
						Update2.setOnAction(e -> FxEvents("UpdatePage", objID.get(0).toString()));
					}
 						   
 				}else if(i == 3)
 				{
 					Image img3 = new Image("file:/kikoassignment/src/" + obj.getString("photos"),true);
					photo3.setVisible(true);
					title_row3.setVisible(true);
					content_3.setVisible(true);
					price_3.setVisible(true);
					createdby3.setVisible(true);
					location_3.setVisible(true);
					photo3.setImage(img3);
					title_row3.setText(obj.getString("title"));
					content_3.setText(obj.getString("description").substring(0, 350));
					price_3.setText("Price: " + obj.getDouble("price") + " USD");
					createdby3.setText("Published By: " +  obj.getString("author"));
					location_3.setText("Location: " + obj.getString("location"));
					view_3.setVisible(true);
					view_3.setOnAction(e -> FxEvents("View", objID.get(0).toString()) );
					if(author.equals(obj.getString("author"))) {
						Remove3.setVisible(true); 
						Remove3.setOnAction(e ->  FxEvents("Delete", objID.get(0).toString()));
						Update3.setVisible(true); 
						Update3.setOnAction(e -> FxEvents("UpdatePage", objID.get(0).toString()));
					}
 						   
 				}else if(i == 4) 
 				{
 						  
 					Image img4 = new Image("file:/kikoassignment/src/" + obj.getString("photos"),true);
					photo4.setVisible(true);
					title_row4.setVisible(true);
					content_4.setVisible(true);
					price_4.setVisible(true);
					createdby4.setVisible(true);
					location_4.setVisible(true);
					photo4.setImage(img4);
					title_row4.setText(obj.getString("title"));
					content_4.setText(obj.getString("description").substring(0, 350));
					price_4.setText("Price: " + obj.getDouble("price") + " USD");
					createdby4.setText("Published By: " +  obj.getString("author"));
					location_4.setText("Location: " + obj.getString("location"));
					view_4.setVisible(true);
					view_4.setOnAction(e -> FxEvents("View", objID.get(0).toString()) );
					if(author.equals(obj.getString("author"))) {
						Remove4.setVisible(true); 
						Remove4.setOnAction(e ->  FxEvents("Delete", objID.get(0).toString()));
						Update4.setVisible(true); 
						Update4.setOnAction(e -> FxEvents("UpdatePage", objID.get(0).toString()));
					}
 						  
 				   	  
 				}
 				else{ i = 0; /*When reach to 4 it will reset to 0*/ }
 		    		
 
 		    	
 	   }
		

		
		
	}
	
	public void SingleContent() {
	
		vistors();
		
		AggregateIterable<Document> ticket  = db.read("artist");
		 
		for (Document document : ticket)
	    {
			var objID = new ArrayList<>(document.values());
		    JSONObject obj = new JSONObject(document);
			Image img1 = new Image("file:/kikoassignment/src/" + obj.getString("photos"),true);
			photo1.setImage(img1);
			title_row1.setText(obj.getString("title"));	
			content_1.setText(obj.getString("description").substring(0, 550));
			title_row2.setText(obj.getString("title"));
			location_1.setText(obj.getString("location"));
			price_1.setText(obj.getString("price").toString());
			singleDate.setText(obj.getString("date"));
			org.json.JSONArray tracks = obj.getJSONArray("tracks");
			
			for(int i = 0; i<tracks.length();  i++) {
				JSONObject track = obj.getJSONArray("tracks").getJSONObject(i);
				String Artist = track.getString("artist");
				String Title = track.getString("title");
				String time = track.getString("duration");
				String TrackID = track.getString("fkey");	
				String id = track.getString("id");
				Track music = new Track(Title,Artist,time,id);
				datalist.getItems().add(music);
			}
			
	
	    }
		

		
	}
	
	public void insertVistors(String objectid) {
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		//LocalDateTime now = LocalDateTime.now();  
		Date d = new Date();
		Crud crud = new Crud();
		Document content = new Document()
				.append("userID", author)
				.append("date", d)
				.append("id", objectid);
		
		crud.create(content, "visitors");
	}
	
	
	public void vistors() 
	{
		Crud visistors = new Crud();
		XYChart.Series set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("January", visistors.size("visitors","2020-01")));
		set1.getData().add(new XYChart.Data("February",visistors.size("visitors","2020-02")));
		set1.getData().add(new XYChart.Data("March",visistors.size("visitors","2020-03")));
		set1.getData().add(new XYChart.Data("April",visistors.size("visitors","2020-04")));
		set1.getData().add(new XYChart.Data("May",visistors.size("visitors","2020-05")));
		set1.getData().add(new XYChart.Data("June",visistors.size("visitors","2020-06")));
		set1.getData().add(new XYChart.Data("July",visistors.size("visitors","2020-07")));
		set1.getData().add(new XYChart.Data("August",visistors.size("visitors","2020-08")));
		set1.getData().add(new XYChart.Data("September",visistors.size("visitors","2020-09")));
		set1.getData().add(new XYChart.Data("October",visistors.size("visitors","2020-10")));
		set1.getData().add(new XYChart.Data("November",visistors.size("visitors","2020-11")));
		set1.getData().add(new XYChart.Data("December",visistors.size("visitors","2020-12")));
		visitors.getData().addAll(set1);
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

	}
	
	public void UpdateContent() throws ParseException {
		
		
		AggregateIterable<Document> ticket  = db.read("artist");
		
		for (Document document : ticket)
	    {
			var objID = new ArrayList<>(document.values());
		    JSONObject obj = new JSONObject(document);
			SingleID = obj.getString("foreignkey");		
			title.setText(obj.getString("title"));
			description.setText(obj.getString("description"));
			price.setText(""+ obj.getString("price"));
			location.setText(""+ obj.getString("location"));
			category.setValue( obj.getString("genre"));
			String sDate = obj.getString("date");
			org.json.JSONArray tracks = obj.getJSONArray("tracks");
			
			try {
				SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
			    Date sdate;
				sdate = parser.parse(sDate);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedDate = formatter.format(sdate);
		        //System.out.println("DATE FORMATTED: " + formattedDate);
		        date.setValue(LocalDate.parse(formattedDate));
			
				
			    
				for(int i = 0; i<tracks.length();  i++) {
					JSONObject track = obj.getJSONArray("tracks").getJSONObject(i);
					String Artist = track.getString("artist");
					String Title = track.getString("title");
					String time = track.getString("duration");
					String TrackID = track.getString("fkey");	
					String id = track.getString("id");
					Track music = new Track(Title,Artist,time,id);
					datalist.getItems().add(music);
					
				}
			
			}catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }

	}
	
	public void EditableTableView() {
		datalist.setEditable(true);
		datalist.setOnMousePressed(e -> {
			 artistname.setText(datalist.getSelectionModel().getSelectedItem().getArtist().toString());
			 timesong.setText(datalist.getSelectionModel().getSelectedItem().getTime().toString());
			 musictitle.setText(datalist.getSelectionModel().getSelectedItem().getTitle().toString());
		});
		
	}
	
	public void Categories() { 
		//categories added 
		category.setValue("Category");
		category.getItems().addAll("Rock","Fusion","RNB","JAZZ");
	}
	public void SearchByType() throws IOException {
		searchbytype.setValue("Search Type");
		searchbytype.getItems().addAll("Title","Author","Genre");
		searchbtn.setOnAction(e-> FxEvents("Search", searchbytype.getValue().toString() +"@"+ SearchTxt.getText()));
		reset.setOnAction(e-> FxEvents("Reset",""));
	}
	
	public void clearAll() {
		
		for (Node node : anchorPane.getChildren()) 
		{
		   
		    if (node instanceof Text) {
		        // clear
		    	((Text)node).setVisible(false);
	    
		    }
		
		    if(node instanceof Button) {
		    	//hide
		    	((Button)node).setVisible(false);
		    }
		    
		    if(node instanceof ImageView) {
		    	//hide
		    	((ImageView)node).setVisible(false);
		    }
		    
		   
		}
	}

	public void leftSide() {
	

		genre.getItems().add(new Categories("Rock"));
		genre.getItems().add(new Categories("Rock"));
		genre.getItems().add(new Categories("Rock"));
		genre.getItems().add(new Categories("Rock"));
		genre.getItems().add(new Categories("Rock"));
		//Reset
		feeds.setOnAction(e -> FxEvents("Reset", ""));
		yourfeed.setOnAction(e -> FxEvents("YourFeed", author));
		
		
		
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
		backgroundImage.setVisible(true);
		greetings.setVisible(true);
		
	}

	
	public void pageBtn() {
		Next.setOnAction(e -> FxEvents("Next", ""));
		Previous.setOnAction(e -> FxEvents("Previous", ""));
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
	
	
	public void publish(ActionEvent event) throws IOException, ParseException {
		
		//this will provide foreign key
		Random rand = new Random();
		int rand_int1 = rand.nextInt(100000000); 
	    int rand_int2 = rand.nextInt(100000000); 
	    foreignkey = author + "-" + rand_int1 + rand_int2;
	    

		LocalDate __date	= date.getValue();
		
		if(!title.getText().toString().isEmpty() && !description.getText().toString().isEmpty() 
					&&  !category.getValue().toString().isEmpty() && !price.getText().isEmpty() 
					&& !date.getValue().toString().isEmpty() ) {
			
			if(albumcoverphoto.getText().isEmpty()) {
				popup.setText("Please add Cover Photo!");
			}else 
			{
				
				Document content = new Document()
						.append("title", title.getText())
						.append("description", description.getText())
						.append("location", location.getText())
						.append("photos", "photos/" + albumcoverphoto.getText())
						.append("date", date.getValue())
						.append("genre", category.getValue().toString())
						.append("price", price.getText())
						.append("author",author)
						.append("foreignkey", foreignkey);
						db.create(content, "artist");
						
						
						datalist.getItems().forEach(e -> {
							String GeneratedObjID = ObjectId.get().toString();
							Document track = new Document()
							.append("title", e.getTitle())
							.append("artist", e.getArtist())
							.append("duration", e.getTime())
							.append("fkey", foreignkey)
							.append("id", GeneratedObjID);
							db.create(track,"tracks");
						});

						popup.setText("Added Successfully!");

				
			}
		  
		}else {
			popup.setText("Failed Please Fill up all fields!");
		}
	    
	} 
	   
	
	public void update(ActionEvent event) throws IOException {
		Crud crud = new Crud();
		Bson content = null;
		 
		if(albumcoverphoto.getText().isEmpty()) {
			content = combine(set("title", title.getText()), set("description", description.getText()), set("date", date.getValue()),set("genre",category.getValue().toString()),set("price", price.getText()));
			popup.setText("Saved!");
		}else {
			content = combine(set("title", title.getText()), set("description", description.getText()), set("photos","photos/" + albumcoverphoto.getText()), set("date", date.getValue()),set("genre",category.getValue().toString()),set("price", price.getText()));
			popup.setText("Saved!");
		}
		crud.update(content, "artist","","");
		System.out.println("Fkey: " + SingleID);
		datalist.getItems().forEach(e -> {
			System.out.println("ID: " + e.getID());
			Bson track = combine(set("title", e.getTitle()), set("duration", e.getTime()), set("artist", e.getArtist()));
			crud.update(track, "tracks",SingleID,e.getID());
		});
		
		
	}
		

	
	public void addTrack() {
		System.out.println("Add Track");
		if(timesong.getText().isEmpty() && musictitle.getText().isEmpty() && artistname.getText().isEmpty()) {
			//popup.setText("Failed Please Fill up all fields!");
			System.out.println("Failed Please Fill up all fields!");
		}else 
		{
			
			Track track = new Track(musictitle.getText(),artistname.getText(),timesong.getText(),idStr);
			datalist.getItems().add(track);
			
			System.out.println("Song Added!!");
			 
			timesong.clear();	
			musictitle.clear();
			artistname.clear();
			
			
		} 
		
	}
	
	public void updateTrack() 
	{	
	
		EditableTableView();
		Track item =  new Track(musictitle.getText(),artistname.getText(),timesong.getText(),idStr);
		int index = datalist.getSelectionModel().getSelectedIndex();
		datalist.getItems().set(index, item);
		
       
        
	}
	 
	public void removeTrack() 
	{
		datalist.getItems().removeAll(datalist.getSelectionModel().getSelectedItem());
	}
	
	
	public void backtohome(ActionEvent event) throws IOException {
		 Session s = new Session();
		 Crud  d = new Crud();
		 s.delete(103);
		 d.clearMemoryVariable();
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
		
		String __usernamefield 	= usernamefield.getText();
		String __passwordfield 	= passwordfield.getText();
		String __firstnamefield = firstnamefield.getText();
		String __lastnamefield	= lastnamefield.getText();
		String __emailaddress	= emailaddress.getText();
		
		User userTable = new User();
		
	
		if(__usernamefield.isEmpty() && __passwordfield.isEmpty() && __firstnamefield.isEmpty() && __lastnamefield.isEmpty() && __emailaddress.isEmpty()) {
			popup.setText("Failed Please Fill up all fields!");
		}else {
			if(userTable.Register(__firstnamefield, __lastnamefield, __emailaddress,__usernamefield, __passwordfield)) {
				popup.setText("Successfully Registered!");
			}else {
				popup.setText("User already Exist!");
			}
		}
	
	}
	
	public void FxEvents(String action,String genericString) 
	{
		Session session;
		Crud crud = new Crud();
		LoadGui ldGui = new LoadGui();
		ActionEvent event = new ActionEvent();
		Stage window = new Stage();
		
		try {
			session = new Session();

			switch(action) 
			{
				case "View":
					session.writeToRandomAccessFile(100, "\n" + genericString);
					crud.MemoryLocation(103,"genericString");
					ldGui.loadTemplateFXML("SinglePage.fxml",true,window);
					insertVistors(genericString);
					((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					break;
				case "UpdatePage":
					session.writeToRandomAccessFile(100, "\n" + genericString);
					crud.MemoryLocation(103,"genericString");
					//System.out.println("ID: " + genericString);
					ldGui.loadTemplateFXML("Update.fxml",true,window);
					//((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
					break;
				case "Search":
					String[] t =  genericString.split("@");
					String searchType = t[0];
					String search = t[1];
					session.writeToRandomAccessFile(100, "\n\n\n\n" +  searchType+"@"+search );
					crud.MemoryLocation(106, "Search");    		
					crud.read("artist"); 
					clearAll();
					needtodisplay();
					content();
					break;
				case "Reset":
					session.delete(100);
					session.delete(103);
					session.delete(106);
					crud.clearMemoryVariable();
					clearAll();
					needtodisplay();
					content();	
					break;
				case "YourFeed":
					session.writeToRandomAccessFile(100, "\n\n\n\n\n" + genericString );
					crud.MemoryLocation(107, "search");
					crud.read("artist");
					clearAll();
					needtodisplay();
					content();	
					break;
				case "Delete":					 
					session.writeToRandomAccessFile(100, "\n" + genericString);
					crud.delete(genericString, "artist");
					clearAll();
					needtodisplay();
					content();
					System.out.println("Delete!");
					break;
				case "Update":
					session.writeToRandomAccessFile(100, "\n" + genericString);
					crud.MemoryLocation(103,"_id");		
					break;
				case "Previous":
					session.writeToRandomAccessFile(100, "\n\n\n\n\n\n"+ setPage--);
					crud.MemoryLocation(108, "Previous");
					clearAll();
					needtodisplay();
		    		crud.read("artist");
		    		content();
		    		break;
				case "Next":
					session.writeToRandomAccessFile(100, "\n\n\n\n\n\n"+ setPage++);
					crud.MemoryLocation(108, "Next");
					clearAll();
					needtodisplay();
		    		crud.read("artist");
		    		content();
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

