package MongoDBConnection;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import assignmentgui.Session;

public class Login extends MongoConnection{
	

	
	public boolean verify(String username, String password) throws IOException {
		//load session class
		Session s = new Session();
		
		boolean res = false;
		MongoCollection<Document> UserLogin  = db.getCollection("users");
		
		BasicDBObject obj = new BasicDBObject();
		obj.put("username", username);
		obj.put("password", password );
	
		Document result = UserLogin.find(obj).first();
		 
		
		if(result != null) {
			
			
			String resultJsonString = result.toJson();
			var objID = new ArrayList<>(result.values());
			JSONObject obj1 = new JSONObject(result);
			
			String userID			= objID.get(0).toString();
			String uname 			= obj1.getString("username");
			String firstname 	    = obj1.getString("firstname");
			String lastname			= obj1.getString("lastname");
			
			
			
			if(resultJsonString.isEmpty()) {
				res = false;
			}else {
				res = true;
				//s.WriteSession(username);
				//write the content to offset 0
				s.writeToRandomAccessFile(0,  userID + "," + uname + "," +  firstname + "," + lastname);	
			}
	
		}

		return res;
	}

}
