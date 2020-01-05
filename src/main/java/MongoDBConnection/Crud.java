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
import com.mongodb.Block;
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


	
	public void SetPage(int page) {
		if(page == 1) {
			this.setPage += 4;
		}else {
			this.setPage -= 4;
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
		
		
        lists = db.getCollection("artist");
        rows.clear(); //clear the ArrayList first
       

        Document lookupFields = new Document("from", "tracks");
        lookupFields.put("localField", "fkey");
        lookupFields.put("foreignField", "foreignkey");
        lookupFields.put("as", "tracks");
       
    	try {
			Session s = new Session();
			String ooo = s.readFromRandomAccessFile(100);
			if(ooo != null) {
				ObjectId = ooo.replaceAll("[^a-zA-Z0-9]", "");
			}else {
				
			}
			
			System.out.println("OBJECT ID: " + ObjectId);
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	BasicDBObject OBJ = new BasicDBObject();
		OBJ.put("_id", ObjectId.isEmpty() || ObjectId == null ?  "" :  new ObjectId(ObjectId));	
		
	    AggregateIterable<Document> data;
	   
	    if(!OBJ.get("_id").equals("")){
	    	data = lists.aggregate(Arrays.asList( 
	        			new Document("$lookup", lookupFields),
	        			new Document("$match", OBJ)
	     	));
	    	
	    }else {
	    	data = lists.aggregate(Arrays.asList( 
	        			new Document("$lookup", lookupFields)
	        			
	     	));
	    	
	    	
	    }
	    
	  
	    //recursion(data);
	   
	    
	    for (Document document : data)
	    {
	    		
	    	
	    		Row row;
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
		    	
		        
		    	org.json.JSONArray c = obj.getJSONArray("tracks");
		    	
		    	row = new Row(title,description,price,location,date,objectID,photos,genre,author,fkey,c);
		    		
		    	   
		          
		    	rows.add(row);
	   }
	    	
	    	

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
	


	public void putObjectID(String id) {
		// TODO Auto-generated method stub
		//ObjectId =  id;
	}
	
	public void putForeignKey(String Fkey) {
		//ForeignKey = Fkey;
	}
	
	
}
