package MongoDBConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import javafx.scene.image.Image;
import kikoassigment.pagination;



public class Crud extends MongoConnection{
	private String __id;
	private static boolean ShowAll = true;
	private static String ObjectId = "";
	private static int setPage;
	
	
	public void SetPage(int page) {
		
		this.setPage = page;
	}
	
	public static int getPage() {
		System.out.println(setPage);
		return 3;
	}
	
	public void AddRecord(String firstname, String lastname, String email, String username, String password, String emailaddress) {
		MongoCollection<Document> userDocuments = db.getCollection("users");
		
	
		userDocuments.drop();
		List<Document> Registers = new ArrayList<>();
		JSONObject json = new JSONObject();
		//BasicDBObject obj = new BasicDBObject();
		json.put("firstname", firstname);
		json.put("lastname", lastname);
		json.put("email", emailaddress);
		json.put("username", username);
		json.put("password", password);

		//System.out.println(json.toString());
		Registers.add(Document.parse(json.toString()));
		userDocuments.insertMany(Registers);
	}
	
	public void createticket(String title, String Description, String location, LocalDate dte, String Genre, String price) {
		
		MongoCollection<Document> ticket = db.getCollection("artist");
		List<Document> ticketlists = new ArrayList<>();
		JSONObject json = new JSONObject();
		
		//Image img1 = new Image(getClass().getResource(printRow.getImage()).toExternalForm(),true);
	    //photo1.setImage(img1);
		//title_row1.setText(printRow.getTitle());	
		//content_1.setText(printRow.getDescription().substring(0, 550));
		//title_row2.setText(printRow.getTitle());
		//location_1.setText(printRow.getLocation());
		//price_1.setText(printRow.getPrice());
		//singleDate.setText(printRow.getDate());
		
		
		json.put("location", location);
		json.put("date", dte);
		json.put("genre", Genre);
		json.put("price", price);
		json.put("description", Description);
		json.put("title", title);

		//System.out.println(json.toString());
		ticketlists.add(Document.parse(json.toString()));
		ticket.insertMany(ticketlists);
		
	}
	
	
	public static ArrayList<Row> display() {
		
		ArrayList<Row> rows = new ArrayList<>();
        Row row;
        
        
		
        MongoCollection<Document> lists = db.getCollection("artist");
       
		if(ShowAll == true) { 
	        try (MongoCursor<Document> cur = lists.find().skip(setPage).limit(4).iterator()) {
	
	            while (cur.hasNext()) {
	
	                var doc = cur.next();
	                var product = new ArrayList<>(doc.values());
	                String title = (String) product.get(6);
	                String location = (String) product.get(5);
	                String description = (String) product.get(4);
	                String price = (String) product.get(3);
	                String date = (String) product.get(2);
	                String img = (String) product.get(1);
	                String id = (String) product.get(0).toString();
	               // System.out.printf("%s: %s%n", product.get(1), product.get(2));
	                row = new Row(title,description,price,location,date,id,img);
	  		        rows.add(row);
	            
	            }
	        }
		}else {
			
		
				System.out.println("false");
				System.out.println(ObjectId);
				try (MongoCursor<Document> cur =  lists.find(Filters.eq("_id", new ObjectId(ObjectId))).iterator()){
				
				 while (cur.hasNext()) {
						
		                var doc = cur.next();
		                var product = new ArrayList<>(doc.values());
		                String title = (String) product.get(6);
		                String location = (String) product.get(5);
		                String description = (String) product.get(4);
		                String price = (String) product.get(3);
		                String date = (String) product.get(2);
		                String img = (String) product.get(1);
		                String id = (String) product.get(0).toString();
		               // System.out.printf("%s: %s%n", product.get(1), product.get(2));
		                row = new Row(title,description,price,location,date,id,img);
		  		        rows.add(row);
		            
		           }
				 
			 } 
			
		}
		return rows;
	
	}
	
	public static ArrayList<trackRow> tracklist() 
	{
		ArrayList<trackRow> rows = new ArrayList<>();
		trackRow trackrow;
		
		  MongoCollection<Document> lists = db.getCollection("tracks");
		   try (MongoCursor<Document> cur = lists.find().skip(0).limit(20).iterator()) {
			  
			   while (cur.hasNext()) {
					
	                var doc = cur.next();
	                var product = new ArrayList<>(doc.values());
	                String trackID = (String) product.get(4);
	                String time = (String) product.get(3);
	                String artist = (String) product.get(2);
	                String title = (String) product.get(1);
	               // System.out.printf("%s: %s%n", product.get(1), product.get(2));
	                trackrow = new trackRow(title,artist,time,trackID);
	  		        rows.add(trackrow);
	            
	            }
			   	
		   }
		   
		
		return rows;
        
	}
	public void getID(String __id__) {
		
		this.__id =  __id__;
	}
	
	public void Show(Boolean s) {
		
		ShowAll = s;
	}

	public void putObjectID(String id) {
		// TODO Auto-generated method stub
		ObjectId =  id;
	}
	
	
}
