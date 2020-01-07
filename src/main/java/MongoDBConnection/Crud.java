package MongoDBConnection;



import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import assignmentgui.Session;




public class Crud extends MongoConnection{
	
	private static String ObjectId = "";
	private static String ForeignKey;
	private static int setPage;
	private static ArrayList<Row> rows = new ArrayList<>();
	private static int counter = 0;
	private static MongoCollection<Document> lists;
	private static MongoCursor<Document> doc;
	private static String memLocation = "";
	private static String column;

	public void SetPage(int page) {
		String page1 = null;
		Session s;
		try {
			s = new Session();
			String p = s.readFromRandomAccessFile(50);
			p = p.replaceAll("[^a-zA-Z0-9]", "");
			//System.out.println(p);
			if(page1 == p) {
				this.setPage += 4;
			}else {
				this.setPage -= 4;
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	
	public void insertRecord(JSONObject json) {
		
//		MongoCollection<Document> ticket = db.getCollection("artist");
//		List<Document> lists = new ArrayList<>();
//		
//		
//		lists.add(Document.parse(json.toString()));
//		ticket.insertMany(lists);
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
	
	public void createticket(String title, String Description, String location, String date, String Genre, double price, String photoname, String foreignkey, String author) {
		
		MongoCollection<Document> ticket = db.getCollection("artist");
		List<Document> ticketlists = new ArrayList<>();
		JSONObject json = new JSONObject();
		
		
		// these are the mongodb fields to be inserted
		json.put("title", title);
		json.put("description", Description);
		json.put("location", location);
		json.put("photos", "photos/" + photoname);
		json.put("date", date);
		json.put("genre", Genre);
		json.put("price", price);
		json.put("author",author);
		json.put("foreignkey", foreignkey);
		
		ticketlists.add(Document.parse(json.toString()));
		ticket.insertMany(ticketlists);
		
		
		
	
	}
	
	
	public static ArrayList<Row> display() {
		
		
        lists = db.getCollection("artist");
        rows.clear(); //clear the ArrayList first
        

        Document lookupFields = new Document("from", "tracks");
        lookupFields.put("localField", "fkey");
        lookupFields.put("foreignField", "foreignkey");
        lookupFields.put("as", "tracks");
        AggregateIterable<Document> data = null; 
        
        if(memLocation.isEmpty()) {
        	data = lists.aggregate(Arrays.asList( 
    				new Document("$lookup", lookupFields),
        			new Document("$skip", 0),
        			new Document("$limit", 4)
        	));
        }else 
        {
        	BasicDBObject OBJ = new BasicDBObject();
        	System.out.println("ELSE! " + column);
        	ObjectId = memLocation.replaceAll("[^a-zA-Z0-9]", "");
    		OBJ.put(column, ObjectId.isEmpty() || ObjectId == null ?  "" :  new ObjectId(ObjectId));	
        	data = lists.aggregate(Arrays.asList( 
        			new Document("$lookup", lookupFields),
        			new Document("$match", OBJ)
        	)); 
        	memLocation = "";
        }
       
      
    	
    	
    	//OBJ.put("title", "asdasd123123ase");	
		for (Document document : data)
 	    {
 	    		
 	    	
 	    		Row row;
 	    		var objID = new ArrayList<>(document.values());
 		   
 		    	JSONObject obj = new JSONObject(document);
 		    	
 		    	
 		    	String title		 = obj.getString("title");
 		    	String description 	 = obj.getString("description");
 		    	double price		 = obj.getDouble("price");
 		    	String date			 = obj.getString("date");
 		    	String author		 = obj.getString("author");
 		    	String genre		 = obj.getString("genre");
 		    	String location		 = obj.getString("location");
 		    	String photos		 = obj.getString("photos");
 		    	String fkey			 = obj.getString("foreignkey");
 		    	String objectID		 = objID.get(0).toString();
 		    	
 		        
 		    	org.json.JSONArray c = obj.getJSONArray("tracks");
 		    	
 		    	row = new Row(title,description,price,location,date,objectID,photos,genre,author,fkey,c);
 		    	
 		    	rows.add(row);
 	   }
  	
	   
	   

	    
	  
	    //recursion(data);
	   
	    
	 
	    	
	    	

    	//must be clear
    	ObjectId = "";
      
		return rows; /*recursion(data)*/
	
	}
	
	public static void recursion(AggregateIterable<Document> data) {
		
		
		doc =   data.cursor();
		
	
//		if(doc.hasNext()) {
//			var d = doc.next();
//			JSONObject obj = new JSONObject(d);
//			System.out.println(obj.getString("title"));
//			recursion(data);
//		}
		//System.out.println(doc.hasNext());
//		while(doc.hasNext()) {
//			var d = doc.next();
//			JSONObject obj = new JSONObject(d);
//			System.out.println(obj.getString("title"));
//			//recursion(data);
//		}
//		System.out.println(doc.hasNext());
		
//		if(doc.hasNext() == true) 
//		{
//			var d = doc.next();
//			JSONObject obj = new JSONObject(d);
//			System.out.println(obj.getString("title"));
//		    recursion(data);
//		    
//		}
			
	
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
	
	
	public static String MemoryLocation(int location, String __column) throws IOException {
		Session s = new Session();
		String Data = s.readFromRandomAccessFile(location);
		memLocation = Data;
		column = __column;
		System.out.println("DATA: " + Data);
		return Data; 
	}
	
}
