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
	private static String ForeignKey;
	private static int setPage;
	private static int[]  pageArray;
	
	
	public void SetPage(int page) {
		if(page == 1) {
			this.setPage += 4;
		}else {
			this.setPage -= 4;
		}
	
	}
	
	public static int getPage() {
		System.out.println(setPage);
		return 3;
	}
	
	public boolean AddRecord(String firstname, String lastname, String email, String username, String password) {
		 MongoCollection<Document> userInfo = db.getCollection("users");
			try (MongoCursor<Document> cur =  userInfo.find(Filters.eq("username",username)).iterator()){
				
				if(cur.hasNext()) {
					return false;
				}else {
					List<Document> Registers = new ArrayList<>();
					JSONObject json = new JSONObject();
					//BasicDBObject obj = new BasicDBObject();
					json.put("firstname", firstname);
					json.put("lastname", lastname);
					json.put("email", email);
					json.put("username", username);
					json.put("password", password);

					//System.out.println(json.toString());
					Registers.add(Document.parse(json.toString()));
					userInfo.insertMany(Registers);
					return true;
				}
			}
		
		
		
	}
	
	public void createticket(String title, String Description, String location, LocalDate dte, String Genre, String price, String photoname, String foreignkey, String author) {
		
		MongoCollection<Document> ticket = db.getCollection("artist");
		List<Document> ticketlists = new ArrayList<>();
		JSONObject json = new JSONObject();
		
		// these are the mongodb fields to be inserted
		json.put("title", title);
		json.put("description", Description);
		json.put("location", location);
		json.put("photos", "photos/" + photoname);
		json.put("date", dte);
		json.put("genre", Genre);
		json.put("price", price);
		json.put("author",author);
		json.put("foreignkey", foreignkey);
		
		ticketlists.add(Document.parse(json.toString()));
		ticket.insertMany(ticketlists);
		
		
		
	
	}
	
	
	public static ArrayList<Row> display() {
		
		ArrayList<Row> rows = new ArrayList<>();
        Row row;
        
      System.out.println("SET PAGE: " + setPage);
		
        MongoCollection<Document> lists = db.getCollection("artist");
       
		if(ShowAll == true) { 
	        try (MongoCursor<Document> cur = lists.find().skip(setPage).limit(4).iterator()) {
	
	            while (cur.hasNext()) {
	
	                var doc = cur.next();
	                var product = new ArrayList<>(doc.values());
	                String foreignkey = (String) product.get(9);
	                String img = (String) product.get(8);
	                String title = (String) product.get(7);
	                String location = (String) product.get(6);
	                String description = (String) product.get(5);
	                String genre = (String) product.get(4);
	                String author 	= (String) product.get(3);
	                String price = (String) product.get(2);
	                String date = (String) product.get(1);
	                String id = (String) product.get(0).toString();
	              
	                row = new Row(title,description,price,location,date,id,img,genre,author,foreignkey);
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
		                String foreignkey = (String) product.get(9);
		                String img = (String) product.get(8);
		                String title = (String) product.get(7);
		                String location = (String) product.get(6);
		                String description = (String) product.get(5);
		                String genre = (String) product.get(4);
		                String author 	= (String) product.get(3);
		                String price = (String) product.get(2);
		                String date = (String) product.get(1);
		                String id = (String) product.get(0).toString();
		              
		                row = new Row(title,description,price,location,date,id,img,genre,author,foreignkey);
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
		   try (MongoCursor<Document> cur = lists.find(Filters.eq("foreignkey" , ForeignKey)).iterator()) {
			  
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
	
	public void insertTracks(String title, String artist, String duration,String foreignkey) 
	{
		MongoCollection<Document> tracks = db.getCollection("tracks");
		List<Document> trackslists = new ArrayList<>();
		JSONObject json = new JSONObject();
		
		// these are the mongodb fields to be inserted tracks table
		json.put("title", title);
		json.put("artist", artist);
		json.put("duration", duration);
		json.put("foreignkey", foreignkey);
		trackslists.add(Document.parse(json.toString()));
		tracks.insertMany(trackslists);
		
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
	
	public void putForeignKey(String Fkey) {
		ForeignKey = Fkey;
	}
	
	
}
