package MongoDBConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;


public class Crud extends MongoConnection{
	private String __id;
	private static boolean ShowAll = true;
	private static String ObjectId = "";
	private static String ForeignKey;
	private static int setPage;
	private static int[]  pageArray;
	private static ArrayList<Row> rows = new ArrayList<>();
	
	
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
				
				//check if username exist
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
	
	static Block<Document> loopData = new Block<Document>() {
		
	    public void apply(final Document document) {
	    	Row row;
	    	//System.out.println(document.toJson());
	    	
	    	var objID = new ArrayList<>(document.values());
	    	
	    	JSONObject obj = new JSONObject(document);
	    	
	    	
	    	String title		 = obj.getString("title");
	    	String description 	 = obj.getString("description");
	    	String price		 = obj.getString("price");
	    	String date			 = obj.getString("date");
	    	String author		 = obj.getString("author");
	    	String genre		 = obj.getString("genre");
	    	String location		 = obj.getString("location");
	    	String photos		 = obj.getString("photos");
	    	String fkey			 = obj.getString("fkey");
	    	String objectID		 = objID.get(0).toString();
	    	
	    	row = new Row(title,description,price,location,date,objectID,photos,genre,author,fkey);
	    	
	    	rows.add(row);
	    	
	    
	    	
	
	    }

		private Date Date(Object object) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public static ArrayList<Row> display() {
		
		
        MongoCollection<Document> lists = db.getCollection("artist");
        rows.clear(); //clear the ArrayList first
        //Join to Collection artist and track
        if(ObjectId.isEmpty()) {
        	
        	 AggregateIterable<Document> data = lists.aggregate(Arrays.asList( 
     				Aggregates.lookup("tracks", "fkey", "foreignkey", "tracks")
     		
     		));
             data.forEach(loopData);
        }else {
        	
        	BasicDBObject obj_ID = new BasicDBObject();
        	obj_ID.put("_id", new ObjectId(ObjectId));
    
        	 AggregateIterable<Document> data = lists.aggregate(Arrays.asList(
      				Aggregates.match(obj_ID), 
      				Aggregates.lookup("tracks", "fkey", "foreignkey", "tracks")
      		
      		));
        	 ObjectId = ""; //make sure to clear this variable
        	 data.forEach(loopData);
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
	                String time = (String) product.get(1);
	                String title = (String) product.get(2);
	                String artist = (String) product.get(3);
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
