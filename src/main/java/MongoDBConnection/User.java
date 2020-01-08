package MongoDBConnection;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class User {

	/*public boolean AddRecord(String firstname, String lastname, String email, String username, String password)
	{
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
		
		
		
	}*/

}
