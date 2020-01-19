package MongoDBConnection;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import assignmentgui.Session;




public class Crud extends MongoConnection{
	
	private static String SearchKeyword = null;
	private static int setPage;
	private static ArrayList<Row> rows = new ArrayList<>();
	private static int counter = 0;
	private static MongoCollection<Document> lists;
	private static String memLocation = "";
	private static String column;
	private static int loc;

	
	public void create(JSONObject json, String Collection) 
	{
		MongoCollection<Document> mgcollection = db.getCollection(Collection);
		List<Document> document = new ArrayList<>();
		document.add(Document.parse(json.toString()));
		mgcollection.insertMany(document);
		 
	}
	
	
	public static ArrayList<Row> read() {
		
		
        lists = db.getCollection("artist");
        rows.clear(); //clear the ArrayList first
        

        Document lookupFields = new Document("from", "tracks");
        lookupFields.put("localField", "foreignkey");
        lookupFields.put("foreignField", "fkey");
        lookupFields.put("as", "tracks");
        
        AggregateIterable<Document> data = null;
        
        if(memLocation.length() == 0) 
        {
        	
       
        	data = lists.aggregate(Arrays.asList(
        			new Document("$lookup", lookupFields),
            		new Document("$skip", 0),
            		new Document("$limit", 4)
            ));
        	
        }else 
        {
        	
        	BasicDBObject keywordAndField = new BasicDBObject();
        	Session session;
			try {
				session = new Session();
				
				switch(loc) {
					case 103:
						SearchKeyword = memLocation.replaceAll("[^a-zA-Z0-9]", "");
		            	System.out.println("Check keyword: " + SearchKeyword);	
		        		keywordAndField.put("_id", new ObjectId(SearchKeyword));
		        		data = lists.aggregate(Arrays.asList( 
			        			new Document("$lookup", lookupFields),
			        			new Document("$match", keywordAndField)
			        	)); 
		        		session.delete(103);
						break;
					case 106:
						session.delete(106);
	        			//System.out.println("LOCATION:  " + loc + "keyword: " + memLocation);
	        		    SearchKeyword = memLocation.trim();
	        		    String[] SearcArray = SearchKeyword.split("@");
	        		    String field = SearcArray[0].isEmpty() ? "title" : SearcArray[0];
	        			keywordAndField.put(field.toLowerCase(), SearcArray[1]);
	        			data = lists.aggregate(Arrays.asList( 
			        			new Document("$lookup", lookupFields),
			        			new Document("$match", keywordAndField)
			        	)); 
	        			break;
					case 107:
						String author = memLocation.trim();
		        		keywordAndField.put("author", author);
		        		data = lists.aggregate(Arrays.asList( 
			        			new Document("$lookup", lookupFields),
			        			new Document("$match", keywordAndField)
			        	)); 
		        		break;
		        		
					case 108: 
						SearchKeyword = memLocation.replaceAll("[^a-zA-Z0-9]", "");
			        	//System.out.println("location: " + loc + " Page: " +SearchKeyword);
			        	int page = Integer.parseInt(SearchKeyword);
			        	data = lists.aggregate(Arrays.asList(
			        			new Document("$lookup", lookupFields),
			            		new Document("$skip", page),
			            		new Document("$limit", 4)
			            ));
						break;
					
					default:
						break;
				}
			
	        	
	        	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        }	
        
        Row row;

        

		for (Document document : data)
 	    {
			
	
 	    		
 	    		var objID = new ArrayList<>(document.values());
 		    	JSONObject obj = new JSONObject(document);
 		    	
 		    	String title		 = obj.getString("title");
 		    	String description 	 = obj.getString("description");
 		    	double price		 = obj.getDouble("price");
 		    	String date		 = 		obj.getString("date");
 		    	String author		 = obj.getString("author");
 		    	String genre		 = obj.getString("genre");
 		    	String location		 = obj.getString("location");
 		    	String photos		 = obj.getString("photos");
 		    	String fkey			 = obj.getString("foreignkey");
 		    	String objectID		 = objID.get(0).toString();
 		   
 		    	
 		    	
 		    	//LocalDate.parse(text, formatter)
 		    	//System.out.println("TITLE TEST: " + title);
 		        
 		    	org.json.JSONArray c = obj.getJSONArray("tracks");
 		    	
 		    	row = new Row(title,description,price,location,date,objectID,photos,genre,author,fkey,c);
 		    	
 		    	rows.add(row);
 		    	
 		    	
 	   }
    
    	//must be clear
		SearchKeyword = "";
		//memLocation = "";
      
		return rows;
	
	}
	
	
	public static void update(Bson json,String Collection) {
		//reference: https://www.mkyong.com/mongodb/java-mongodb-update-document/
		String ObjectId = memLocation.replaceAll("[^a-zA-Z0-9]", "");
		MongoCollection<Document> mgcollection = db.getCollection(Collection);
		BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(ObjectId));
		mgcollection.updateOne(searchQuery,json);
		

	}
	
	public static void delete(String ObjectId , String Collection ) 
	{
		//reference: https://www.mkyong.com/mongodb/java-mongodb-delete-document/
		MongoCollection<Document> mgcollection = db.getCollection(Collection);
		BasicDBObject document = new BasicDBObject();
		document.put("_id", new ObjectId(ObjectId));
		mgcollection.findOneAndDelete(document);
	}
	
	public static void clearMemoryVariable() {
		memLocation = "";
	}
	
	public static String MemoryLocation(int location, String __column) throws IOException {
		Session s = new Session();
		String Data = s.readFromRandomAccessFile(location);
		memLocation = Data;
		column = __column;
		loc =  location;
		return Data; 
	}


	
	
}
